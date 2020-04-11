package com.jamiusiam.paddle.verifier;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StringParserTest {

    @Test
    void serialize() {
        StringParser stringParser = new StringParser();

        HashMap<String, String> map = new HashMap<>();
        assertNotEquals(0, stringParser.serialize(map));
        assertTrue(stringParser.serialize(map).contains("a:0"));

        map.put("name", "Jamius");
        map.put("user_id", "20");
        assertTrue(stringParser.serialize(map).contains("a:2"));
    }
}