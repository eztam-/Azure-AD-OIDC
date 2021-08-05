package com.birschl.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@RestController
public class HelloController {

    @Autowired
    private WebClient webClient;


    /**
     * Access to protected data from Webapp to WebApiA through client credential flow. The access token is obtained by webclient, or
     * <p>@RegisteredOAuth2AuthorizedClient("webApiA")</p>. In the end, these two approaches will be executed to
     * DefaultOAuth2AuthorizedClientManager#authorize method, get the access token.
     *
     * @return Respond to protected data from WebApi A.
     */
    @GetMapping("/")
    public String callWebApiA() {
        String body = webClient
                .get()
                .uri("http://localhost:8081/B")
                .attributes(clientRegistrationId("webApiA"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("Call callWebApiA(), request '/webApiA/sample' returned: {%s}".formatted(body));
        return "Request '/webApiA/sample'(WebApi A) returned a " + (body != null ? "success." : "failure.");
    }

}
