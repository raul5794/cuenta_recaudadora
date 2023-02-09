package bcp.cuenta.recaudadora.Entity.primary.Error;

import bcp.cuenta.recaudadora.Entity.primary.Generic.GenericResponse;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ErrorResponse extends GenericResponse {
@Column(name = "result_code_company")
    private String resultCodeCompany;
@Column(name = "result_description_company")
    private String resultDescriptionCompany;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return getRqUUID() != null && Objects.equals(getRqUUID(), that.getRqUUID());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
