package com.devpulse.devpulse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final DeveloperRepository developerRepository;

    public DataLoader(DeveloperRepository developerRepository){
        this.developerRepository = developerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (developerRepository.count() == 0) {
            developerRepository.save(new Developer("Alice", "alice@example.com", "Java"));
            developerRepository.save(new Developer("Bob", "bob@example.com", "Python"));
            developerRepository.save(new Developer("Charlie", "charlie@example.com", "React"));
        } else {
            System.out.println("Developers already exist. Skipping insert.");
        }
    }
}
