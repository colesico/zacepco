package colesico.zacepco.script.lib.model;

import java.util.List;

public class ExpertiseClue {

   /**
    * Clues  to be analysis
    */
   public EntityId clueA;
   public EntityId clueB;

   /**
    * Ancillary clues to help discover additional clue associations
    */
   public List<EntityId>  ancillaries;

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

   public EntityId getClueA() {
      return clueA;
   }

   public void setClueA(EntityId clueA) {
      this.clueA = clueA;
   }

   public EntityId getClueB() {
      return clueB;
   }

   public void setClueB(EntityId clueB) {
      this.clueB = clueB;
   }

   public List<EntityId> getAncillaries() {
      return ancillaries;
   }

   public void setAncillaries(List<EntityId> ancillaries) {
      this.ancillaries = ancillaries;
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
