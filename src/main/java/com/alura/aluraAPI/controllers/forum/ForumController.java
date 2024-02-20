package com.alura.aluraAPI.controllers.forum;

import com.alura.aluraAPI.dtos.forum.insert.PublicationDto;
import com.alura.aluraAPI.dtos.forum.insert.PublicationsAlterDto;
import com.alura.aluraAPI.dtos.forum.read.PublicationReadDto;
import com.alura.aluraAPI.services.forum.PublicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping(value = "/publish")
    public ResponseEntity<Page<PublicationReadDto>> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer pages,
                                                            @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPages,
                                                            @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                                                            @RequestParam(name = "orderBy", defaultValue = "publicationDate", required = false) String orderBy) {
        return ResponseEntity.ok(publicationsService.findAll(PageRequest.of(pages, linesPerPages, Sort.Direction.valueOf(direction), orderBy)));
    }

    @PutMapping(value = "/publish/{id}")
    public ResponseEntity<Void> alterPublish(@PathVariable Long id, @RequestBody PublicationsAlterDto publicationsAlterDto) {
        publicationsService.alterPublication(id, publicationsAlterDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/publish/mark/{id}")
    public ResponseEntity<Void> markAsResolved(@PathVariable Long id) {
        publicationsService.markAsResolved(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/publish/{id}")
    public ResponseEntity<Void> deletePublish(@PathVariable Long id) {
        publicationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
