![Paddle Verifier Logo](https://cdn.jsdelivr.net/gh/jamius19/paddle-verifier/img/logo.svg)



## Paddle Webhook Verifier

[![Maven Central](https://img.shields.io/maven-central/v/com.jamiussiam/paddle-verifier.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.jamiussiam%22%20AND%20a:%22paddle-verifier%22) [![codecov](https://codecov.io/github/jamius19/paddle-verifier/graph/badge.svg?token=GAGKYUP12G)](https://codecov.io/github/jamius19/paddle-verifier) [![GitHub license](https://img.shields.io/github/license/Naereen/StrapDown.js.svg)](https://github.com/jamius19/paddle-verifier/blob/master/LICENSE)
<br/>

### What is it?

A small utility library (without any external dependecies) for verifying paddle webhooks via Public key and given signature. Paddle is an online payment gateway and more details about verifying paddle webhooks can be found [on their official documentation.](https://developer.paddle.com/webhook-reference/verifying-webhooks)

<br/>

### How to get it?

#### Maven
Include it in your Maven projects.
```xml
<dependency>
  <groupId>com.jamiussiam</groupId>
  <artifactId>paddle-verifier</artifactId>
  <version>2.1</version>
</dependency>
```
  
#### Gradle
Include it in your Gradle projects.
```groovy
implementation 'com.jamiussiam:paddle-verifier:2.1'
```

#### Jar File
You can download the ***.jar** file from release.  

<br/>

### How to use it?

Using Paddle Verifier is very easy. Firstly, import it from the package.

```java
import com.jamiussiam.paddle.verifier.Verifier;
```

Then use it as shown,

```java
String publicKey;
String postBody;

Verifier verifier = new Verifier(publicKey);
boolean isValid = verifier.verifyDataWithSignature(postBody);
```
<br/>


### Detailed Guide

#### Constructor Parameter

`publicKey` is quite self explanatory, it's the `String` representation of your public key. For example,
```java
String publicKey =  "-----BEGIN PUBLIC KEY-----\n" +
                    "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIfVXds6Dfo+EZGFcOJPuhUverHOConA\n" +
                    "j51EQuVpAEhPw6PgyZCi504jxNgiGj6YVOkEJtz5C2d3mgJzsBJs6fUCAwEAAQ==\n" +
                    "-----END PUBLIC KEY-----\n";
```

#### Method Parameter

`postBody` is your POST data from Paddle webhook and it should be in this format,
```http request
alert_id=1688369608&balance_currency=GBP&balance_earnings=438.94&balance_fee=689.32  ....
```
It should contain `p_signature` key. The key value pairs should be separated by `&` and the post body should be [URL Encoded.](https://en.wikipedia.org/wiki/Percent-encoding) (Paddle does this by dafault)

<br/>


#### Spring Boot
In spring boot, you can define the bean by,
```java
String publicKey;

@Bean
public Verifier getVerifier() {
    return new Verifier(publicKey);
}
```

Then, inject it inside your `Controller` or `Component` like,
```java
@Autowired
Verifier verifier;
```

Finally, you can use it to verify incoming requests from Paddle,

```java
@PostMapping(value = "/webhook/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
private boolean webhook(HttpEntity<String> httpEntity) {

    // Get the validity staus of the request
    boolean isValid = verifier.verifyDataWithSignature(httpEntity.getBody());

    if (isValid) {
        // ... your logic goes here
    }

    return isValid;
}

```



