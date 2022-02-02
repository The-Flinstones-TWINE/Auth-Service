package com.twine.AuthService.controller;


import com.twine.AuthService.entities.UserDetails;
import com.twine.AuthService.service.UserDetailsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/twine-auth")
@CrossOrigin("*")
@Api(tags = "Users By type and email")
public class UserDetailsController {
    @Autowired
    private UserDetailsService userDetailsService;

    //-------------------------------Authenticate User by email------------------------------
    @GetMapping(value = "/user/{email}/{password}")
    public Boolean authenticateUserByEmail(@PathVariable("email") String email, @PathVariable("password") String password) {

        return userDetailsService.authenticatedUserByEmail(email, password);
    }

    //-----------------------------Create New User---------------------------------------
    @PostMapping("/user")
    public UserDetails createUser(@Valid @RequestBody UserDetails user) {

        return userDetailsService.createUser(user);
    }

    //------------------------------Create All User------------------------------------------
    @PostMapping("/users")
    List<UserDetails> createUsers(@RequestBody List<UserDetails> user){

        return userDetailsService.createUsers(user);
    }

    //-------------------------------Get All Users--------------------------------------------
    @GetMapping(value = "/users")
    public List<UserDetails> getAllUsers() {

        return userDetailsService.getAllUsers();
    }

    //-------------------------------Get User by email--------------------------------------------
    @GetMapping(value = "/user/{email}")
    public UserDetails getUserByEmail(@PathVariable("email") String email) {

        return userDetailsService.getUserByEmail(email);
    }

    //-------------------------------Update User By Email----------------------------------------
    @PutMapping(value = "/user/{email}")
    public UserDetails updateUserByEmail(@PathVariable("email") String email, @RequestBody UserDetails user) {

        return userDetailsService.updateUserByEmail(email, user);
    }

    //---------------------------------Delete User By Email---------------------------------------
    @DeleteMapping(value = "/user/{email}")
    public String deleteUserById(@PathVariable("email") String email) {

        userDetailsService.deleteUserByEmail(email);
        return "User with email " + email + " has been deleted!";
    }
}

