package ru.endpoint.controller;

import ru.endpoint.entity.User;
import ru.endpoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/fillUsersTableFromFile")
    public String fillUsersTableFromFile() {
        String csvFilePath = "src/main/resources/TestCSV.csv";
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                User user = new User();
                user.setName(values[0]);
                user.setAge(Integer.parseInt(values[1]));
                user.setCity(values[2]);
                users.add(user);
            }
            userRepository.saveAll(users);
        } catch (IOException e) {
            return "Error reading CSV file: " + e.getMessage();
        }

        return "CSV data imported successfully";
    }

    @PostMapping("/fillUsersTableFromJSON")
    public String fillUsersTableFromJSON(@RequestBody List<User> users) {
        userRepository.saveAll(users);
        return "JSON data imported successfully";
    }
}
