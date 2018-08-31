package com.example.RESTfulServices.configurations;

import com.example.RESTfulServices.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestTemplateConfiguration extends Utils {
    OkHttpClient clientHTTP1 = new OkHttpClient.Builder()
            .protocols(Arrays.asList(Protocol.HTTP_1_1))
            .build();
    /**
     *
     * H2_PRIOR_KNOWLEDGE -> h2c
     * The prior_knowledge => no upgrade negotiation needed
     *                     => using this means we are sure that the other client is using HTTP/2
     *
     * */
    OkHttpClient clientHTTP2 = new OkHttpClient.Builder()
            .protocols(Arrays.asList(Protocol.H2_PRIOR_KNOWLEDGE)) //it's h2c
            .build();

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory(clientHTTP2));
    }
}
