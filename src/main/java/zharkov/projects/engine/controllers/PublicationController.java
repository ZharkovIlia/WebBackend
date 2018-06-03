package zharkov.projects.engine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zharkov.projects.engine.services.PublicationService;
import zharkov.projects.model.frontend.AuthenticationContainer;
import zharkov.projects.model.frontend.Publication;

import java.util.Collection;

@RestController
@RequestMapping("/backend")
public class PublicationController {
    private static PublicationService service = new PublicationService();

    @GetMapping("/publications")
    public ResponseEntity<Collection<Publication>> getAllPublications(@RequestHeader("Login") String login,
                                                                      @RequestHeader("Password") String password) {
        return new ResponseEntity<>(service.getPublicationsByVisibility(
                new AuthenticationContainer(login, password),true),
                HttpStatus.OK
        );
    }

    @GetMapping("/drafts")
    public ResponseEntity<Collection<Publication>> getAllDrafts(@RequestHeader("Login") String login,
                                                                @RequestHeader("Password") String password) {
        return new ResponseEntity<>(service.getPublicationsByVisibility(
                new AuthenticationContainer(login, password),false),
                HttpStatus.OK
        );
    }
}
