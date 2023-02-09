package bcp.cuenta.recaudadora.Entity.primary;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericRequest;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@DiscriminatorValue("extorno")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExtornoRequest  extends GenericRequest {

    @Column(name = "operation_number_annulment")
    @Size(min = 8,max = 8)
    private String operationNumberAnnulment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExtornoRequest that = (ExtornoRequest) o;
        return getRqUUID() != null && Objects.equals(getRqUUID(), that.getRqUUID());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
