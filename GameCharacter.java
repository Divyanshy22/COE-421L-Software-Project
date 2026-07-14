import java.util.LinkedHashMap;
import java.util.Map;

public class GameCharacter {

    private final String name;
    private final String defaultDialogue;
    private final Map<String, String> sceneDialogue = new LinkedHashMap<>();
    private final Map<String, String> conditionalDialogue = new LinkedHashMap<>();

    public GameCharacter(String name, String defaultDialogue) {
        this.name = name;
        this.defaultDialogue = defaultDialogue;
    }

    // fluent builder methods so CharacterFactory can chain calls
    public GameCharacter addSceneDialogue(String sceneKey, String text) {
        sceneDialogue.put(sceneKey.toLowerCase(), text);
        return this;
    }

    public GameCharacter addConditionalDialogue(String flag, String text) {
        conditionalDialogue.put(flag, text);
        return this;
    }

    public String getName() {
        return name;
    }

    // decides what this character says based on story flags first, then scene, then default
    public String getDialogue(GameState state) {
        for (Map.Entry<String, String> entry : conditionalDialogue.entrySet()) {
            if (state.didHappen(entry.getKey())) {
                return name + ": \"" + entry.getValue() + "\"";
            }
        }

        String sceneKey = state.getLocation().toLowerCase();
        if (sceneDialogue.containsKey(sceneKey)) {
            return name + ": \"" + sceneDialogue.get(sceneKey) + "\"";
        }

        return name + ": \"" + defaultDialogue + "\"";
    }
}
