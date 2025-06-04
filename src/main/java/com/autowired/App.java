package com.autowired;

import com.github.javafaker.Faker;
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
            generateRandomStudents(studentRepository);
            System.out.println(studentRepository.count());
            System.out.println("all student names");
            studentRepository.findAll().forEach(student1 -> {
                System.out.println(student1.getFirstName());
            });
        };
    }

    private static void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i=0;i<=100;i++){
            String firstName= faker.name().firstName();
            String lastName= faker.name().lastName();
            String email= """
                    %s.%s@autowired.com
                    """.formatted(firstName,lastName);
            Student student=new Student(
                    null,
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17,55)
            );
            studentRepository.save(student);

        }
    }

}
