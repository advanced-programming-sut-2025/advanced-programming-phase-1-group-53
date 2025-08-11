package com.stardew.Models.NPC;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpLanguageModel implements LanguageModel {
    private final String apiUrl;
    private final String apiKey;

    public HttpLanguageModel(String apiUrl, String apiKey) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    @Override
    public String generateResponse(String prompt) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String body = "{\"model\":\"gpt-4\",\"messages\":[{\"role\":\"user\",\"content\":\""
                + prompt.replace("\"", "\\\"") + "\"}]}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                return response.toString(); // اینجا باید JSON رو پارس کنی تا متن خالص رو بگیری
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "خطا در ارتباط با مدل زبانی.";
        }
    }
}
