package com.alura.aluraAPI.services.calculates;

import com.alura.aluraAPI.models.person.Signature;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
@Component
public class CalculateTimeSignatureStrategy implements ICalculable<Signature>{
    @Override
    public void calculateTime(Signature signature) {
        Date initialDate = signature.getInitialDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initialDate);
        calendar.add(Calendar.YEAR,1);
        signature.setFinalDate(calendar.getTime());
    }
}
