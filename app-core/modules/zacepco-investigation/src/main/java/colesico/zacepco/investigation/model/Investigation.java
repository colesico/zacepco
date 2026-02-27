package colesico.zacepco.investigation.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;

import java.util.Date;

@Record(table = "investigations")
public class Investigation {

    /**
     * Investigation id
     */
    @Column
    public Long id;

    @Column
    public InvestigationStatus status;

    /**
     * Investigation script
     */
    @Column
    public Long scriptId;

    public Date createdAt;

    /**
     * Current resource amount
     */
    public Integer resource;

    /**
     * Investigation owner user
     */
    public String owner;
}
