package com.example.Java.Controller;

import com.example.Java.Services.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    UserController usercontroller;


    @GetMapping("/userslist")
    public String displayUsers(Model model) throws ParseException, JsonProcessingException {
        JSONArray result= usercontroller.listUsers();
            model.addAttribute("empList", result);
            return "List";
        }

    @GetMapping("/login")
    public String LoginPage(){
        return "Login";
    }

    @GetMapping("/createPage")
    public String CreatePage(){
        return "CreateUser";
    }

    @GetMapping("/UpdatePage/{id}")
    public String UpdatePage(@PathVariable String id,Model model){
        model.addAttribute("uuid",id);
        return "UpdateUser";
    }

    @PostMapping("/submit")
    public String handleButtonClick(@RequestParam String id) throws ParseException {
        // Process the ID here
        System.out.println(id);
        usercontroller.DeleteUser(id);
        return "redirect:/userslist";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) throws ParseException {
        // Process the ID here
        System.out.println(id);
        usercontroller.DeleteUser(id);
        return "redirect:/userslist";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String first_name,
                             @RequestParam String last_name,
                             @RequestParam String street,
                             @RequestParam String address,
                             @RequestParam String city,
                             @RequestParam String state,
                             @RequestParam String phone,
                             @RequestParam String email) throws ParseException {
        usercontroller.CreateUser(first_name,last_name,street,address,city,state,email,phone);
        return "redirect:userslist";
    }

    @PostMapping("/update/{id}")
    public String UpdateUser(@PathVariable String id,@RequestParam String first_name,
                             @RequestParam String last_name,
                             @RequestParam String street,
                             @RequestParam String address,
                             @RequestParam String city,
                             @RequestParam String state,
                             @RequestParam String phone,
                             @RequestParam String email) throws ParseException {
        usercontroller.UpdateUser(id,first_name,last_name,street,address,city,state,email,phone);
        return "redirect:/userslist";
    }


}

