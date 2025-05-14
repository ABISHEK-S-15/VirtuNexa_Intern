package adventure;

import java.util.*;

class StoryNode {
    public String id;
    public String description;
    public Map<Integer, Choice> choices;

    public StoryNode(String id, String description) {
        this.id = id;
        this.description = description;
        this.choices = new LinkedHashMap<>();
    }

    public void addChoice(int option, String text, String nextNodeId) {
        choices.put(option, new Choice(text, nextNodeId));
    }
}