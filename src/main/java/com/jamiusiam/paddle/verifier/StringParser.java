package com.jamiusiam.paddle.verifier;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class StringParser {

    public String serialize(Map<String, String> value) {
        StringBuilder returnString = new StringBuilder();

        returnString.append("a:").append(value.size()).append(":{");

        value.forEach((key, val) -> {
            // Escape urls
            String valEscaped = URLDecoder.decode(val, StandardCharsets.UTF_8);

            returnString.append(generateInnerValues(key));
            returnString.append(generateInnerValues(valEscaped));
        });

        returnString.append("}");

        return returnString.toString();
    }


    private String generateInnerValues(String val) {
        return String.format("s:%d:\"%s\";", val.length(), val);
    }
}
