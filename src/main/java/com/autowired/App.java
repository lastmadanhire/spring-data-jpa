package com.autowired;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

        };
    }

    private static void pagination(StudentRepository studentRepository) {
        Pageable pageable= PageRequest.of(0,10, Sort.Direction.ASC);
        Page<Student> page= studentRepository.findAll(pageable);
        System.out.println(page);
        System.out.println(page.getTotalElements());
    }

    private static void sort(StudentRepository studentRepository) {
        Sort sort=Sort.by("firstName").descending().
                and(Sort.by("lastName").descending());
        studentRepository.findAll(sort).forEach(student1 -> {
            System.out.println(student1.getFirstName());
        });
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
