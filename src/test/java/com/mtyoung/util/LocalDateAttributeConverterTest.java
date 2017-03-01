package com.mtyoung.util;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/15/17.
 */
public class LocalDateAttributeConverterTest {

    LocalDate ldt;
    Date dt;
    LocalDateAttributeConverter converter;

    @Before
    public void setUp() throws Exception {
        ldt = LocalDate.of(2017,2,15);
        dt = Date.valueOf("2017-02-15");
        converter = new LocalDateAttributeConverter();
    }

    @Test
    public void convertToDatabaseColumn() throws Exception {
        assertEquals("Local Date to Date conversion failed", dt, converter.convertToDatabaseColumn(ldt));
    }

    @Test
    public void convertToEntityAttribute() throws Exception {
        assertEquals("Date to Local Date conversion failed", ldt, converter.convertToEntityAttribute(dt));

    }

    @Test
    public void convertFromString() throws Exception {
        assertEquals(LocalDate.of(1999, 3, 31), converter.convertFromString("31 Mar 1999"));
    }

}