package com.example.Java.Services;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateUserService {

    private RestTemplate restTemplate;

    CreateUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String createUser(String token,String first_name ,
                             String last_name ,
                             String street ,
                             String address ,
                             String city,
                             String state ,
                             String email ,
                             String phone ) throws ParseException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + token);



        String requestBody = "{" +
                "\"first_name\":\"" + first_name + "\"," +
                "\"last_name\":\"" + last_name + "\"," +
                "\"street\":\"" + street + "\"," +
                "\"address\":\"" + address + "\"," +
                "\"city\":\"" + city + "\"," +
                "\"state\":\"" + state + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"phone\":\"" + phone + "\"" +
                "}";

        String apiEndpoint = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";

        HttpEntity<String> request = new HttpEntity<>(requestBody, httpHeaders);

        ResponseEntity<String> responseEntity;
        responseEntity = restTemplate.postForEntity(apiEndpoint, request, String.class);

        if (responseEntity.getStatusCodeValue()==201)
        {
            return "User Added";
        } else {
            return "Error";
        }
    }
}


