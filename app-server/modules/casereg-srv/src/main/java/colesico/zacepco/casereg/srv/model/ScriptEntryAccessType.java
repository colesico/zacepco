package colesico.zacepco.casereg.srv.model;

/**
 * Defines the availability and access restrictions for  {@link ScriptEntry}.
 */
public enum ScriptEntryAccessType {

    /**
     * Public case. Available to all players  for free.
     */
    PUBLIC,

    /**
     * Protected case. Visible  but requires an access key.
     */
    PROTECTED,

    /**
     * Private  case. Visible and accessible only to the creator.
     */
    PRIVATE
}