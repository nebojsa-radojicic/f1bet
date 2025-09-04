package com.sporty.f1bet.provider.openf1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class OpenF1Config {

    @Bean
    public OpenF1Client openF1Client() {
        RestClient client = RestClient.builder().baseUrl("https://api.openf1.org/v1").build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();
        return factory.createClient(OpenF1Client.class);
    }

}