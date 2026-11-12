package colesico.zacepco.docket.srv.model;

/**
 * Defines the availability and access restrictions for script.
 */
public enum ScriptStatus {

    /**
     * Active, available to all players  for free.
     */
    PUBLIC,

    /**
     * Active,  requires an access key to start investigation.
     */
    RESTRICTED,

    /**
     * Not active, no possible to start investigation
     */
    ARCHIVED
}