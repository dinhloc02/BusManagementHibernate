package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class CompositeKey implements Serializable {
    private int driverId;
    private int routeId;

}
