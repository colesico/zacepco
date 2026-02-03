package colesico.zacepco.script.lib.assist;

import colesico.zacepco.script.lib.model.*;

public class ScriptTemplateBuilder {

    protected Script script;


    public Script build(){
        script = new Script();

        script.meta = new Metadata();

        script.misc = new Miscellaneous();

        script.personages = new PersonageDict();
        script.locations = new LocationDict();
        script.artifacts = new ArtifactDict();

        script.search = new SearchSpec();
        script.interrogation = new InterrogationSpec();
        script.expertise = new ExpertiseSpec();

    }
}
