package com.stardew.Models.NPC;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class HttpLanguageModel implements LanguageModelAsync {
    private final String apiUrl;
    private final String modelName;

    public HttpLanguageModel(String apiUrl, String modelName) {
        this.apiUrl = apiUrl;
        this.modelName = modelName;
    }

    @Override
    public CompletableFuture<String> generateResponseAsync(String prompt) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                String body = String.format(
                    "{\"model\":\"%s\",\"prompt\":\"%s\"}",
                    modelName,
                    prompt.replace("\"", "\\\"")
                );

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(body.getBytes());
                }

                StringBuilder finalText = new StringBuilder();

                // Ollama stream JSON objects, we read line-by-line
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        int idx = line.indexOf("\"response\":\"");
                        if (idx != -1) {
                            int start = idx + 12;
                            int end = line.indexOf("\"", start);
                            if (end != -1) {
                                String chunk = line.substring(start, end);
                                finalText.append(chunk);
                            }
                        }
                    }
                }

                return finalText.toString().trim();

            } catch (IOException e) {
                e.printStackTrace();
                return "خطا در ارتباط با Ollama.";
            }
        });
    }
}
