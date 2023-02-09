package bcp.cuenta.recaudadora.Entity.primary;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericRequest;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@DiscriminatorValue("pago")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequest extends GenericRequest {
    @Size(min = 4, max = 4)
    @Column(name = "service_id")
    String serviceId;
    @Size(min = 2, max = 2)
    @Column(name = "payment_type")
    String paymentType;
    @Size(min = 4, max = 20)
    @Column(name = "amount_total")
    Double amountTotal;
    @Nullable
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "checkNumber", column = @Column(name = "check_number")),
            @AttributeOverride(name = "financialEntity", column = @Column(name = "financial_entity"))
    })
    Check check;
    @Size(min = 1, max = 28)
    @Column(name = "document_reference")
    String documentReference;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PagoRequest that = (PagoRequest) o;
        return getRqUUID() != null && Objects.equals(getRqUUID(), that.getRqUUID());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
