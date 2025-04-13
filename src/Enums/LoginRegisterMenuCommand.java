package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginRegisterMenuCommand implements Command {
    exit(""),
    enterMenu(""),
    showCurrentMenu(""),
    register(""),
    login(""),
    pickQuestion(""),
    forgetPassword("");
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
