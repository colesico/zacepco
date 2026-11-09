package colesico.zacepco.script.model.scriptdoc;

import colesico.zacepco.script.yaml.YamlComment;
import colesico.zacepco.script.yaml.YamlCompact;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * General answers
 */
public final class AnswersSpec {

    @YamlComment(text = "Full crime fabula")
    public String fabula;

    @YamlComment(text = {
            "Personages event log for each time tick",
            "Each element of the log array corresponds to a time tick",
            "Used to verify the investigation's findings (answers)"
    })
    public Map<PersonageSpec.Id, List<PersonageEventSpec>> personageLogs = new LinkedHashMap<>();

    @YamlComment(text = {
            "Location event log for each time tick",
            "Each element of the array corresponds to a time tick",
            "Used to verify the investigation's findings as answers"
    })
    public Map<LocationSpec.Id, List<LocationEventSpec>> locationLogs = new LinkedHashMap<>();

    @YamlComment(text = {
            "False clues",
            "Used as answers to verify the investigation's findings and results"
    })
    @YamlCompact
    public List<ClueSpec.Id> falsityClues = new ArrayList<>();

    @YamlComment(text = {
            "Target entities that this clue leads to",
            "Used as answers to verify the investigation results"
    })
    @YamlCompact
    public Map<ClueSpec.Id,List<EntitySpec.UID>> clueLeads = new LinkedHashMap<>();

    @YamlComment(text = {
            "Planned crime solving strategy. ",
            " Specify field: ",
            " 'location' for search; ",
            " 'personage' for interrogation; ",
            " nothing for expertise",
            "",
            "Example for location search: ",
            "   - location: L2",
            "   focus: [T4,C2]",
            "   result: C5"
    })
    public List<StrategyItemSpec> strategy = new ArrayList<>();
}
