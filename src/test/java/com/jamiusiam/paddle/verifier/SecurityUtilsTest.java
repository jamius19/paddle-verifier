package com.jamiusiam.paddle.verifier;

import org.bouncycastle.util.encoders.Base64;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUtilsTest {

    static String pSignature;
    static String publicKeyString;
    static String expectedArrayStringPSignature;
    static SecurityUtils securityUtils;

    @BeforeAll
    public static void SetupAll() {
        pSignature = "iUSu0ED1YWQaIP7e2%2BEgUErKeK4NG8JCvuOD65sxmA6QOMDq76GtCKwjZX2ohMCDT%2FfWt8goIcz7WkeYj032woMlFUB%2FUyr2Y%2FA1ZBlhLCqcMpprXAGPD%2BavzkXbf%2Bqe2xfsLgTKZ%2F%2FPYmAZHKFFjNWAyHE1bZ73wt5N35gtJEr2imcSw9hvTaBXJ4LZ8fQRRbnLNOsWJd7G4RPUnBWJ8T0%2BstxTf7B0IjTWcTZvF%2BWapfjTkL%2FaxjZ32yusEYdJDKI6o6iKTfRgR%2BsW2jGtC16r3EWNJXDnuGr%2FVcX8%2F0xzlwg8C%2FDemAiJ39sXAiBMXLHaS3tatoOnSS0yvBu%2FsK3SUOtga89ANx7%2FoGPrhiPNh%2Fcfi4uzWkLmLuTyCiXKzBalIcnkWUzt4GUnkSmJLsQO57uSLDlXnBaDEUOHTdcxP4J1xMiQpfQP0my0Owq4C3E3YBSxcvTLDmMVcwIl1ogj2W2yjcY7kcpZMkqXA7gWti3X7AyQtRQh82iiOIpoKlK4%2BH%2BP8yYtF0OzSkiYLbsiNGlflDz6%2FxUkX0xNqfT%2BGg0ByJ0lIsD1tA1RqV%2Fp8NZQaXv8xHoy7E8lJcfCKa6XDgWheIVq%2BUs4qHAuZyRAm592LSyWJon%2Ff1rRbY7DiCrR75pS5s6T3HmLDqpwybv42G98MmPJI8hoBG46QaE%3D";
        expectedArrayStringPSignature = "[-119, 68, -82, -48, 64, -11, 97, 100, 26, 32, -2, -34, -37, -31, 32, 80, 74, -54, 120, -82, 13, 27, -62, 66, -66, -29, -125, -21, -101, 49, -104, 14, -112, 56, -64, -22, -17, -95, -83, 8, -84, 35, 101, 125, -88, -124, -64, -125, 79, -9, -42, -73, -56, 40, 33, -52, -5, 90, 71, -104, -113, 77, -10, -62, -125, 37, 21, 64, 127, 83, 42, -10, 99, -16, 53, 100, 25, 97, 44, 42, -100, 50, -102, 107, 92, 1, -113, 15, -26, -81, -50, 69, -37, 127, -22, -98, -37, 23, -20, 46, 4, -54, 103, -1, -49, 98, 96, 25, 28, -95, 69, -116, -43, -128, -56, 113, 53, 109, -98, -9, -62, -34, 77, -33, -104, 45, 36, 74, -10, -118, 103, 18, -61, -40, 111, 77, -96, 87, 39, -126, -39, -15, -12, 17, 69, -71, -53, 52, -21, 22, 37, -34, -58, -31, 19, -44, -100, 21, -119, -15, 61, 62, -78, -36, 83, 127, -80, 116, 34, 52, -42, 113, 54, 111, 23, -27, -102, -91, -8, -45, -112, -65, -38, -58, 54, 119, -37, 43, -84, 17, -121, 73, 12, -94, 58, -93, -88, -118, 77, -12, 96, 71, -21, 22, -38, 49, -83, 11, 94, -85, -36, 69, -115, 37, 112, -25, -72, 106, -1, 85, -59, -4, -1, 76, 115, -105, 8, 60, 11, -16, -34, -104, 8, -119, -33, -37, 23, 2, 32, 76, 92, -79, -38, 75, 123, 90, -74, -125, -89, 73, 45, 50, -68, 27, -65, -80, -83, -46, 80, -21, 96, 107, -49, 64, 55, 30, -1, -96, 99, -21, -122, 35, -51, -121, -9, 31, -117, -117, -77, 90, 66, -26, 46, -28, -14, 10, 37, -54, -52, 22, -91, 33, -55, -28, 89, 76, -19, -32, 101, 39, -111, 41, -119, 46, -60, 14, -25, -69, -110, 44, 57, 87, -100, 22, -125, 17, 67, -121, 77, -41, 49, 63, -126, 117, -60, -56, -112, -91, -12, 15, -46, 108, -76, 59, 10, -72, 11, 113, 55, 96, 20, -79, 114, -12, -53, 14, 99, 21, 115, 2, 37, -42, -120, 35, -39, 109, -78, -115, -58, 59, -111, -54, 89, 50, 74, -105, 3, -72, 22, -74, 45, -41, -20, 12, -112, -75, 20, 33, -13, 104, -94, 56, -118, 104, 42, 82, -72, -8, 127, -113, -13, 38, 45, 23, 67, -77, 74, 72, -104, 45, -69, 34, 52, 105, 95, -108, 60, -6, -1, 21, 36, 95, 76, 77, -87, -12, -2, 26, 13, 1, -56, -99, 37, 34, -64, -11, -76, 13, 81, -87, 95, -23, -16, -42, 80, 105, 123, -4, -60, 122, 50, -20, 79, 37, 37, -57, -62, 41, -82, -105, 14, 5, -95, 120, -123, 106, -7, 75, 56, -88, 112, 46, 103, 36, 64, -101, -97, 118, 45, 44, -106, 38, -119, -1, 127, 90, -47, 109, -114, -61, -120, 42, -47, -17, -102, 82, -26, -50, -109, -36, 121, -117, 14, -86, 112, -55, -69, -8, -40, 111, 124, 50, 99, -55, 35, -56, 104, 4, 110, 58, 65, -95]";
        securityUtils = new SecurityUtils();

        // Random Public keys generated from
        // https://8gwifi.org/RSAFunctionality?rsasignverifyfunctions=rsasignverifyfunctions&keysize=2048
        publicKeyString = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoUKAP/wV37SEgq77lFRR\n" +
                "SJD4P7gEszlT7zOiRaH/QZTC4O4TMOoPrbtimAxqMkFJWW9nh4m/fRTNvtSF2XIb\n" +
                "ZXPFRiFrglqz611eFsL/W2/JwamLTq531b3P3dSwoJK9jfsJZ3RS75eBNsG7lSTG\n" +
                "d29eo3iSGGuux5UTULjSFTO36m/tuUpOI0E2/qTjisrNFdFu1DiGdChwL4iRaBPO\n" +
                "e8u07Y1+ARtAd0BMO7vEPk3tn8remjjUqJ6Lsw3vLDfRuOWysbErSiYS7PkDT5WV\n" +
                "FA5ATWRDnLMUvwD7i7UfoY9FC2MZk6bk8lkmBB/jv2dtcamOBVLZIi3htofrQdeW\n" +
                "uQIDAQAB\n" +
                "-----END PUBLIC KEY-----";
    }


    @Test
    void getPublicKey() throws InvalidKeySpecException {
        PublicKey publicKey = securityUtils.getPublicKey(publicKeyString);
        byte[] base = publicKey.getEncoded();
        byte[] original = Base64.encode(base);

        String publicKeyStringRemoved = publicKeyString
                .replace("-----BEGIN PUBLIC KEY-----\n", "")
                .replace("-----END PUBLIC KEY-----", "");

        assertEquals(publicKeyStringRemoved.replace("\n", ""),
                new String(original).replace("\n", ""));
    }

    @Test
    void getPublicKeyWithInvalidArgs() {
        String publicKeyModified = publicKeyString.replace("a", "basdasavas");

        InvalidKeySpecException exception = assertThrows(InvalidKeySpecException.class, () -> {
            PublicKey publicKey = securityUtils.getPublicKey(publicKeyModified);
        });

    }

    @Test
    void getEncodedPSignature() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("p_signature", pSignature);

        byte[] baseSign = securityUtils.getEncodedPSignature(map);
        assertEquals(expectedArrayStringPSignature, Arrays.toString(baseSign));

        // Random char replacing
        pSignature = pSignature.replace('a', 'c');
        map.replace("p_signature", pSignature);

        baseSign = securityUtils.getEncodedPSignature(map);
        assertNotEquals(expectedArrayStringPSignature, Arrays.toString(baseSign));
    }
}