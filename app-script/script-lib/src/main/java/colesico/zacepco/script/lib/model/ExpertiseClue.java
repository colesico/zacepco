package colesico.zacepco.script.lib.model;

public class ExpertiseClue {

   /**
    * Clues  to be analysis
    * (two)
    */
   public EntityId[] clues;


   /**
    * Ancillary clues
    */
   public Significative ancillaries;

   /**
    * Threshold shift
    */
   public Integer shift;

   /**
    * Detection threshold for additional links
    */
   public Integer threshold;

   /**
    * Additional links (entities IDs) identified during the analysis
    */
   public EntityId[] associations;
}
