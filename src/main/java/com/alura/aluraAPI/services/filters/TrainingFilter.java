//package com.alura.aluraAPI.services.filters;
//
//import com.alura.aluraAPI.dtos.content.readOnly.TrainingReadDTO;
//import com.alura.aluraAPI.dtos.content.readOnly.TrainingSearchDTO;
//import com.alura.aluraAPI.services.filters.validation.IValidatorFilterContent;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class TrainingFilter {
//    private Set<IValidatorFilterContent<TrainingSearchDTO,TrainingReadDTO>> filterContents;
//
//    public TrainingFilter(Set<IValidatorFilterContent<TrainingSearchDTO, TrainingReadDTO>> filterContents) {
//        this.filterContents = filterContents;
//    }
//
//    public List<TrainingReadDTO> filter(TrainingSearchDTO dto){
//        Set<TrainingReadDTO> listFilter = new HashSet<>();
//        filterContents.forEach(validator -> validator.validate(dto,listFilter));
//        return listFilter.stream().toList();
//    }
//}
