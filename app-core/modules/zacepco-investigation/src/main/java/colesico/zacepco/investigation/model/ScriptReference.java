package colesico.zacepco.investigation.model;

import colesico.framework.jdbirec.Column;
import colesico.framework.jdbirec.Record;

import java.util.Date;

@Record(table = "scripts")
public class ScriptReference {

    @Column
    private Long id;

    @Column
    private String uuid;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String[] authors;

    /**
     * Reference creation date
     */
    @Column
    private Date createdAt;

    /**
     * Reference owner
     */
    @Column
    private String owner;
}
