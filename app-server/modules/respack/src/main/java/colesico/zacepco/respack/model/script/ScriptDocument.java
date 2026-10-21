package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.List;
import java.util.Optional;

/**
 * Script document
 */
public class ScriptDocument {

    /**
     * Meta information
     */
    @YamlComment(text = {
            "",
            "Script meta information",
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
            "Crime setting specification",
            "Describes entire crime environment",
            ""
    })
    public Setting setting;

    /**
     * Investigation spec
     */
    @YamlComment(text = {
            "",
            "Investigation specification",
            ""
    })
    public Investigation investigation;

    /**
     * General answers
     */
    @YamlComment(text = {
            "",
            "General answers",
            ""
    })
    public Answers answers;

    public ScriptDocument() {
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

    public Optional<AssetReference> canonicalReference(AssetReference ref) {
        if (ref == null) {
            return Optional.empty();
        }

        if (ref.uan == null || ref.uan.isBlank()) {
            return getDefaultReferenceUsn().map(defaultScript -> {
                var nRef = new AssetReference();
                nRef.uan = defaultScript;
                nRef.id = ref.id;
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
