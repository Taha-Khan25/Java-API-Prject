package com.example.Java.Services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UpdateUserService {

    private final RestTemplate restTemplate;


    public UpdateUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String updateUser(String token,
                             String uuid,
                             String first_name,
                             String last_name,
                             String street,
                             String address,
                             String city,
                             String state,
                             String email,
                             String phone) {

        HttpHeaders httpHeaders=new HttpHeaders();
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

        HttpEntity<String> requestEntity=new HttpEntity<>(requestBody,httpHeaders);


        String apiEndpoint = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid="+uuid;
        System.out.println(apiEndpoint);

        ResponseEntity<String> responseEntity=restTemplate.postForEntity(apiEndpoint,requestEntity, String.class);

        if (responseEntity.getStatusCodeValue()==200)
        {
            return "Updated";
        }
        else if(responseEntity.getStatusCodeValue()==500)
        {
            return "UUID NOT FOUND";
        }
        else {
            return "Error";
        }

    }

}

