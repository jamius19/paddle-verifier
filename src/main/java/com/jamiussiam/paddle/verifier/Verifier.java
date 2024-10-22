package com.jamiussiam.paddle.verifier;

import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible for verifying the supplied POST data
 * with the public signature.
 *
 * @see SecurityUtils
 * @see StringParser
 * @author Jamius Siam
 */
public class Verifier {
    private final PublicKey publicKey;

    private final SecurityUtils securityUtils;
    private final StringParser stringParser;

    /**
     * Creates a verifier instance from given Public Key
     * @param publicKeyString   The Public Key
     */
    public Verifier(String publicKeyString) {

        securityUtils = new SecurityUtils();
        stringParser = new StringParser();

        try {
            this.publicKey = securityUtils.getPublicKey(publicKeyString);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid Public Key Given");
        }
    }

    /**
     * This method takes the <code>postBody</code> and <code>publicKey</code> and
     * returns a boolean with verification status. The <code><b>p_signature</b></code>
     * field must be present inside the <code>postBody</code> string.
     *
     * @param postBody            The body of the POST request sent from Paddle.
     * @return                    Returns true is data is valid.
     *
     * @throws IllegalArgumentException     It is thrown if the data is not a valid POST body.
     */
    public boolean verifyDataWithSignature(String postBody) {
        boolean isVerified = verifyInputData(postBody);

        // If data isn't a valid post body we throw an exception.
        if(!isVerified || !postBody.contains("p_signature")) {
            throw new IllegalArgumentException("The data supplied isn't a valid POST body.");
        }

        // Get the TreeMap from POST Body
        TreeMap<String, String> postData = getSortedMapFromBody(postBody);

        // Get the p_signature
        byte[] pSignatureEncoded = securityUtils.getEncodedPSignature(postData);

        // Remove the p_signature from postData
        postData.remove("p_signature");


        // Get the serialized String
        String serializedString = stringParser.serialize(postData);


        // Verify the data
        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initVerify(publicKey);
            signer.update(serializedString.getBytes(StandardCharsets.UTF_8));

            isVerified = signer.verify(pSignatureEncoded);
        } catch (Exception e) {
            isVerified = false;
            e.printStackTrace();
        }

        return isVerified;
    }



    /**
     * Checks if the supplied data string is a valid POST body.
     *
     * @param data      The body of the POST request sent from Paddle.
     * @return          Returns true if it's a valid POST body.
     */
    private boolean verifyInputData(String data) {
        String regex = "&\\w+=";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        return matcher.find();
    }


    /**
     * Returns a sorted key value pair from POST body.
     * It includes the <code><b>p_signature</b></code> field.
     *
     * @param data  The POST body data.
     * @return      Returns a {@link TreeMap} with the data as key value pair.
     */
    private TreeMap<String, String> getSortedMapFromBody(String data) {
        String[] split = data.split("&");
        TreeMap<String, String> ret = new TreeMap<>();

        for(String s : split) {
            String[] half = s.split("=", 2);
            ret.put(half[0], half[1]);
        }

        return ret;
    }
}
