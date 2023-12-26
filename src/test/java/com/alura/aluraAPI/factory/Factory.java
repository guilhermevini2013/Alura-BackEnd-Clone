package com.alura.aluraAPI.factory;

import com.alura.aluraAPI.dtos.content.insert.CertificateDTO;
import com.alura.aluraAPI.dtos.content.insert.CourseDTO;
import com.alura.aluraAPI.models.content.Course;

import java.util.ArrayList;

public class Factory {
    public static Course createCurse(){
        return new Course();
    }
    public static CourseDTO createCurseDTO(){
        return new CourseDTO(1l,"exemple name","exemple name",new ArrayList<>(),
                new CertificateDTO(1l,"exemple",1));
    }
}
