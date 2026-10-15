package colesico.zacepco.script.model.script;

import colesico.zacepco.script.model.setting.EntityReference;
import colesico.zacepco.script.model.setting.Setting;
import colesico.zacepco.script.model.investigation.Investigation;
import colesico.zacepco.script.yaml.YamlComment;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

/**
 * Script document
 */
public class Script {

    /**
     * Meta information
     */
    @YamlComment(text = {
            "",
            "Script meta information.",
            ""
    })
    public ScriptMetainfo metainfo;

    @YamlComment(text = {
            "",
            "Imported scripts",
            "Array of USN's (Unique script names)",
            "",
    })
    public List<String> include;

    /**
     * Environment spec
     */
    @YamlComment(text = {
            "",
            "Crime setting specification.",
            "Describes entire crime environment.",
            ""
    })
    public Setting setting;

    /**
     * Investigation spec
     */
    @YamlComment(text = {
            "",
            "Investigation specification.",
            ""
    })
    public Investigation investigation;

    /**
     * General answers
     */
    @YamlComment(text = {
            "",
            "General answers.",
            ""
    })
    public Answers answers;

    public Script() {
    }

    /**
     * Gets the default reference script USN.
     *
     * @return an {@link Optional} with the first included USN, or {@link Optional#empty()} if none
     */
    public Optional<String> getDefaultReferenceUsn() {
        return Optional.ofNullable(include)
                .flatMap(list -> list.stream().findFirst());
    }

    public Optional<EntityReference> normalizedReference(EntityReference ref) {
        if (ref == null) {
            return Optional.empty();
        }

        if (ref.script == null || ref.script.isBlank()) {
            return getDefaultReferenceUsn().map(defaultScript -> {
                var nRef = new EntityReference();
                nRef.script = defaultScript;
                nRef.entity = ref.entity;
                return nRef;
            });
        }

        return Optional.of(ref);
    }

    public ScriptMetainfo getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(ScriptMetainfo metainfo) {
        this.metainfo = metainfo;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public Investigation getInvestigation() {
        return investigation;
    }

    public void setInvestigation(Investigation investigation) {
        this.investigation = investigation;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public List<String> getInclude() {
        return include;
    }

    public void setInclude(List<String> include) {
        this.include = include;
    }

    @Override
    public String toString() {
        return "Script{" +
                "meta=" + metainfo +
                '}';
    }
}
