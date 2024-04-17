package com.yongoe.exam.utils;

import cn.hutool.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPTExample {
    public static void main(String[] args) throws Exception {
        String apiKey = "YOUR_OPENAI_API_KEY"; // 将此处替换为您自己的 OpenAI API Key

        URL url = new URL("https://api.openai.com/v1/engines/davinci-codex/completions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);

        JSONObject requestBody = new JSONObject();
        requestBody.append("prompt", "User input goes here.");
        requestBody.append("max_tokens", 200);
        requestBody.append("temperature", 0.7);

        OutputStream outputStream = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write(requestBody.toString());
        writer.flush();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            System.out.println(stringBuilder.toString());
        } else {
            System.err.println("Error connecting to the server." + responseCode);
        }
    }
}