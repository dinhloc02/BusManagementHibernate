package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@IdClass(CompositeKey.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assignment")
public class Assignment {
    @Id
    @Column(name = "route_id", nullable = false)
    int routeId;

    @Id
    @Column(name = "driver_id", nullable = false)
    int driverId;

    @Column( nullable = false)
    int quantity;


}
