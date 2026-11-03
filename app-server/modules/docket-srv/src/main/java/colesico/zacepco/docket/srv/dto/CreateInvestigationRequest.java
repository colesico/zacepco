package colesico.zacepco.docket.srv.dto;

public class CreateInvestigationRequest {
    public Long scriptEntryId;
    public Integer detectivesNum;
    public Double randFactor = 1.0;
    public Double budgetScale = 1.0;

    public Long getScriptEntryId() {
        return scriptEntryId;
    }

    public void setScriptEntryId(Long scriptEntryId) {
        this.scriptEntryId = scriptEntryId;
    }

    public Integer getDetectivesNum() {
        return detectivesNum;
    }

    public void setDetectivesNum(Integer detectivesNum) {
        this.detectivesNum = detectivesNum;
    }

    public Double getRandFactor() {
        return randFactor;
    }

    public void setRandFactor(Double randFactor) {
        this.randFactor = randFactor;
    }

    public Double getBudgetScale() {
        return budgetScale;
    }

    public void setBudgetScale(Double budgetScale) {
        this.budgetScale = budgetScale;
    }
}
