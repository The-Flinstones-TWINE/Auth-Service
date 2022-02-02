package com.twine.AuthService.controller;

import com.twine.AuthService.service.GoogleUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.twine.AuthService.constants.AuthConstants.AUTHORIZATION_HEADER;

@RestController
    @RequestMapping("/twine-auth")
    @CrossOrigin("*")
    @Api(tags = "Google Authentication")
    public class GoogleUserController {
        @Autowired
        GoogleUserService googleUserService;
        @PostMapping("/google-auth")
        public ResponseEntity authControllerMethod(@RequestHeader HttpHeaders headers) {
            try {
                List<String> header = headers.getValuesAsList(AUTHORIZATION_HEADER);
                return googleUserService.authService(header.get(0).split(" ")[1]);
            } catch (Exception e) {
                System.out.println("Exception occured");
            }
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
