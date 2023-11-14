package uel.bd.Bulbapedia.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/generation")
public class GenerationController {
    @PostMapping("/populate")
    public void populateGeneration() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri()
    }

}
