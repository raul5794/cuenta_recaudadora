package bcp.cuenta.recaudadora.Entity.primary;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Check implements Serializable {
    @Size(min = 1,max = 15)
    private String checkNumber;
    @Size(min =3, max=3)
    private String financialEntity;
}
