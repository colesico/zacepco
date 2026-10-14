package colesico.zacepco.cases.srv.model;

/**
 * Defines the availability and access restrictions for  {@link Casebook}.
 */
public enum CasebookAccessType {

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