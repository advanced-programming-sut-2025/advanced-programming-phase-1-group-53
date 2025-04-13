package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommand implements Command {
    exit(""),
    changePassword(""),
    showCurrentMenu(""),
    enterMenu(""),
    changeUsername(""),
    changeEmail(""),
    changeNickname(""),
    showInfo("");
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
