package com.mtyoung.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.time.*;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/15/17.
 */
public class LocalDateTimeAttributeConverterTest {
    LocalDateTime ldt;
    Timestamp tsp;
    LocalDateTimeAttributeConverter converter;

    @Before
    public void setUp() throws Exception {
        ldt = LocalDateTime.of(2017,2,15,0,0,0);
        tsp = Timestamp.valueOf("2017-02-15 00:00:00");
        converter = new LocalDateTimeAttributeConverter();
    }


    @Test
    public void convertToDatabaseColumn() throws Exception {
        assertEquals("LocalDateTime to SQLTimeStamp not successful", tsp, converter.convertToDatabaseColumn(ldt));
    }

    @Test
    public void convertToEntityAttribute() throws Exception {
        assertEquals("SQL Timestamp to LocalDateTime not successful", ldt, converter.convertToEntityAttribute(tsp));
    }

}