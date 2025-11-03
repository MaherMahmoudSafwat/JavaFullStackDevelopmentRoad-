package JWT.example.JWT_Tutorials.JWTTokens;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.antlr.v4.runtime.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService
{
    // 🔑 PART 1: HARD-CODED SECRET KEY
// This is a backup secret key stored directly in the code
// Think of it like a spare key hidden under the doormat
    private static final String SECRET = "TmV3U2VjcmV0S2V5Rm9ySldUU29uZ2luZ21hc2sgZmF2b3I=";

    // 🔑 PART 2: VARIABLE TO STORE THE REAL SECRET KEY
// This will hold the actual secret key we generate
// Like having an empty keychain waiting for a key
    private static String SecretKeys;

    // 🏗️ PART 3: CONSTRUCTOR - RUNS WHEN JwtService IS CREATED
// This is like the setup instructions that run automatically
    public JwtService()
    {
        // 📝 ACTION: Generate a new secret key and store it
        // When someone creates a new JwtService, it automatically makes a secret key
        SecretKeys = GenerateSecretKey();
    }
    // 🔑 PART 4: SECRET KEY GENERATOR
    // This method creates a brand new, random secret key
    public String GenerateSecretKey()
    {
        try
        {
            // 🛠️ STEP 1: Get a "key maker" machine
            // "HmacSHA256" tells it what type of key to make
            KeyGenerator KeyGen = KeyGenerator.getInstance("HmacSHA256");

            // 🎲 STEP 2: Use the machine to generate a random key
            // This creates a completely random, secure key
            SecretKey secretKey = KeyGen.generateKey();

            // 👀 STEP 3: Print the key to console (for debugging)
            // ⚠️ WARNING: Never do this in production - it exposes your secret!
            System.out.println("SecretKey : " + secretKey.toString());

            // 📦 STEP 4: Convert the key to text format we can store
            // Base64 encoding turns binary data into readable text
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }
        catch (NoSuchAlgorithmException e)
        {
            // 🚨 STEP 5: Error handling if something goes wrong
            throw new RuntimeException("Error generating a secret key ",e);
        }
    }
    public String GenerateToken(String Username)
    {
        // 🎯 JWT SERVICE PURPOSE:
        // This method creates a JSON Web Token (JWT) after successful user authentication
        // JWT acts as a DIGITAL ID CARD that proves the user's identity for future requests

        // 📦 JWT IN GENERAL:
        // - JWT = JSON Web Token = Secure way to transmit information between parties
        // - Contains user identity, permissions, and expiration time
        // - Signed digitally to prevent tampering
        // - Self-contained (server doesn't need to store it in database)

        // 🔄 USER-SERVER COMMUNICATION FLOW:

        // 🚀 FIRST TIME (Login):
        // 1. User sends username/password to /login endpoint
        // 2. Server validates credentials → If valid, calls this method
        // 3. This method generates JWT token containing user identity
        // 4. Server sends JWT back to client
        // 5. Client stores JWT (usually in localStorage or cookies)

        // 🔁 SUBSEQUENT REQUESTS (After Login):
        // 1. Client sends JWT in Authorization header: "Bearer <token>"
        // 2. Server extracts JWT and validates signature + expiration
        // 3. If valid, server trusts the user identity from JWT without checking password again
        // 4. Server processes the request and returns protected data

        // 📝 CREATING JWT CLAIMS:
        // Claims = Pieces of information about the user (like ID card details)
        Map<String,Object> Claims = new HashMap<>();
        Claims.put("username",Username); // 🏷️ Add username as a custom claim

        // 🔨 BUILDING THE JWT TOKEN:
        return Jwts.builder()
                // 📋 SET CUSTOM CLAIMS: Additional user data stored in the token
                .setClaims(Claims)

                // 🏷️ SET SUBJECT: Primary identifier of the token (usually username/userId)
                // This is the main way to identify which user this token belongs to
                .setSubject(Username)

                // ⏰ SET ISSUED AT: Timestamp when token was created
                // Used to track token age and for security auditing
                .setIssuedAt(new Date(System.currentTimeMillis()))

                // ⏰ SET EXPIRATION: Timestamp when token becomes invalid
                // Security measure - tokens automatically expire after 3 hours
                // Prevents unlimited access if token is stolen
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*3))

                // 🔐 SIGN THE TOKEN: Create digital signature using secret key
                // - This prevents tampering - if anyone changes the token, signature becomes invalid
                // - HS256 = HMAC with SHA-256 algorithm
                // - getKey() retrieves the server's secret signing key
                .signWith(GetKey(), SignatureAlgorithm.HS256)

                // 🎫 COMPACT: Convert to final string format
                // Result: "header.payload.signature" format that can be easily transmitted
                .compact();
    }
    // 🔑 PART 5: KEY GETTER - CONVERTS TEXT BACK TO USABLE KEY
    // This takes our stored text key and turns it back into a real cryptographic key
    public static Key GetKey()
    {
        // 🔄 STEP 1: Convert the Base64 text back to raw bytes
        // Think of it like unpacking a stored key
        byte[] KeyBytes = Decoders.BASE64.decode(SecretKeys);

        // 🔄 STEP 2: Convert bytes into a proper Key object that JWT can use
        // This creates the actual key that will sign our JWT tokens
        return Keys.hmacShaKeyFor(KeyBytes);
    }
    // 🎯 METHOD: Extract username from JWT token
