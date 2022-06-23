package entity;

import javax.persistence.*;

@Entity
@Table(name = "detailroute")
public class DetailRoute {
    @Id
    private int id;
    @OneToOne
    @Column(name = "route_id")
    private Route route;
    protected int quantity;

    public DetailRoute() {
    }

    public DetailRoute(Route route, int quantity) {
        this.route = route;
        this.quantity = quantity;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "route=" + route +
                ", quantity=" + quantity +
                '}';
    }
}
