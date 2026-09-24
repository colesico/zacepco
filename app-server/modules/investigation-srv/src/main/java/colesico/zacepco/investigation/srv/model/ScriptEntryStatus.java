package colesico.zacepco.investigation.srv.model;

public enum ScriptEntryStatus {

    /**
     * The script has been added to the catalog but has not been verified yet.
     * Visible only to the owner. The owner retains full management control.
     */
    SUBMITTED,

    /**
     * The script has been verified and is now available to all participants.
     * It becomes public property; the owner can no longer modify, delete, or manage it.
     */
    PUBLIC,

    /**
     * The script has been verified but was not approved for public access.
     * It remains private and visible only to the owner. The owner retains full management control.
     */
    PRIVATE
}