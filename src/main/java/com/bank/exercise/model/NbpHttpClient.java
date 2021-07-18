package com.bank.exercise.model;

import com.bank.exercise.dto.CurrencyDTO;
import com.bank.exercise.exceptions.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class NbpHttpClient {
    private static final String NBP_API_URI = "http://api.nbp.pl/api/exchangerates/rates/c/%s/today/";
//    private static final String Test = "http://api.nbp.pl/api/exchangerates/rates/c/%s/today/";

    private final HttpClient httpClient;


    public NbpHttpClient() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    public CurrencyDTO getNbpRates(String currency) throws BadRequestException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(String.format(NBP_API_URI, currency)))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return deserialize(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
           throw new BadRequestException();
        }
    }

    private CurrencyDTO deserialize(String body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(body, CurrencyDTO.class);
    }
}
