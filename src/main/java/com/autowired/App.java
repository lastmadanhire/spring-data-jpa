package com.autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){

        return args -> {
            Student student = new Student(
                    null,
                    "Last",
                    "Madanhire",
                    "lastmadanhire557@gmail.com",
                    32
            );
            studentRepository.save(student);
            System.out.println(studentRepository.count());
            System.out.println(studentRepository.findAll());
            System.out.println(studentRepository.findById(1L));


        };
    }

}
