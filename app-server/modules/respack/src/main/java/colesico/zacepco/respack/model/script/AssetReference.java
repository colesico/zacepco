package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

public class AssetReference {

    @YamlComment(text = {
            "Referenced assets UAN (target assets)",
            "Must be mentioned in 'assets' section"
    })
    public String uan;

    @YamlComment(text = "Referenced asset id (target asset)")
    public String id;

    public String getUan() {
        return uan;
    }

    public void setUan(String uan) {
        this.uan = uan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AssetReference{" +
                "uan='" + uan + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
