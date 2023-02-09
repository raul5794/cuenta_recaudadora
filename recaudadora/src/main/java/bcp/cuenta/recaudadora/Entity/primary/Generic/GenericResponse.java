package bcp.cuenta.recaudadora.Entity.primary.Generic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity(name = "response")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name="tipo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PrimaryKey.class)
public class GenericResponse {
    @Id
    @Size(min = 36, max = 36)
    @Column(name = "rquuid")
    @NotEmpty(message = "No puede estar Vacio")
    private String rqUUID;
    @Size(min = 6, max = 6)
    @Column(name = "result_code")
    private String resultCode;
    @Size(min = 1, max = 80)
    @Column(name = "result_description")
    private String resultDescription;
    @Size(min = 19, max = 19)
    @Column(name = "operation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss",locale = "es_PE",timezone = "GMT-5")
    private Date operationDate;

    @JsonIgnore
    @Column(name = "tipo",insertable = false,updatable = false)
    @NotEmpty(message = "No puede estar Vacio")
    private String tipo;

    @Id
    @JsonIgnore
    private String id;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GenericResponse that = (GenericResponse) o;
        return rqUUID != null && Objects.equals(rqUUID, that.rqUUID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
