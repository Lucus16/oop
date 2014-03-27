package hw7.server.engine;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import utils.Range;

/**
 * TODO: Add validation for input fields
 * @author lars
 *
 */
public class Engine {
	private HashMap<String, User> users;
	private static final char[] passChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
	private static final int passLength = 8;

	public byte[] getSalt(String username) {
		return users.get(username).getSalt();
	}

	public int login(User user, byte[] hash) {
		if (!user.authenticate(hash)) {
			return 1;
		}
		return 0;
	}

	public User getUser(String username) {
		return users.get(username);
	}
	
	public String addUser(String username, String email) {
		SecureRandom gen = new SecureRandom();
		char[] password = new char[passLength];
		for (int i : new Range(passLength)) {
			password[i] = passChars[gen.nextInt(passChars.length)];
		}
		users.put(username, new User(username, email, password.toString()));
		return password.toString();
	}
}
