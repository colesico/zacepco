package colesico.zacepco.respack.model.assets;

import colesico.zacepco.respack.yaml.YamlComment;

import java.util.List;

/**
 * Assets document model
 */
public class AssetsDocument {

    /**
     * Meta information
     */
    @YamlComment(text = {
            "",
            "Assets meta information",
            ""
    })

    public AssetsMetainfo metainfo;

    public List<EntityAsset> personages;

    public List<EntityAsset> locations;


}
