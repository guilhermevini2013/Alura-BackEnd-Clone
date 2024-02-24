package com.alura.aluraAPI.services.forum;

import com.alura.aluraAPI.dtos.forum.insert.ResponseDTO;
import com.alura.aluraAPI.dtos.forum.read.ResponseReadDTO;
import com.alura.aluraAPI.models.forum.Publication;
import com.alura.aluraAPI.models.forum.Response;
import com.alura.aluraAPI.models.person.Student;
import com.alura.aluraAPI.repositories.PublicationRepository;
import com.alura.aluraAPI.repositories.StudentRepository;
import com.alura.aluraAPI.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResponseService {
    private final PublicationRepository publicationRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public ResponseReadDTO insert(Long idPublication, ResponseDTO responseDTO) {
        Response entityResponse = new Response(responseDTO);
        entityResponse.setStudent(getStudent(responseDTO.idStudent()));
        Publication publication = getPublication(idPublication);
        publication.addResponse(entityResponse);
        return new ResponseReadDTO(entityResponse);
    }

    private Publication getPublication(Long idPublication) {
        return publicationRepository.findById(idPublication).orElseThrow(() -> new ResourceNotFoundException("Publication Not Found"));
    }

    private Student getStudent(Long idStudent) {
        return studentRepository.findById(idStudent).orElseThrow(() -> new ResourceNotFoundException("Student Not Found"));
    }
}
