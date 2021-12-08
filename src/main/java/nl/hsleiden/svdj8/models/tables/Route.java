package nl.hsleiden.svdj8.models.tables;

import javax.persistence.*;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "total_time")
    private int totalTime;

    @OneToOne(targetEntity = Result.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "result_id", referencedColumnName = "result_id")
    private Result result;

    public Route(int totalTime, Result result) {
        this.totalTime = totalTime;
        this.result = result;
    }

    public Route() {

    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Long getRouteID() {
        return routeId;
    }

    public void setRouteID(Long routeId) {
        this.routeId = routeId;
    }
}
