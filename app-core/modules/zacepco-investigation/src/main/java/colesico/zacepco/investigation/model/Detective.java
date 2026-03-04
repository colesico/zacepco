package colesico.zacepco.investigation.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;

/**
 * A detective involved in the investigation
 */
@Record(table = "detectives")
public class Detective {

    @Column
    private Long id;

    /**
     * User who can play detective role
     */
    @Column
    private Long  playerId;

    /**
     * Investigation ref
     */
    @Column
    private Long investigationId;

    /**
     * Number of points awarded to a detective in an investigation
     */
    @Column
    private Integer points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getInvestigationId() {
        return investigationId;
    }

    public void setInvestigationId(Long investigationId) {
        this.investigationId = investigationId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
