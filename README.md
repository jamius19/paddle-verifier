![Paddle Verifier Logo](https://cdn.jsdelivr.net/gh/jamius19/paddle-verifier/img/logo.svg)



### Paddle Webhook Verifier

![Generic badge](https://img.shields.io/badge/Version-1.0-blue.svg)  [![Build Status](https://travis-ci.com/jamius19/paddle-verifier.svg?branch=master)](https://travis-ci.com/jamius19/paddle-verifier) [![Codecov Status](https://codecov.io/gh/jamius19/paddle-verifier/branch/master/graph/badge.svg)](https://codecov.io/gh/jamius19/paddle-verifier/branch/master/graph/badge.svg) [![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/dwyl/esta/issues)  



#### What is it?

It's a small utility library for verifying paddle webhooks. Paddle is a online payment gateway. More details about verifying paddle webhooks can be found [on their official documentation.](https://developer.paddle.com/webhook-reference/verifying-webhooks)



#### How to get it?

I'm publishing it to maven central as soon as possible. For the time being please download the **.jar** file from release.



##### How to use it?

Using Paddle Verifier is very easy. Firstly, import it from the package.

```java
import com.jamiussiam.paddle.verifier.Verifier;
```



Then use it as shown,

```java
String publicKey;
String postBody;

Verifier verifier = new Verifier();
boolean isValid = verifier.verifyDataWithSignature(postBody, publicKey);
```



