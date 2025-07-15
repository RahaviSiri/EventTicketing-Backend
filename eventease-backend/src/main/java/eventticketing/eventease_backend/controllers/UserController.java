package eventticketing.eventease_backend.controllers;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eventticketing.eventease_backend.Constants;
import eventticketing.eventease_backend.models.User;
import eventticketing.eventease_backend.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userServices;
    

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String, String> userMap) {
        String name = (String) userMap.get("name");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userServices.registerUser(name, email, password);
        return new ResponseEntity<>(generateJWTToken(user),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody Map<String, String> userMap) {
    
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userServices.validateUser(email, password);
        return new ResponseEntity<>(generateJWTToken(user),HttpStatus.OK);
    }

    private Map <String,String> generateJWTToken (User user){
        long timeStamp = System.currentTimeMillis();
        SecretKey key = Keys.hmacShaKeyFor(Constants.API_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder().signWith(key).setIssuedAt(new Date(timeStamp)).setExpiration(new Date(timeStamp + Constants.TOKEN_VALIDITY)).claim("userId",user.getId()).claim("email", user.getEmail()).compact();
        Map <String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
