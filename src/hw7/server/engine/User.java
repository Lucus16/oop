package hw7.server.engine;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class User {
	private String username;
	private String email;
	private byte[] salt;
	private byte[] passwordHash;
	private ShoppingCart cart;
	
	public User(String username, String email, String password) {
		assert password.length() <= 51;
		SecureRandom gen = new SecureRandom();
		salt = new byte[4];
		gen.nextBytes(salt);
		this.email = email;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(1);
			return;
		}
		md.update(password.getBytes());
		md.update(salt);
		this.passwordHash = md.digest();
		this.username = username;
	}

	public byte[] getSalt() {
		return salt;
	}
	
	public boolean authenticate(byte[] hash) {
		return hash.equals(passwordHash);
	}
	
	public ShoppingCart getCart() {
		return cart;
	}
}
