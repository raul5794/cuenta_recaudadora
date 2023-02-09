package bcp.cuenta.recaudadora.Entity.secondary;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "volante")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Volante {

    @Id
    private String numero;
    @Column(name = "razon_social")
    private String razonSocial;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Volante volante = (Volante) o;
        return numero != null && Objects.equals(numero, volante.numero);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
