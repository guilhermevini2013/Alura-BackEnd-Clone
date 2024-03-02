package com.alura.aluraAPI.controllers.forum;

import com.alura.aluraAPI.dtos.forum.insert.PublicationDto;
import com.alura.aluraAPI.dtos.forum.insert.ResponseDTO;
import com.alura.aluraAPI.dtos.forum.read.PublicationReadDto;
import com.alura.aluraAPI.dtos.forum.read.PublicationSeachDTO;
import com.alura.aluraAPI.dtos.forum.read.ResponseReadDTO;
import com.alura.aluraAPI.models.forum.PublicationStatus;
import com.alura.aluraAPI.services.forum.PublicationsService;
import com.alura.aluraAPI.services.forum.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/forum", produces = "application/json")
@RequiredArgsConstructor
@Tag(name = "Forum", description = "Controller for Forum(Publish and Commentary)")
@SecurityRequirement(name = "bearerAuth")
public class ForumController {
    private final PublicationsService publicationsService;
    private final ResponseService responseService;

    @PostMapping(value = "/publish")
    @Operation(description = "Create new publication",
            summary = "Create new publication for Student",
            responses = {
                    @ApiResponse(description = "Create", responseCode = "201"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Validation field", responseCode = "400")
            })
    public ResponseEntity<Void> addPublish(@RequestBody @Valid PublicationDto publicationDTO, UriComponentsBuilder componentsBuilder) {
        PublicationReadDto insert = publicationsService.insert(publicationDTO);
        URI uri = componentsBuilder.path("/forum/id/{id}").buildAndExpand(insert.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/publish")
    @Operation(description = "Find all publications and return pageable",
            summary = "Find all publications",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<Page<PublicationReadDto>> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer pages,
                                                            @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPages,
                                                            @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                                                            @RequestParam(name = "orderBy", defaultValue = "publicationDate", required = false) String orderBy) {
        return ResponseEntity.ok(publicationsService.findAll(PageRequest.of(pages, linesPerPages, Sort.Direction.valueOf(direction), orderBy)));
    }

    @GetMapping(value = "/publish/filter")
    @Operation(description = "Find all publications by filter and return pageable",
            summary = "Find all publications by filter",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<Page<PublicationReadDto>> findAllByFilter(@RequestParam(name = "page", defaultValue = "0", required = false) Integer pages,
                                                                    @RequestParam(name = "linesPerPage", defaultValue = "15", required = false) Integer linesPerPages,
                                                                    @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                                                                    @RequestParam(name = "orderBy", defaultValue = "publicationDate", required = false) String orderBy,
                                                                    @RequestParam(name = "title", required = false) String title,
                                                                    @RequestParam(name = "status", defaultValue = "none", required = false) String status) {
        PublicationSeachDTO publicationSearch = new PublicationSeachDTO(title, PublicationStatus.valueOf(status.toUpperCase()));
        return ResponseEntity.ok(publicationsService.findAllByFilter(PageRequest.of(pages, linesPerPages, Sort.Direction.valueOf(direction), orderBy), publicationSearch));
    }

    @PutMapping(value = "/publish/{id}")
    @Operation(description = "Alter publication",
            summary = "Alter publication",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Publication Not Found", responseCode = "404"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Validation field", responseCode = "400")
            })
    public ResponseEntity<Void> alterPublish(@PathVariable Long id, @RequestBody @Valid PublicationDto publicationsAlterDto) {
        publicationsService.alterPublication(id, publicationsAlterDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/publish/mark")
    @Operation(description = "Mark publication as resolved",
            summary = "Mark publication as resolved",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Publication Not Found", responseCode = "404"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<Void> markAsResolved(@RequestParam(name = "idPublish") Long idPublish,
                                               @RequestParam(name = "idStudent") Long idStudent) {
        publicationsService.markAsResolved(idPublish, idStudent);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/publish")
    @Operation(description = "Deletes student's publication",
            summary = "Deletes publication",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Publication Not Found", responseCode = "404"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403")
            })
    public ResponseEntity<Void> deletePublish(@RequestParam(name = "idPublish") Long idPublish,
                                              @RequestParam(name = "idStudent") Long idStudent) {
        publicationsService.deleteById(idPublish, idStudent);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/response/{idPublication}")
    @Operation(description = "Adding commentary in publication",
            summary = "Adding commentary in publication",
            responses = {
                    @ApiResponse(description = "Create", responseCode = "201"),
                    @ApiResponse(description = "Publication Not Found", responseCode = "404"),
                    @ApiResponse(description = "Student Not Found", responseCode = "404"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Validation field", responseCode = "400")
            })
    public ResponseEntity<ResponseReadDTO> addComment(@PathVariable Long idPublication, @RequestBody @Valid ResponseDTO responseDTO, UriComponentsBuilder componentsBuilder) {
        ResponseReadDTO entityInserted = responseService.insert(idPublication, responseDTO);
        URI uri = componentsBuilder.path("/response/{id}").buildAndExpand(entityInserted.id()).toUri();
        return ResponseEntity.created(uri).body(entityInserted);
    }
}
