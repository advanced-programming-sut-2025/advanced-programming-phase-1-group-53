package com.stardew.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpMenuCommand implements Command {
    register("^register -u (?<username>.+?) -p (?<password>.+?) -c (?<passwordConfirm>.+?) -n (?<nickName>.+?) -e (?<email>.+?) -g (?<gender>.+?)$"),
    exit("^exit$"),
    enterMenu("^enter menu -m (?<menu>.+?)$"),
    showCurrentMenu("^show current menu$"),
    pickQuestion("^pick question -q (?<questionNumber>\\d+) -a (?<answer>.+?) -c (?<answerConfirm>.+?)$");
    private final String pattern;

    SignUpMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
