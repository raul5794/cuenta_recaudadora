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
@Entity(name = "request")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name="tipo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PrimaryKey.class)
public class GenericRequest {
    @Id
    @Size(min = 36, max = 36)
    @Column(name = "rquuid")
    @NotEmpty(message = "No puede estar Vacio")
    private String rqUUID;
    @Size(min = 19, max = 19)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss",locale = "es_PE",timezone = "GMT-5")
    private Date operationDate;
    @Size(min = 1, max = 8)
    @Column(name = "operation_number")
    private String operationNumber;
    @Size(min = 3, max = 3)
    private String banco;
    @Size(min = 1, max = 14)
    @Column(name = "customer_id")
    private String customerId;
    @Size(min = 2, max = 2)
    private String channel;

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
        GenericRequest that = (GenericRequest) o;
        return rqUUID != null && Objects.equals(rqUUID, that.rqUUID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
