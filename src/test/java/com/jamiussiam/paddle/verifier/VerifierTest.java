package com.jamiussiam.paddle.verifier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifierTest {

    static String publicKeyString;
    static String postBody;

    @BeforeAll
    public static void SetupOnce() {
        publicKeyString = "-----BEGIN PUBLIC KEY-----\n" +
                "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAo55zPV8qRmhukewHPSqj\n" +
                "j+jkOUeZl9vO3XVqeEK7qFyMEEi2hweznRkkstblof7YNMzpjGMXWpvTd6MPzrBd\n" +
                "kyI6mAbQQvo5elBGHrRk2wDQ5a1ypb7WcJvt6sZYa/c7FDQM20eY/DWgLtg/o3Vm\n" +
                "wcUpO3BuvaqoV80gelg+L9+ikwm5HQU5aZNiMnRc6n2whdBzNChBpbNXdIk/gEqD\n" +
                "8jxSSWWap9n1s+b/0QhKpRF3rN5lSvcY3fsXO+5gZISvlnRbE3TIHxQu+/aHHzjb\n" +
                "oIQ86BY1/GSb3SzojNKribhE8FRaSCoGCBe6GtFEbOBVgtETHA22o26VkZBxdFWZ\n" +
                "w3ttW1ghmNesWfSSD4uGsCxgi6qrosmXfmsLV1rC5bj95ngxlmHWqtPLPwXyrMDu\n" +
                "IpKspJREl1CVzNvyAjgfF+28hI0VIzEOs5rHFm13UktXAkCuX3PXu+ss+6atTqfa\n" +
                "LvhsKnbeTJlWP5Ya7pP+V3h0XaeSCdVypWV/agG83i3V/QD0OBeWZD8jbgcLUdsI\n" +
                "bQHPUfMIHKwT0Z2N5OoK8lzGjI+FJY5tsU+3c9GbVMVcCOCTPT1Wiko6HMj4SQ9f\n" +
                "0cJbfbUjB5O/qeHnjJUHQt8H1P64td8lXdPYXFYXQTzPRaHgyQ0XAug6D63zS6Jj\n" +
                "1/fFiXXkOM9g0TxupfOeaoUCAwEAAQ==\n" +
                "-----END PUBLIC KEY-----";

        postBody = "alert_id=1688369608&alert_name=subscription_payment_succeeded&balance_currency=GBP&balance_earnings=438.94&balance_fee=689.32&balance_gross=969.43&balance_tax=470.42&checkout_id=7-003ed87d513daec-9d69480610&country=CA&coupon=Coupon+3&currency=EUR&customer_name=customer_name&earnings=992.35&email=walsh.noemie%40example.net&event_time=2020-04-11+18%3A59%3A09&fee=0.36&initial_payment=false&instalments=7&marketing_consent=1&next_bill_date=2020-05-02&next_payment_amount=next_payment_amount&order_id=9&passthrough=Example+String&payment_method=paypal&payment_tax=0.21&plan_name=Example+String&quantity=11&receipt_url=https%3A%2F%2Fmy.paddle.com%2Freceipt%2F1%2Fa273b86f41944c4-711b5fa760&sale_gross=467.55&status=trialing&subscription_id=4&subscription_payment_id=4&subscription_plan_id=4&unit_price=unit_price&user_id=6&p_signature=MG58%2BtMV80bahqT7VXTho63eqPHi0tfuM6nTv3%2BufkjAqvC9UG%2Bmf39p2dtrJBnkD64RIDmVpKPaLI0VUKn3BVQKDHfPTFbXQKBFjtUXrywFBssnOGka1QUvF4z5G%2F8cZ3VpQEcdw3BfGFFQonAdS67X7HCsLOuuhdTmeTk%2BT8PsSXxQViPCEn%2FjFBtP6%2FaBw4X3WYIQTnOkYYxmApOLcxPAmi%2FeGzfCJJVoZqS%2Buwz8rOYElMn3aD4wnoxUD0LVsW6kT%2BSu6OXZU4eSHkVcsmvmaXaC%2B6NWb2o%2FavIMKnries4%2BtTY%2BXjPg6cuDVzBAWpmvEtkFIDDrBNyHETvCxywMvpJZUMhHRCqNDMulZdNpCkVtvQpZK3W2uTD4H5GRx82AWKdM5LHlOv0qM00CvMHuOy8uULJiymYBshNgvuOtB4C0DMfWzSBVxXwGgLDGZwrAQ0bzuwUGNWIjAuBH%2BUOmHmjpHV83cu9%2BLc6hqgVicQ61EEFciONEe09zhfOZaavQn4YwisLmFw2F39CLGOVvMluZpNjsSR5YkUWuA7dA%2Fnv0pW3IC9sNEMuSRuVgT3XmR3rI7st5qQIYnTzGj6gVL7Q5DqVcbrD2yD%2FvZbMsXWhtyFJsiE065zSkuUU5rWPG96%2BnpPo79yZPI5vKpzaCVt9F%2FVGNhtx3gghy1Rg%3D";
    }

    @Test
    void verifyDataWithSignature() {
        Verifier verifier = new Verifier();
        assertTrue(verifier.verifyDataWithSignature(postBody, publicKeyString));
    }

    @Test
    void verifyDataWithSignatureInvalidPostBody() {
        Verifier verifier = new Verifier();
        String postBody = VerifierTest.postBody.replace("&", "b");

        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class,
                () -> verifier.verifyDataWithSignature(postBody, publicKeyString));
    }

    @Test
    void verifyDataWithSignatureInvalidPublicKey() {
        Verifier verifier = new Verifier();
        String publicKey = VerifierTest.publicKeyString.replace("a", "bdf");

        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class,
                () -> verifier.verifyDataWithSignature(postBody, publicKey));
    }

    @Test
    void verifyDataWithSignatureInvalidSignature() {
        Verifier verifier = new Verifier();
        String postBody = VerifierTest.postBody.replace("T", "bva12");

        assertFalse(verifier.verifyDataWithSignature(postBody, publicKeyString));
    }

    void verifyDataWithSignatureWithoutSignature() {
        Verifier verifier = new Verifier();
        String postBody = VerifierTest.postBody.replace("p_signature", "no_signature");

        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class,
                () -> verifier.verifyDataWithSignature(postBody, publicKeyString));
    }
}