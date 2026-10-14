package colesico.zacepco.investigation.srv.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;

import java.util.Date;

/**
 * Investigation process model
 */
@Record(table = "investigations")
public class InvestigationProcess {

    /**
     * Process id
     */
    @Column
    private Long id;

    /**
     * User (owner) who created case and can manage it
     */
    @Column
    private Long userid;

    @Column
    private InvestigationStatus status;

    /**
     * Current amount of resource for investigation
     */
    @Column
    private Integer resource;

    /**
     * Script ref
     *
     * @see Casebook
     */
    @Column
    private Long scriptId;

    /**
     * When case entity was created
     */
    @Column
    private Date created;


}
