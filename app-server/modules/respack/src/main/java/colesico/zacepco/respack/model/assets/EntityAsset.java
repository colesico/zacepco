package colesico.zacepco.respack.model.assets;

import colesico.zacepco.respack.yaml.YamlComment;

abstract public class EntityAsset extends Asset{

    /**
     * Entity short name
     */
    @YamlComment(text = "Short name")
    public String name;

    /**
     * Detailed description.
     */
    @YamlComment(text = {
            "Detailed description",
    })
    public String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
