package bcp.cuenta.recaudadora.Entity.secondary;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "anexo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Anexo {

    @Id
    private String codigo;

    private String descripcion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Anexo anexo = (Anexo) o;
        return codigo != null && Objects.equals(codigo, anexo.codigo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
