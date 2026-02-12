package colesico.zacepco.script.lib.model;

import colesico.zacepco.script.lib.model.entity.EntityId;

import java.util.List;

public class InterrogationArtifact {

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
