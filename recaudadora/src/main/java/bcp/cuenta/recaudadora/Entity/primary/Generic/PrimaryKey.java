package bcp.cuenta.recaudadora.Entity.primary.Generic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryKey implements Serializable {

    private String rqUUID;

    private String id;
}
