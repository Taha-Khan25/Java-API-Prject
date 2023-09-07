package com.example.Java.Services;

import com.example.Java.Controller.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ListCustomerService {

    ObjectMapper objectMapper=new ObjectMapper();
    private final RestTemplate restTemplate;

    public ListCustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JSONArray listUser(String token) throws ParseException, JsonProcessingException {

        String apiEndpoint = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + token);


        HttpEntity<String> requestEntity = new HttpEntity<>(null, httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(apiEndpoint,HttpMethod.GET,requestEntity , String.class);
        String responseBody=responseEntity.getBody();
        System.out.println(responseEntity.getStatusCodeValue());
        JSONArray object= (JSONArray) new JSONParser().parse(responseBody);
        return object;

    }

}
