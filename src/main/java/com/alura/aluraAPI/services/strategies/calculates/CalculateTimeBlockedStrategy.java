package com.alura.aluraAPI.services.strategies.calculates;

import com.alura.aluraAPI.models.warn.Blocked;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Component
public class CalculateTimeBlockedStrategy implements ICalculable<Blocked> {
    @Override
    public void calculateTime(Blocked blocked) {
        Date initialDate = Date.from(Instant.now());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initialDate);
        calendar.add(Calendar.HOUR_OF_DAY, blocked.getTimeInHours());
        blocked.setExpirationDate(calendar.getTime());
    }
}
