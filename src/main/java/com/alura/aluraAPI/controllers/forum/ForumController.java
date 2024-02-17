package com.alura.aluraAPI.controllers.forum;

import com.alura.aluraAPI.dtos.forum.insert.PublicationDto;
import com.alura.aluraAPI.services.forum.PublicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/forum")
@RequiredArgsConstructor
public class ForumController {
    private final PublicationsService publicationsService;

    @PostMapping(value = "/publish")
    public ResponseEntity<Void> publish(@RequestBody PublicationDto publicationDTO) {
        publicationsService.insert(publicationDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(publicationDTO).toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping(value = "/publish/{id}")
    public ResponseEntity<Void> publish(@PathVariable Long id) {
        publicationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
