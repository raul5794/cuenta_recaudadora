package bcp.cuenta.recaudadora.Entity.primary;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericResponse;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("extorno")
public class ExtornoResponse extends GenericResponse {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExtornoResponse that = (ExtornoResponse) o;
        return getRqUUID() != null && Objects.equals(getRqUUID(), that.getRqUUID());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
