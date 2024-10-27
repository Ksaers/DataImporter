package ru.endpoint.controller;

import ru.endpoint.entity.Customer;
import ru.endpoint.repository.CustomerRepository;
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
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/fillCustomersTableFromFile")
    public String fillCustomersTableFromFile() {
        String csvFilePath = "src/main/resources/TestCSV.csv";
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Customer customer = new Customer();
                customer.setName(values[0]);
                customer.setAge(Integer.parseInt(values[1]));
                customer.setCity(values[2]);
                customers.add(customer);
            }
            customerRepository.saveAll(customers);
        } catch (IOException e) {
            return "Error reading CSV file: " + e.getMessage();
        }

        return "CSV data imported successfully";
    }

    @PostMapping("/fillCustomersTableFromJSON")
    public String fillCustomersTableFromJSON(@RequestBody List<Customer> customers) {
        customerRepository.saveAll(customers);
        return "JSON data imported successfully";
    }
}
