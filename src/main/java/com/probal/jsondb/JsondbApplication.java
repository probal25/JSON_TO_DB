package com.probal.jsondb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.probal.jsondb.domain.User;
import com.probal.jsondb.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class JsondbApplication {

     public static void main(String[] args) {
        SpringApplication.run(JsondbApplication.class, args);
    }


    @Bean
    CommandLineRunner runner (UserService userService) {
        return args -> {
            // Read json data and write to db
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");

            try {
                List<User> users = objectMapper.readValue(inputStream, typeReference);
                userService.saveAll(users);
                System.out.println("User saved!!!!");
            }
            catch (IOException e) {
                System.out.println("Unable to save users : " + e.getMessage());
            }
        };
    }
}
