package bcp.cuenta.recaudadora.Entity.primary;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericResponse;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@DiscriminatorValue("pago")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PagoResponse extends GenericResponse {
    @Size(min = 1, max = 12)
    @Column(name = "operation_number_company")
    String operationNumberCompany;
    @Nullable
    @Size(min = 0, max = 60)
    String endorsement = "Gracias por realizar su pago";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PagoResponse that = (PagoResponse) o;
        return getRqUUID() != null && Objects.equals(getRqUUID(), that.getRqUUID());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
