package hw7.server.engine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import utils.Range;

/**
 * TODO: Add validation for input fields
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class Engine implements Serializable {
	private static final long serialVersionUID = 8511777500939554446L;
	private HashMap<String, User> users;
	private static final char[] passChars = 
			("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
			"0123456789").toCharArray();
	private static final int passLength = 8;
	
	public Engine() {
		users = new HashMap<String, User>();
	}

	public byte[] getSalt(String username) {
		User user = users.get(username);
		if (user == null) { return null; }
		return user.getSalt();
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
		if (users.containsKey(username)) {
			return null;
		}
		SecureRandom gen = new SecureRandom();
		char[] password = new char[passLength];
		for (int i : new Range(passLength)) {
			password[i] = passChars[gen.nextInt(passChars.length)];
		}
		String passString = new String(password);
		users.put(username, new User(username, email, passString));
		return passString;
	}
}
