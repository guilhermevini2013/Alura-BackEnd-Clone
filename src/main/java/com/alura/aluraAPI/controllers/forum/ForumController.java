package com.alura.aluraAPI.controllers.forum;

import com.alura.aluraAPI.dtos.forum.insert.PublicationDto;
import com.alura.aluraAPI.dtos.forum.insert.ResponseDTO;
import com.alura.aluraAPI.dtos.forum.read.PublicationReadDto;
import com.alura.aluraAPI.dtos.forum.read.PublicationSeachDTO;
import com.alura.aluraAPI.dtos.forum.read.ResponseReadDTO;
import com.alura.aluraAPI.models.forum.PublicationStatus;
import com.alura.aluraAPI.services.forum.PublicationsService;
import com.alura.aluraAPI.services.forum.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/forum")
@RequiredArgsConstructor
public class ForumController {
    private final PublicationsService publicationsService;
    private final ResponseService responseService;

    @PostMapping(value = "/publish")
    public ResponseEntity<Void> addPublish(@RequestBody PublicationDto publicationDTO, UriComponentsBuilder componentsBuilder) {
        PublicationReadDto insert = publicationsService.insert(publicationDTO);
        URI uri = componentsBuilder.path("/forum/id/{id}").buildAndExpand(insert.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/publish")
    public ResponseEntity<Page<PublicationReadDto>> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer pages,
                                                            @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPages,
                                                            @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                                                            @RequestParam(name = "orderBy", defaultValue = "publicationDate", required = false) String orderBy) {
        return ResponseEntity.ok(publicationsService.findAll(PageRequest.of(pages, linesPerPages, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/publish/filter")
    public ResponseEntity<Page<PublicationReadDto>> findAllByFilter(@RequestParam(name = "page", defaultValue = "0", required = false) Integer pages,
                                                                    @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPages,
                                                                    @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                                                                    @RequestParam(name = "orderBy", defaultValue = "publicationDate", required = false) String orderBy,
                                                                    @RequestParam(name = "title", required = false) String title,
                                                                    @RequestParam(name = "status",defaultValue = "none",required = false) String status) {
        PublicationSeachDTO publicationSearch = new PublicationSeachDTO(title, PublicationStatus.valueOf(status.toUpperCase()));
        return ResponseEntity.ok(publicationsService.findAllByFilter(PageRequest.of(pages, linesPerPages, Sort.Direction.valueOf(direction), orderBy), publicationSearch));
    }

    @PutMapping(value = "/publish/{id}")
    public ResponseEntity<Void> alterPublish(@PathVariable Long id, @RequestBody PublicationDto publicationsAlterDto) {
        publicationsService.alterPublication(id, publicationsAlterDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/publish/mark")
    public ResponseEntity<Void> markAsResolved(@RequestParam(name = "idPublish") Long idPublish,
                                               @RequestParam(name = "idStudent") Long idStudent) {
        publicationsService.markAsResolved(idPublish,idStudent);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/publish")
    public ResponseEntity<Void> deletePublish(@RequestParam(name = "idPublish") Long idPublish,
                                              @RequestParam(name = "idStudent") Long idStudent) {
        publicationsService.deleteById(idPublish,idStudent);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/response/{idPublication}")
    public ResponseEntity<ResponseReadDTO> addComment(@PathVariable Long idPublication, @RequestBody ResponseDTO responseDTO, UriComponentsBuilder componentsBuilder){
        ResponseReadDTO entityInserted = responseService.insert(idPublication, responseDTO);
        URI uri = componentsBuilder.path("/response/{id}").buildAndExpand(entityInserted.id()).toUri();
        return ResponseEntity.created(uri).body(entityInserted);
    }
}
