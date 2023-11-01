package shwendel.dicerolla.messages;

import org.bukkit.configuration.file.FileConfiguration;
import shwendel.dicerolla.DiceRolla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiceRollaMessageManager {

    private static final String MESSAGES_NOTATION = "messages";

    private static Map<DiceRollaMessage, List<String>> messages;

    static {
        reload();
    }

    public static void reload() {

        FileConfiguration config = DiceRolla.getInstance().getConfig();

        messages = new HashMap<>();

        for(DiceRollaMessage diceRollaMessage : DiceRollaMessage.values()) {

            List<String> allMessages = new ArrayList<>();

            for(String message : config.getStringList(MESSAGES_NOTATION + "." + diceRollaMessage.getPath())) {
                allMessages.add(diceRollaMessage.translateColorCodes(message));
            }

            messages.put(diceRollaMessage, allMessages);

        }

    }

    public static Map<DiceRollaMessage, List<String>> getMessages() {
        return messages;
    }

}
