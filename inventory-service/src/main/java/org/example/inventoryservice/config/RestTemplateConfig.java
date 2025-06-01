package org.example.inventoryservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    private final String apiKey;
    private static final String API_KEY_HEADER = "X-API-KEY";

    public RestTemplateConfig(@Value("${app.security.api-key}") String apiKey) {
        this.apiKey = apiKey;
    }

    @Bean
    public RestTemplate secureRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // Añadimos el interceptor que agrega el header con la API Key
        restTemplate.setInterceptors(Collections.singletonList(new ApiKeyInterceptor(apiKey)));
        return restTemplate;
    }

    private static class ApiKeyInterceptor implements ClientHttpRequestInterceptor {

        private final String apiKey;

        public ApiKeyInterceptor(String apiKey) {
            this.apiKey = apiKey;
        }

        @Override
        @NonNull
        public ClientHttpResponse intercept(
                @NonNull HttpRequest request,
                @NonNull byte[] body,
                @NonNull ClientHttpRequestExecution execution) throws IOException {

            // Agrega el header "X-API-KEY" con el valor de la apiKey
            request.getHeaders().add(API_KEY_HEADER, apiKey);
            return execution.execute(request, body);
        }
    }
}