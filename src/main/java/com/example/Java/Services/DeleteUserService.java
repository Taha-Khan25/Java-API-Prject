package com.example.Java.Services;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class DeleteUserService {

    private final RestTemplate restTemplate;

    DeleteUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String deleteUser(String uuid,String token) {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + token);


        HttpEntity<String> requestEntity=new HttpEntity<>(null,httpHeaders);

        String apiEndpoint = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid="+uuid;
        System.out.println(apiEndpoint);


        ResponseEntity<String> responseEntity=restTemplate.postForEntity(apiEndpoint,requestEntity, String.class);

        if (responseEntity.getStatusCodeValue()==200)
        {
            return "Deleted";
        }
        else if(responseEntity.getStatusCodeValue()==400)
        {
            return "UUID NOT FOUND";
        }
        else {
            return "Error";
        }

    }

}
