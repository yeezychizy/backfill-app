package com.chizu.backfill.controller;

import com.chizu.backfill.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * @author : chizubeokwuosa
 * @Description :
 * @created : chizubeokwuosa, Sunday
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello Chizu! Welcome to Spring Boot!";
    }

    @GetMapping("/person")
    public String person(@RequestBody Person person) {
        String displayName = person.getFirstName() + " " + person.getLastName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        LocalDate dob = LocalDate.parse(person.getDob(), formatter);
        LocalDate today = LocalDate.now();
        int age = Period.between(dob, today).getYears();
        return "This person's full name is " + displayName + " and he is " + age + " years old";

    }
}
