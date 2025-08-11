package com.stardew.Models.NPC;

public interface LanguageModel {
    // دریافت پرامپت و پاسخ دادن بهش
    String generateResponse(String prompt);
}
