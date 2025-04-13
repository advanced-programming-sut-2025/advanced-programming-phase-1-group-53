package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommand implements Command {
    exit(""),
    showCurrentMenu(""),
    enterMenu(""),
    exitGame(""),
    newGame(""),
    loadGame(""),
    selectMap("");
    // TODO بقیه دستور های مربوط به بازی
    private final String pattern;

    GameMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
