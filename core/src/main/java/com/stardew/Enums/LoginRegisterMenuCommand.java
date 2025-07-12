package com.stardew.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginRegisterMenuCommand implements Command {
    exit("^exit$"),
    enterMenu("^enter menu -m (?<menu>.+?)$"),
    showCurrentMenu("^show current menu$"),
    login("^login -u (?<username>.+?) -p (?<password>.+?)$"),
    forgetPassword("^forget password -u (?<username>.+?)"),
    answer("^answer -a (?<answer>.+?)$");
    private final String pattern;

    LoginRegisterMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
