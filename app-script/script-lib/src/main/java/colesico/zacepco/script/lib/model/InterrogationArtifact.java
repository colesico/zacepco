package colesico.zacepco.script.lib.model;

import java.util.List;

public class InterrogationArtifact {

    /**
     * Personage id
     */
    public EntityId personage;

    /**
     * Entities on which interrogation is focused
     */
    public List<EntityId> focus;

    /**
     * Target artifacts
     */
    public List<EntityId> artifacts;

    /**
     * Bonus rate for artifact discovery
     */
    public Integer bonus;
}
