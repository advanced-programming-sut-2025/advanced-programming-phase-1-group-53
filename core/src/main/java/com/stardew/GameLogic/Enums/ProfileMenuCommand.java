package com.stardew.GameLogic.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommand implements Command {
    exit("^exit$"),
    changePassword("^change password$"),
    showCurrentMenu("^show current menu$"),
    enterMenu("^enter menu -m (?<menu>.+?)$"),
    changeUsername("^change username -u (?<username>.+?)$"),
    changeEmail("^change email -e (?<email>.+?)$"),
    changeNickname("^change nickname -n (?<nickname>.+?)$"),
    showInfo("^show info$"),;
    private final String pattern;

    ProfileMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
