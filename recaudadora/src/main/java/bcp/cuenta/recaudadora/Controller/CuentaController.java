package bcp.cuenta.recaudadora.Controller;

import bcp.cuenta.recaudadora.Entity.primary.*;
import bcp.cuenta.recaudadora.Entity.primary.Error.ErrorResponse;
import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericResponse;
import bcp.cuenta.recaudadora.Entity.primary.Generic.PrimaryKey;
import bcp.cuenta.recaudadora.Entity.secondary.Anexo;
import bcp.cuenta.recaudadora.Entity.secondary.Recaudacion;
import bcp.cuenta.recaudadora.Entity.secondary.Volante;
import bcp.cuenta.recaudadora.Service.Chaski.AnexoService;
import bcp.cuenta.recaudadora.Service.Chaski.RecaudacionService;
import bcp.cuenta.recaudadora.Service.Chaski.VolanteService;
import bcp.cuenta.recaudadora.Service.RequestService;
import bcp.cuenta.recaudadora.Service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("bcp")
public class CuentaController {

    @Autowired
    RequestService requestService;
    @Autowired
    ResponseService responseService;

    @Autowired
    VolanteService volanteService;

    @Autowired
    RecaudacionService recaudacionService;

    @Autowired
    AnexoService anexoService;


    @PostMapping(value = "/consulta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consulta(@RequestBody ConsultaRequest consultaRequest) throws Exception {
        try {
            consultaRequest.setId("c");
            this.requestService.saveRequest(consultaRequest);
            Optional<Volante> volante = this.volanteService.getVolante(consultaRequest.getCustomerId());
            Optional<Anexo> anexo = this.anexoService.getAnexo(consultaRequest.getCustomerId());
            if (volante.isEmpty() && anexo.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setRqUUID(consultaRequest.getRqUUID());
                errorResponse.setResultDescription("ERROR AL PROCESAR TRANSACCION");
                errorResponse.setResultCode("CP0138");
                errorResponse.setOperationDate(new Date());
                errorResponse.setResultDescriptionCompany("No existe el número");
                errorResponse.setResultCodeCompany("404");
                errorResponse.setId("e");
                this.responseService.saveResponse(errorResponse);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            } else {
                ConsultaResponse consultaResponse = new ConsultaResponse();
                consultaResponse.setRqUUID(consultaRequest.getRqUUID());
                consultaResponse.setOperationDate(new Date());
                consultaResponse.setResultDescription("PROCESO CONFORME");
                consultaResponse.setResultCode("CP0000");
                consultaResponse.setId("c");
                consultaResponse.setCustomerName(volante.isPresent() ? volante.get().getRazonSocial() : anexo.get().getDescripcion());
                consultaResponse.setOperationNumberCompany(volante.isPresent() ? volante.get().getNumero() : anexo.get().getDescripcion());
                this.responseService.saveResponse(consultaResponse);
                return new ResponseEntity<>(consultaResponse, HttpStatus.OK);
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setRqUUID(consultaRequest.getRqUUID());
            errorResponse.setResultDescription("ERROR AL PROCESAR TRANSACCION");
            errorResponse.setResultCode("CP0138");
            errorResponse.setOperationDate(new Date());
            errorResponse.setResultDescriptionCompany("ERROR AL PROCESAR TRANSACCION");
            errorResponse.setResultCodeCompany("123");
            errorResponse.setId("e");
            e.printStackTrace();
            this.responseService.saveResponse(errorResponse);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/pago", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pago(@RequestBody PagoRequest pagoRequest) throws Exception {
        try {
            Optional<ConsultaResponse> r = this.responseService.getResponse(new PrimaryKey(pagoRequest.getRqUUID(), "c"));


            pagoRequest.setId("p");
            this.requestService.saveRequest(pagoRequest);

            Recaudacion recaudacion = new Recaudacion();

            recaudacion.setCodigo(pagoRequest.getOperationNumber());
            recaudacion.pkI("1041201", pagoRequest.getOperationNumber());
            recaudacion.setNumeroCuenta("193-0014555-1-29");
            recaudacion.setImporte(pagoRequest.getAmountTotal());
            recaudacion.setFecha(new Date());
            recaudacion.setUsuario("AUTOMATICO");
            recaudacion.setFechaOperacion(new Date());

            this.recaudacionService.saveRecaudacion(recaudacion);

            PagoResponse pagoResponse = new PagoResponse();
            pagoResponse.setRqUUID(pagoRequest.getRqUUID());
            pagoResponse.setResultCode("CP000");
            pagoResponse.setResultDescription("PROCESO CONFORME");
            pagoResponse.setOperationDate(recaudacion.getFecha());
            pagoResponse.setOperationNumberCompany(r.get().getOperationNumberCompany());
            pagoResponse.setEndorsement("Gracias por su pago");
            pagoResponse.setId("p");
            this.responseService.saveResponse(pagoResponse);
            return new ResponseEntity<>(pagoResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setRqUUID(pagoRequest.getRqUUID());
            errorResponse.setResultDescription("ERROR AL PROCESAR TRANSACCION");
            errorResponse.setResultCode("CP0138");
            errorResponse.setOperationDate(new Date());
            errorResponse.setResultDescriptionCompany("ERROR AL PROCESAR TRANSACCION");
            errorResponse.setResultCodeCompany("123");
            errorResponse.setId("e");
            e.printStackTrace();
            this.responseService.saveResponse(errorResponse);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/extorno", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> extorno(@RequestBody ExtornoRequest extornoRequest) throws Exception {
        try {
            extornoRequest.setId("x");
            this.requestService.saveRequest(extornoRequest);
            Recaudacion recaudacion = new Recaudacion();
            recaudacion.pkI("1041201", extornoRequest.getOperationNumberAnnulment());
            this.recaudacionService.deleteRecaudacion(recaudacion.getId());
            ExtornoResponse extornoResponse = new ExtornoResponse();
            extornoResponse.setRqUUID(extornoRequest.getRqUUID());
            extornoResponse.setResultCode("CP000");
            extornoResponse.setResultDescription("PROCESO CONFORME");
            extornoResponse.setOperationDate(new Date());
            extornoResponse.setId("x");
            this.responseService.saveResponse(extornoResponse);
            return new ResponseEntity<>(extornoResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setRqUUID(extornoRequest.getRqUUID());
            errorResponse.setResultDescription("ERROR AL PROCESAR TRANSACCION");
            errorResponse.setResultCode("CP0138");
            errorResponse.setOperationDate(new Date());
            errorResponse.setResultDescriptionCompany("ERROR AL PROCESAR TRANSACCION");
            errorResponse.setResultCodeCompany("123");
            errorResponse.setId("e");
            e.printStackTrace();
            this.responseService.saveResponse(errorResponse);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
