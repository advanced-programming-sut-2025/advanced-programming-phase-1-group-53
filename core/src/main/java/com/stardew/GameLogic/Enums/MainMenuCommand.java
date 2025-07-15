package com.stardew.GameLogic.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommand implements Command {
    exit("^exit$"),
    logout("^logout$"),
    enterMenu("^enter menu -m (?<menu>.+?)$"),
    showCurrentMenu("^show current menu$");
    private final String pattern;

    MainMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
