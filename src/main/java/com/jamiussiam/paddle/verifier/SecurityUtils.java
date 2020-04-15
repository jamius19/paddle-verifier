package com.jamiussiam.paddle.verifier;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.SortedMap;
import java.util.TreeMap;

import java.util.Base64;

public class SecurityUtils {

    /**
     * Contruts a {@link PublicKey} from a {@link String}
     *
     * @param publicKeyString   The string to be converted.
     * @return                  The constructed {@link PublicKey}
     * @throws InvalidKeySpecException  Thrown if the supplied <b><code>publicKeyString</code></b> is invalid.
     */
    public PublicKey getPublicKey(String publicKeyString) throws InvalidKeySpecException {
        publicKeyString = publicKeyString
                .replace("-----BEGIN PUBLIC KEY-----\n", "")
                .replace("-----END PUBLIC KEY-----", "");

        try {
            byte[] byteKey = Base64.getMimeDecoder().decode(publicKeyString);
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(keySpecX509);
        } catch (NoSuchAlgorithmException ignored) {
        }

        return null;
    }


    /**
     * Constructs a base64 encoded<code>byte[]</code> from the POST DATA
     * {@link TreeMap}. Expects an <b>encoded</b> signature.
     *
     * @param postData  The sorted {@link TreeMap} of POST data containing encoded <code>p_signature</code>
     * @return          The <code>byte[]</code> containing the signature
     */
    public byte[] getEncodedPSignature(SortedMap<String, String> postData) {
        // Get the p_signature
        String pSignature = postData.get("p_signature");

        // Decodes the signature
        pSignature = URLDecoder.decode(pSignature, StandardCharsets.UTF_8);

        return Base64.getDecoder().decode(pSignature);
    }

}
