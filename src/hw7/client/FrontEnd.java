package hw7.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import hw7.server.ServerFront;

/**
 * 
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public abstract class FrontEnd {
	protected ServerFront serverFront;
	
	protected int login(String username, String password) {
		byte[] salt = serverFront.getSalt(username);
		if (salt == null) { return 3; }
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
	
	protected int register(String username, String email) {
		return serverFront.addUser(username, email);
	}
}
