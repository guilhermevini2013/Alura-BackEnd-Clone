package com.alura.aluraAPI.factory;

import com.alura.aluraAPI.dtos.content.insert.CertificateDTO;
import com.alura.aluraAPI.dtos.content.insert.CurseDTO;
import com.alura.aluraAPI.models.content.Curse;

import java.util.ArrayList;

public class Factory {
    public static Curse createCurse(){
        return new Curse();
    }
    public static CurseDTO createCurseDTO(){
        return new CurseDTO(1l,"exemple name","exemple name",new ArrayList<>(),
                new CertificateDTO(1l,"exemple",1));
    }
}
