package zharkov.projects.engine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zharkov.projects.engine.services.PublicationService;
import zharkov.projects.model.frontend.AuthenticationContainer;
import zharkov.projects.model.frontend.Publication;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class PublicationController {
    private static final PublicationService service = new PublicationService();

    @GetMapping("/publications")
    public ResponseEntity<Collection<Publication>> getAllPublications(@RequestHeader("Login") String login,
                                                                      @RequestHeader("Password") String password) {
        return new ResponseEntity<>(service.getPublicationsByVisibility(
                new AuthenticationContainer(login, password),true),
                HttpStatus.OK
        );
    }

    /*@GetMapping("/drafts")
    public ResponseEntity<Collection<Publication>> getAllDrafts(@RequestHeader("Login") String login,
                                                                @RequestHeader("Password") String password) {
        return new ResponseEntity<>(service.getPublicationsByVisibility(
                new AuthenticationContainer(login, password),false),
                HttpStatus.OK
        );
    }*/

    @GetMapping("/user/{id}/publications")
    public ResponseEntity<Collection<Publication>> getPublicationsByUserId(@RequestHeader("Login") String login,
                                                                           @RequestHeader("Password") String password,
                                                                           @PathVariable("id") int id) {
        Collection<Publication> publications = service.getPublicationsByUserId(
                new AuthenticationContainer(login, password), id
        );
        if (publications == null) {
            return new ResponseEntity<>((Collection<Publication>) null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @GetMapping("/user/publications")
    public ResponseEntity<Collection<Publication>> getPublicationsOfCurrentUser(@RequestHeader("Login") String login,
                                                                           @RequestHeader("Password") String password) {
        Collection<Publication> publications = service.getPublicationsOfCurrentUser(
                new AuthenticationContainer(login, password)
        );
        if (publications == null) {
            return new ResponseEntity<>((Collection<Publication>) null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }
}
