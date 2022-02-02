package com.twine.AuthService.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.twine.AuthService.contact.Response;
import com.twine.AuthService.entities.GoogleUserDetails;
import com.twine.AuthService.repository.GoogleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Optional;

import static com.twine.AuthService.constants.AuthConstants.*;

@Service
    public class GoogleUserService {
        @Autowired
        GoogleUserRepository googleUserRepository;
        public ResponseEntity authService(String idTokenString) throws GeneralSecurityException, IOException {

            try {
                HttpTransport transport = new NetHttpTransport();
                JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
                GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).
                        setAudience(Arrays.asList(CLIENT_ID)).build();
                GoogleIdToken idToken = verifier.verify(idTokenString);
                if (idToken != null) {

                    GoogleIdToken.Payload payload = idToken.getPayload();
                    String email = (String) payload.get("email");
                    String name = (String) payload.get("name");
                    Optional<GoogleUserDetails> role = googleUserRepository.findById(email);

                    if (role.get().getAccounType().equalsIgnoreCase(BASIC_USER)) {

                        Response response = new Response();
                        response.setName(name);
                        response.setRole(BASIC_USER);
                        response.setEmail(email);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    } else if (role.get().getAccounType().equalsIgnoreCase(SUBSCRIPTION_USER)) {
                        Response response = new Response();
                        response.setName(name);
                        response.setRole(SUBSCRIPTION_USER);
                        response.setEmail(email);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                }
                else {
                    Error error = new Error("INVALID_TOKEN");
                    return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);//invalid token
                }
            }
            catch(Exception e){
                Error error = new Error("UNAUTHORISED_USER");
                return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED); //User does not have a role

            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
