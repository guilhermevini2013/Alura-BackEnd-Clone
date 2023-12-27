package com.alura.aluraAPI.services.filters;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class FilterSpecification<T> {
    public Specification<T> filterByString(String nameAttribute, String value){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(nameAttribute),"%"+value+"%");
    }
    public Specification<T> filterByDouble(String doubleAttribute, Double value){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(doubleAttribute),"%"+value+"%");
    }
    public Specification<T> filterByLocalDate(String localDateAttribute, LocalDate value){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(localDateAttribute), value);
    }
}
