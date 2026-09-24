package colesico.zacepco.catalog.srv.model;

/**
 * Defines the availability and access restrictions for a detective game scenario.
 */
public enum ScriptAccessType {

    /**
     * Public scenario. Available to all players in the catalog for free.
     */
    FREE,

    /**
     * Protected scenario. Visible in the catalog but requires an activation key.
     */
    PROTECTED,

    /**
     * Private or custom scenario. Visible and accessible only to the creator.
     */
    PRIVATE
}