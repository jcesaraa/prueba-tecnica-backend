package org.example.productservice.config;

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
        // Añadimos interceptor que inserta el API key en cada petición
        restTemplate.setInterceptors(Collections.singletonList(new ApiKeyInterceptor(apiKey)));
        return restTemplate;
    }

    static class ApiKeyInterceptor implements ClientHttpRequestInterceptor {
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

            request.getHeaders().add(API_KEY_HEADER, apiKey);

            return execution.execute(request, body);
        }
    }
}