// Purpose: Get the username (subject) from the JWT token
// How it works: Uses the extractClaim method with Claims::getSubject
    public static String extractUserName(String token) // 🛠️ FIXED: Parameter name consistency
    {
        return extractClaim(token, Claims::getSubject);
    }

    // 🎯 METHOD: Generic claim extractor (REUSABLE)
// Purpose: Extract ANY claim from the JWT token using a function
// <T> means: This method works with any type (String, Date, Boolean, etc.)
// How it works: Gets all claims, then applies the provided function to get specific claim
    private static <T> T extractClaim(String token, Function<Claims, T> claimResolver) // 🛠️ FIXED: Added static, fixed parameter names
    {
        // 🎯 STEP 1: Extract ALL claims from the JWT token
        final Claims claims = extractAllClaims(token); // 🛠️ FIXED: Variable name consistency

        // 🎯 STEP 2: Use the provided function to get the specific claim we want
        return claimResolver.apply(claims);
    }

    // 🎯 METHOD: Extract ALL claims from JWT token
// Purpose: Parse the JWT token and get all the data (claims) inside it
// How it works: Uses JWT library to parse and validate the token
    private static Claims extractAllClaims(String token) // 🛠️ FIXED: Added static
    {
        return Jwts.parser()
                // 🎯 SET SIGNATURE KEY: Use our secret key to verify token authenticity
                // This ensures the token wasn't tampered with
                .setSigningKey(GetKey()) // 🛠️ FIXED: Method name consistency (getKey vs GetKey)

                // 🎯 BUILD: Create the parser instance
                .build()

                // 🎯 PARSE: Validate signature and extract claims from the token
                // This will throw exception if signature is invalid or token is malformed
                .parseClaimsJws(token)

                // 🎯 GET BODY: Extract the payload part of JWT (where user data is stored)
                .getBody();
    }

    // 🎯 METHOD: Validate JWT token against user details
// Purpose: Check if token is valid for the given user and not expired
// How it works: Compares token username with user details and checks expiration
    public static boolean validateToken(String token, UserDetails userDetails) // 🛠️ FIXED: Parameter name consistency
    {
        // 🎯 STEP 1: Extract username from the token
        final String userName = extractUserName(token); // 🛠️ FIXED: Variable name consistency

        // 🎯 STEP 2: Validate two conditions:
        // 1. Username in token matches username in userDetails
        // 2. Token is not expired
        return (userName.equals(userDetails.getUsername()) // 🛠️ FIXED: Method call syntax
                && !isTokenExpired(token)); // 🛠️ FIXED: Parameter name consistency
    }

    // 🎯 METHOD: Check if token is expired
// Purpose: Determine if the token has passed its expiration time
// How it works: Compares token expiration date with current date
    private static boolean isTokenExpired(String token) // 🛠️ FIXED: Parameter name consistency
    {
        // 🎯 If expiration date is BEFORE current time, token is expired
        return extractExpiration(token).before(new Date());
    }

    // 🎯 METHOD: Extract expiration date from token
// Purpose: Get the expiration timestamp from the JWT token
// How it works: Uses the extractClaim method with Claims::getExpiration
    private static Date extractExpiration(String token) // 🛠️ FIXED: Parameter name consistency
    {
        return extractClaim(token, Claims::getExpiration);
    }
}

