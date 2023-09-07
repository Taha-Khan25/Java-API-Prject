package com.example.Java.Controller;


import com.example.Java.Services.AuthenticationService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
public class AuthenticatorController {

     @Autowired
     AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public String authenticateUser(@RequestParam String loginId, @RequestParam String password, Model model) throws ParseException {
        // Define the API endpoint and request bod
        RestTemplate restTemplate = new RestTemplate();
        String apiEndpoint = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{" +
                "\"login_id\":\"" + loginId + "\",\"password\":\"" + password + "\"" +
                "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiEndpoint, requestEntity, String.class);

        if (responseEntity.getStatusCodeValue() == 200) {
            return "redirect:/userslist";
        }
        if (responseEntity.getStatusCodeValue() == 500) {
            model.addAttribute("error", "Authentication failed");
            return "Login";
        }
        else {
            return "redirect:/login";
        }
    }
}