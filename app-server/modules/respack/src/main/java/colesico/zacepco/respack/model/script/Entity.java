package colesico.zacepco.respack.model.script;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.Objects;

/**
 * Abstract entity model.
 * This class must be extended by specific entities.
 */
public abstract class Entity<ID extends EntityId> {

    /**
     * Entity unique ID
     */
    @YamlComment(text = "Unique within script ID")
    public ID id;

    /**
     * Detailed description.
     */
    @YamlComment(text = {
            "Taget asset"
    })
    public AssetReference asset;

    @YamlComment(text = "Entity reference")
    public AssetReference reference;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public AssetReference getAsset() {
        return asset;
    }

    public void setAsset(AssetReference asset) {
        this.asset = asset;
    }

    public AssetReference getReference() {
        return reference;
    }

    public void setReference(AssetReference reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
