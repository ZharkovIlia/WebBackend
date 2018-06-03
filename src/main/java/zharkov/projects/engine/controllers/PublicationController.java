package zharkov.projects.engine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zharkov.projects.engine.services.PublicationService;
import zharkov.projects.model.frontend.Publication;

import java.util.Collection;

@RestController
@RequestMapping("/backend")
public class PublicationController {
    private static PublicationService service = new PublicationService();

    @GetMapping("/publications")
    public ResponseEntity<Collection<Publication>> getAllPublications() {
        return new ResponseEntity<>(service.getPublicationsByVisibility(true), HttpStatus.OK);
    }

    @GetMapping("/drafts")
    public ResponseEntity<Collection<Publication>> getAllDrafts() {
        return new ResponseEntity<>(service.getPublicationsByVisibility(false), HttpStatus.OK);
    }
}
