package com.example.todo.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.todo.entity.User;
import com.google.gson.Gson;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtTokenUtil {

	public String createToken(String userName,String userPassword) {
		String token = JWT.create().withClaim("username", userName).withClaim("password",userPassword)
				.withExpiresAt(new Date(System.currentTimeMillis()+1200000L))
				.sign(HMAC512("todoapp".getBytes()));
		return token;
	}

	public static String authenticate(String token) {
		String user = JWT.require(Algorithm.HMAC512("todoapp".getBytes()))
				.build()
				.verify(token)
				.getSubject();
		return user;
	}

	public static boolean VerifyToken(String token) {
		try {
			authenticate(token);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public static String extractToken(String header) {
		return header.replaceAll("Bearer ", "");
	}
	
	public static String[] getHeaderData(String authHeaderBase64) {
		String authHeader=authHeaderBase64.replaceAll("Bearer ", "");
		System.out.println("authHeaderBase64 : " + authHeaderBase64);
		byte[] decodedBytes = Base64.getDecoder().decode(authHeader);
		authHeader = new String(decodedBytes);
		return authHeader.split(":");
	}
	
	public static User getDecoded(String encodedToken) throws UnsupportedEncodingException {
	      String[] pieces = encodedToken.split("\\.");
	      String b64payload = pieces[1];
	      String jsonString = new String(org.apache.commons.codec.binary.Base64.decodeBase64(b64payload), "UTF-8");

	      return new Gson().fromJson(jsonString, User.class);
	}
}