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
@DiscriminatorValue("consulta")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaRequest extends GenericRequest {
        @Size(min = 4,max = 4)
        @Column(name = "service_id")
        private String serviceId;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
                ConsultaRequest that = (ConsultaRequest) o;
                return getRqUUID() != null && Objects.equals(getRqUUID(), that.getRqUUID());
        }

        @Override
        public int hashCode() {
                return getClass().hashCode();
        }
}
