package com.mtyoung.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

/**
 * Convert from sqlDate to LocalDate. This will be used by Hibernate.
 * Class based on: http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/
 *
 * @author pwaite
 */
@Converter
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }

    public LocalDate convertFromString(String date) throws ParseException {
        if (date.equals("N/A")) {
            return LocalDate.of(1990, 01,01);
        } else {
            String[] parts = date.split(" ");
            String days = parts[0];
            String month = parts[1];
            String year = parts[2];
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            Date now = new java.sql.Date(formatter.parse(days + "-" + month + "-" + year).getTime());
            LocalDate dt = convertToEntityAttribute(now);
            return dt;
        }
    }

}