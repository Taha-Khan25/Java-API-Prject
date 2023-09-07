package com.example.Java.Controller;


import com.example.Java.Services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    CreateUserService createUserService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    DeleteUserService deleteUserService;

    @Autowired
    UpdateUserService updateUserService;

    @Autowired
    ListCustomerService listCustomerService;

    @GetMapping("/createUser")
    public String CreateUser(String first_name, String last_name, String street, String address, String city, String state, String email, String phone) throws ParseException {
        String token = authenticationService.getToken();
        return createUserService.createUser(token, first_name, last_name, street, address, city, state, email, phone);
    }
    @GetMapping("/DeleteUser")
    public String DeleteUser(@RequestParam String uuid) throws ParseException {
        String token = authenticationService.getToken();
        return deleteUserService.deleteUser(uuid, token);
    }
    @GetMapping("/Update")
    public String UpdateUser(String uuid, String first_name, String last_name, String street, String address, String city, String state, String email,
            String phone) throws ParseException {
        String token = authenticationService.getToken();
        return updateUserService.updateUser(token, uuid, first_name, last_name, street, address, city, state
                , email, phone);
    }
    @GetMapping("/list-users")
    public JSONArray listUsers() throws ParseException, JsonProcessingException {

        String bearerToken = authenticationService.getToken();


        if (bearerToken != null) {
            JSONArray result = listCustomerService.listUser(bearerToken);
            return result;
        } else {

            return null; //ResponseEntity.status(401).body("Authentication Failed");

        }
    }
}
