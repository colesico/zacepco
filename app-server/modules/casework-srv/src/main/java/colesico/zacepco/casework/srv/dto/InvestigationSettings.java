package colesico.zacepco.casework.srv.dto;

public class InvestigationSettings {

    public Integer detectivesNum;
    public Double randFactor;

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
}
