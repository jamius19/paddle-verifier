package com.jamiussiam.paddle.verifier;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 */
public class StringParser {

    /**
     * Serializes a string {@link Map} to <b>php's</b> equivalent
     * <code>serialize()</code> function.
     *
     * @param value     The {@link Map} to be serialized
     * @return          The serialized String.
     */
    public String serialize(Map<String, String> value) {
        StringBuilder serializedString = new StringBuilder();

        serializedString.append("a:").append(value.size()).append(":{");

        value.forEach((key, val) -> {
            try {
                // Escape urls
                String valEscaped = URLDecoder.decode(val, StandardCharsets.UTF_8.name());
                serializedString.append(generateInnerValues(key));
                serializedString.append(generateInnerValues(valEscaped));

            } catch (UnsupportedEncodingException e) {
                System.err.println("Invalid Charset given.");
                e.printStackTrace();
            }
        });

        serializedString.append("}");

        return serializedString.toString();
    }


    /**
     * Generates the inner values for serialization
     */
    private String generateInnerValues(String val) {
        return String.format("s:%d:\"%s\";", val.getBytes(StandardCharsets.UTF_8).length, val);
    }
}
