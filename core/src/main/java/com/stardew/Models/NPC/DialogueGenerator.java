package com.stardew.Models.NPC;

import com.stardew.Models.Game.App;
import com.stardew.Models.Game.Game;
import com.stardew.Models.Game.Player;
import java.util.concurrent.CompletableFuture;

public class DialogueGenerator {
    private final LanguageModelAsync languageModel;

    public DialogueGenerator(LanguageModelAsync languageModel) {
        this.languageModel = languageModel;
    }

    public CompletableFuture<String> generateNPCDialogueAsync(NPC npc, Player player) {
        Game game = App.getGame();
        StringBuilder prompt = new StringBuilder();

        prompt.append("You are roleplaying as an NPC in a Stardew Valley-like game.\n")
            .append("NPC Name: ").append(npc.getName()).append("\n")
            .append("Personality: ").append(npc.getPersonality()).append("\n")
            .append("Season: ").append(game.dateAndTime.getSeason()).append("\n")
            .append("Weather: ").append(game.weather.getWeather().toString()).append("\n")
            .append("Player name: ").append(player.personalInfo.getNickname()).append("\n")
            .append("Dialogue history: ").append(player.getNPCDialogueHistory(npc)).append("\n")
            .append("Task: Generate a short, natural dialogue line the NPC would say to the player under these conditions. ")
            .append("It should match the personality and mood, and it should not repeat previous lines exactly.");

        return languageModel.generateResponseAsync(prompt.toString());
    }
}
