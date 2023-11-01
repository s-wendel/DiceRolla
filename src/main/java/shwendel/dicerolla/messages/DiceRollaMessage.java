package shwendel.dicerolla.messages;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum DiceRollaMessage {

    ROLL_HELP("roll_help"),
    INVALID_DICE("invalid_dice"),
    ROLL_RESULT("roll_result"),
    BROLL_RESULT("broll_result"),
    NO_PERMISSION("no_permission"),
    RELOAD_CONFIG("reload_config"),
    DICEROLLA_HELP("dicerolla_help"),
    ;

    private String path;

    DiceRollaMessage(String path) {
        this.path = path;
    }

    private static final char COLOR_CHAR = ChatColor.COLOR_CHAR;
    private static final String START_TAG = "&#";

    public String getPath() {
        return path;
    }

    public List<String> getMessages() {
        return DiceRollaMessageManager.getMessages().get(this);
    }

    // By Elementeral https://www.spigotmc.org/threads/hex-color-code-translate.449748/#post-3867804
    protected String translateColorCodes(String message) {
        final Pattern hexPattern = Pattern.compile(START_TAG + "([A-Fa-f0-9]{6})");
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
    }

}
