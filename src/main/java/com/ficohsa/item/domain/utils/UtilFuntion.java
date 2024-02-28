package com.ficohsa.item.domain.utils;

import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.config.exception.SPError;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class UtilFuntion {



    public static LocalDate transformStringToLocalDate(String date){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        }catch (DateTimeParseException dateTimeParseException){
            throw new CustomException(SPError.INVALID_DATE_FORMAT.getErrorCode(),SPError.INVALID_DATE_FORMAT.getErrorMessage());
        }
    }


    public static LocalDateTime transformDateToLocalDateTime(Date time){
        Instant instant = time.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        return localDateTime;
    }

    public static Date transformLocalDateTimeToDate(LocalDateTime time){
        ZonedDateTime zonedDateTime = time.atZone(ZoneId.systemDefault());
        return  Date.from(zonedDateTime.toInstant());
    }
}
