package bcp.cuenta.recaudadora.Entity.primary;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericResponse;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@DiscriminatorValue("consulta")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaResponse extends GenericResponse {
    @Size(min = 1,max = 12)
    @Column(name = "operation_number_company")
    String operationNumberCompany;
    @Size(min = 1,max = 40)
            @Column(name = "customer_name")
    String customerName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ConsultaResponse that = (ConsultaResponse) o;
        return getRqUUID() != null && Objects.equals(getRqUUID(), that.getRqUUID());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
