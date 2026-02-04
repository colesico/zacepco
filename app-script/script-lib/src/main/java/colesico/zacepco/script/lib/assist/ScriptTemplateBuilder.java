package colesico.zacepco.script.lib.assist;

import colesico.zacepco.script.lib.model.*;

public class ScriptTemplateBuilder {

    protected Script script;


    public Script build(){
        script = new Script();

        script.meta = new Metadata();

        script.misc = new Miscellaneous();

        script.personages = new PersonageDict();
        script.board = new Scene();
        script.artifacts = new ArtifactDict();

        script.search = new Search();
        script.interrogation = new Interrogation();
        script.expertise = new Expertise();

    }
}
