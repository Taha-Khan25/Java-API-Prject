package com.example.Java.Services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    private final RestTemplate restTemplate;

    public AuthenticationService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public String authenticate( String loginId, String password) throws ParseException {
        // Define the API endpoint and request body
        String apiEndpoint = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{" +
                "\"login_id\":\"" + loginId + "\",\"password\":\"" + password + "\"" +
                "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiEndpoint, requestEntity, String.class);

        if (responseEntity.getStatusCodeValue() == 200) {
            return "Success";
        }
        else {
            return "Failed";
        }
    }

    public String getToken() throws ParseException {
        // Define the API endpoint and request body
        String apiEndpoint = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        String loginId = "test@sunbasedata.com";
        String password = "Test@123";

        // Create headers with content type application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request body
        String requestBody = "{" +
                "\"login_id\":\"" + loginId + "\",\"password\":\"" + password + "\"" +
                "}";

        // Create the HttpEntity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiEndpoint, requestEntity, String.class);

        if (responseEntity.getStatusCodeValue() == 200) {
            String responseBody = responseEntity.getBody();
            String bearerToken = parseBearerToken(responseBody);
            return bearerToken;
        } else {
            return null;
        }
    }

    private String parseBearerToken(String responseBody) throws ParseException {
       JSONObject jsonObject= (JSONObject) new JSONParser().parse(responseBody);
        return jsonObject.get("access_token").toString();
    }
}

