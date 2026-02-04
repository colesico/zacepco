package colesico.zacepco.script.lib.model;

import java.util.List;

public class ExpertiseClue {

   /**
    * Entities to be analysis (two or more)
    */
   public List<EntityId> clues;

   /**
    * Ancillary clues to help discover additional clue associations
    */
   public List<EntityId> focus;

   /**
    * Threshold shift
    */
   public Integer shift;

   /**
    * Additional associations detection threshold
    */
   public Integer threshold;

   /**
    * Additional clue associations - entities IDs detected during expertise
    */
   public List<EntityId> associations;

   /**
    * Bonus rate for a successful expertise
    */
   public Integer bonus;

   public List<EntityId> getClues() {
      return clues;
   }

   public void setClues(List<EntityId> clues) {
      this.clues = clues;
   }

   public List<EntityId> getFocus() {
      return focus;
   }

   public void setFocus(List<EntityId> focus) {
      this.focus = focus;
   }

   public Integer getShift() {
      return shift;
   }

   public void setShift(Integer shift) {
      this.shift = shift;
   }

   public Integer getThreshold() {
      return threshold;
   }

   public void setThreshold(Integer threshold) {
      this.threshold = threshold;
   }

   public List<EntityId> getAssociations() {
      return associations;
   }

   public void setAssociations(List<EntityId> associations) {
      this.associations = associations;
   }

   public Integer getBonus() {
      return bonus;
   }

   public void setBonus(Integer bonus) {
      this.bonus = bonus;
   }
}
