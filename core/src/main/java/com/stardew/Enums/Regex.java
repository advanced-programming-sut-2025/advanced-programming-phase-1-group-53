package com.stardew.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {
    username("^^[a-zA-Z0-9-]+$$"),
    password("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[?><,\\\"';:\\\\/|\\\\[\\\\]{}+=)(*&^%$#!]).{8,}$"),
    email("^[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)*@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(?![\\?<>\"';:\\/|}{+=)(*&^%$#!]).*$"),
    AT_LEAST_ONE_NUMBER(".*[0-9].*"),
    AT_LEAST_ONE_LOWERCASE(".*[a-z].*"),
    AT_LEAST_ONE_UPPERCASE(".*[A-Z].*"),
    AT_LEAST_ONE_SPECIAL(".*[?><,\"';:\\/|\\[\\]{}+=)(*&^%$#!].*"),
    INVALID_CHARACTERS(".*[^a-zA-Z0-9?><,\"';:\\/|\\[\\]{}+=)(*&^%$#!].*"),
    MINIMUM_LENGTH(".{8,}"),
    USERNAME_CONTAINS_INVALID_SYMBOLS(".*[^a-zA-Z0-9\\-].*"),
    EMAIL_USERNAME_START_END("^[a-zA-Z0-9](.*[a-zA-Z0-9])?@.+$"),
    NO_DOUBLE_DOTS("^(?!.*\\.\\.).*$"),
    EMAIL_USERNAME_VALID("^[a-zA-Z0-9][a-zA-Z0-9._-]*[a-zA-Z0-9]@.+$"),
    ONLY_ONE_AT_SIGN("^[^@]+@[^@]+$"),
    DOMAIN_START_END("^[^@]+@[a-zA-Z0-9].*[a-zA-Z0-9].*$"),
    DOMAIN_VALID("^[^@]+@[a-zA-Z0-9.-]+$"),
    DOMAIN_SUFFIX_MIN_LENGTH(".*\\.[a-zA-Z]{2,}$"),
    NO_INVALID_SYMBOLS("^[^?><,\"'\\[\\];:\\/|{}+=)(*&^%$#!]+$");
    private final String pattern;
    Regex(String pattern) {
        this.pattern = pattern;
    }
    public boolean regexMatcher(String input) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        return matcher.matches();
    }
}
