package com.devyank.auth.keycloak.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

@RequestMapping("/token")
@RestController
public class TokenController {

    //this block is a reconciliate with keycloak and spring
    @PostMapping("/")
    public ResponseEntity<String> token(@RequestBody User user) {

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formUrlEncodedData = new LinkedMultiValueMap<>();
        formUrlEncodedData.add("client_id", user.clientId);
        formUrlEncodedData.add("username", user.username);
        formUrlEncodedData.add("password", user.password);
        formUrlEncodedData.add("grant_type", user.grant_type);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(formUrlEncodedData, headers);

        var result = restTemplate.postForEntity("http://localhost:8080/realms/twitter/protocol/openid-connect/token", entity, String.class);

        return result;
    }

    public record User(String password, String clientId, String grant_type, String username) {}
}
