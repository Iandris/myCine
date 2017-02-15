package com.mtyoung.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Convert from sqlDate to LocalDate. This will be used by Hibernate.
 * Class based on: http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/
 *
 * @author Mike Young
 */
@Converter
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDate) {
        return (locDate == null ? null : Timestamp.valueOf(locDate));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDateTime());
    }
}
