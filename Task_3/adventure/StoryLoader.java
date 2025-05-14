package adventure;

import java.util.*;

class StoryLoader {
    public static Map<String, StoryNode> loadStory() {
        Map<String, StoryNode> storyMap = new HashMap<>();

        StoryNode start = new StoryNode("start", "You awaken in the Heartwood Glade, an ancient forest glowing with ethereal light. A disoriented sprite hovers nearby.");
        start.addChoice(1, "Ask the sprite where you are", "sprite_intro");
        start.addChoice(2, "Follow the distant chanting sound", "forest_ritual");
        storyMap.put("start", start);

        StoryNode sprite_intro = new StoryNode("sprite_intro", "The sprite speaks in riddles, warning you of a realm on the brink of collapse. It tells you to find the Guardian of the Grove.");
        sprite_intro.addChoice(1, "Seek the Guardian", "grove_guardian");
        sprite_intro.addChoice(2, "Ignore the sprite and walk deeper into the woods", "deep_forest");
        storyMap.put("sprite_intro", sprite_intro);

        StoryNode forest_ritual = new StoryNode("forest_ritual", "You arrive at a grove where cloaked figures chant around a floating relic. You've stumbled upon an ancient druidic ritual.");
        forest_ritual.addChoice(1, "Step forward and ask to join", "druid_initiation");
        forest_ritual.addChoice(2, "Steal the relic and run", "cursed_escape");
        storyMap.put("forest_ritual", forest_ritual);

        StoryNode grove_guardian = new StoryNode("grove_guardian", "The Grove Guardian, a sentient tree, warns you that only those who wield wisdom, courage, and kindness may repair Aetherion.");
        grove_guardian.addChoice(1, "Accept his blessing", "blessing_given");
        grove_guardian.addChoice(2, "Try to cut him down", "forest_revenge");
        storyMap.put("grove_guardian", grove_guardian);

        StoryNode deep_forest = new StoryNode("deep_forest", "The deeper you go, the more eerie the woods become. Suddenly, a pack of wolves emerges.");
        deep_forest.addChoice(1, "Fight the wolves", "fight_wolves");
        deep_forest.addChoice(2, "Attempt to reason with the wolves", "wolf_appeal");
        storyMap.put("deep_forest", deep_forest);

        StoryNode druid_initiation = new StoryNode("druid_initiation", "The druids welcome you and offer a chance to prove your worth through an ancient trial.");
        druid_initiation.addChoice(1, "Accept the trial", "trial_of_wisdom");
        druid_initiation.addChoice(2, "Politely refuse and leave", "druid_exit");
        storyMap.put("druid_initiation", druid_initiation);

        StoryNode cursed_escape = new StoryNode("cursed_escape", "As you grab the relic, a curse burns through your veins. You're marked. The druids vanish.");
        cursed_escape.addChoice(1, "Seek a healer", "elven_healer");
        cursed_escape.addChoice(2, "Embrace the curse", "dark_path");
        storyMap.put("cursed_escape", cursed_escape);

        StoryNode blessing_given = new StoryNode("blessing_given", "The Guardian blesses you with the ability to sense the world’s fractures. Your journey begins now.");
        blessing_given.addChoice(1, "Follow the path to the kingdom", "kingdom_arrival");
        blessing_given.addChoice(2, "Take the unmarked path into the wild", "wild_adventure");
        storyMap.put("blessing_given", blessing_given);

        StoryNode forest_revenge = new StoryNode("forest_revenge", "You strike the Guardian. Vines lash out. The forest is angry.");
        forest_revenge.addChoice(1, "Beg for mercy", "mercy_request");
        forest_revenge.addChoice(2, "Break free and flee", "end_card");
        storyMap.put("forest_revenge", forest_revenge);

        StoryNode fight_wolves = new StoryNode("fight_wolves", "You battle fiercely. The alpha steps forward to challenge you.");
        fight_wolves.addChoice(1, "Strike the alpha down", "end_card");
        fight_wolves.addChoice(2, "Bow to the alpha", "end_card");
        storyMap.put("fight_wolves", fight_wolves);

        StoryNode wolf_appeal = new StoryNode("wolf_appeal", "The wolves hesitate. The alpha offers a pact.");
        wolf_appeal.addChoice(1, "Accept the pact", "end_card");
        wolf_appeal.addChoice(2, "Reject and move on", "end_card");
        storyMap.put("wolf_appeal", wolf_appeal);

        StoryNode trial_of_wisdom = new StoryNode("trial_of_wisdom", "You must see what lies beneath the surface of the reflecting pool.");
        trial_of_wisdom.addChoice(1, "Peer into the depths", "end_card");
        trial_of_wisdom.addChoice(2, "Ignore the challenge", "end_card");
        storyMap.put("trial_of_wisdom", trial_of_wisdom);

        StoryNode druid_exit = new StoryNode("druid_exit", "You leave the grove and find a fork in the road.");
        druid_exit.addChoice(1, "Follow the road", "kingdom_arrival");
        druid_exit.addChoice(2, "Enter the wilderness", "wild_adventure");
        storyMap.put("druid_exit", druid_exit);

        StoryNode elven_healer = new StoryNode("elven_healer", "The healer slows the curse but cannot remove it.");
        elven_healer.addChoice(1, "Thank the healer and leave", "end_card");
        elven_healer.addChoice(2, "Ask for more help", "end_card");
        storyMap.put("elven_healer", elven_healer);

        StoryNode dark_path = new StoryNode("dark_path", "You embrace the curse and feel power surge through you.");
        dark_path.addChoice(1, "Continue", "end_card");
        dark_path.addChoice(2, "Resist", "end_card");
        storyMap.put("dark_path", dark_path);

        StoryNode kingdom_arrival = new StoryNode("kingdom_arrival", "You arrive at the kingdom. The king offers support.");
        kingdom_arrival.addChoice(1, "Accept the king’s offer", "end_card");
        kingdom_arrival.addChoice(2, "Decline and go alone", "end_card");
        storyMap.put("kingdom_arrival", kingdom_arrival);

        StoryNode wild_adventure = new StoryNode("wild_adventure", "You venture into ancient ruins.");
        wild_adventure.addChoice(1, "Explore the ruins", "end_card");
        wild_adventure.addChoice(2, "Continue through the wild", "end_card");
        storyMap.put("wild_adventure", wild_adventure);

        StoryNode mercy_request = new StoryNode("mercy_request", "The Guardian releases you and gives a final warning.");
        mercy_request.addChoice(1, "Redeem yourself", "end_card");
        mercy_request.addChoice(2, "Leave the forest", "end_card");
        storyMap.put("mercy_request", mercy_request);

        StoryNode end_card = new StoryNode("end_card", "You have reached the end of your journey. Your choices shaped the world of Aetherion.");
        storyMap.put("end_card", end_card);

        return storyMap;
    }
}
