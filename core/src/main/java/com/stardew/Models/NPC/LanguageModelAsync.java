package com.stardew.Models.NPC;

import java.util.concurrent.CompletableFuture;

public interface LanguageModelAsync {
    CompletableFuture<String> generateResponseAsync(String prompt);
}
