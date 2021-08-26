/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crasdefault.Slack;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author yurih
 */
public class Slack {
    private static HttpClient client  = HttpClient.newHttpClient();
    private static final String URL = "https://hooks.slack.com/services/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";

public static void EnviarMensagem(String teste)  {
    JSONObject json = new JSONObject();
    json.put("text",teste);

    HttpRequest request = HttpRequest.newBuilder(
            URI.create(URL))
            .header("accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
            .build();

        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        }catch (Exception e){
            e.printStackTrace();
        }




}


}
