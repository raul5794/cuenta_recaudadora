package bcp.cuenta.recaudadora.Entity.secondary;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "con_recaudacion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Recaudacion {

    @EmbeddedId
    PK id;


    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    private String codigo;

    @Column(name = "fecha_operacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOperacion;

    private double importe;

    private String usuario;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;


    public void pkI(String a,String b){
        this.setId(new PK(a,b));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PK implements Serializable{
        @Column(name = "numero_operacion")
        private String numeroOperacion;

        @Column(name = "codigo_cuenta")
        private String codigoCuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recaudacion that = (Recaudacion) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
