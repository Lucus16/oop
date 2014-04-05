package hw7.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import hw7.server.ServerFront;

public abstract class FrontEnd {
	protected ServerFront serverFront;
	
	protected int login(String username, String password) {
		byte[] salt = serverFront.getSalt(username);
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(1);
			return -1;
		}
		md.update(password.getBytes());
		md.update(salt);
		byte[] hash = md.digest();
		return serverFront.login(username, hash);
	}
}
