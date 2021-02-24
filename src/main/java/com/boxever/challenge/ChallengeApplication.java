package com.boxever.challenge;

import com.boxever.challenge.service.GraphProviderService;
import com.boxever.challenge.service.UserInputLooper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;


@SpringBootApplication
@RequiredArgsConstructor
public class ChallengeApplication {

    private final GraphProviderService graphProviderService;
    private final UserInputLooper userInputLooper;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }

    @PostConstruct
    private void postConstruct() throws IOException {
        userInputLooper.doConsoleLoop(graphProviderService.provideGraph());
    }
}
