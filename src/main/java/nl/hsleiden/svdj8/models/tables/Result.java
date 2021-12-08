package nl.hsleiden.svdj8.models.tables;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultID;

    @Column(name = "amount_questions")
    private int amountQuestions;

    @Column(name = "total_time")
    private Time totalTime;

    @ManyToOne(targetEntity = Grant.class)
    @JoinColumn(name = "grant_id", nullable = false, referencedColumnName = "grant_id")
    private Grant grant;

    public Result(Long resultID, int amountQuestions, Time totalTime) {
        this.resultID = resultID;
        this.amountQuestions = amountQuestions;
        this.totalTime = totalTime;
    }

    public Result() {

    }

    public void setResultID(Long resultID) {
        this.resultID = resultID;
    }

    public Long getResultID() {
        return resultID;
    }

    public int getAmountQuestions() {
        return amountQuestions;
    }

    public void setAmountQuestions(int amountQuestions) {
        this.amountQuestions = amountQuestions;
    }

    public Time getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Time totalTime) {
        this.totalTime = totalTime;
    }

    public Grant getGrant() {
        return grant;
    }

    public void setGrant(Grant grant) {
        this.grant = grant;
    }
}
