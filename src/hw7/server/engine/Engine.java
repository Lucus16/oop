package hw7.server.engine;

import java.util.HashMap;

public class Engine {
	private HashMap<String, User> users;

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
	
	public void addUser(String username, User user) {
		users.put(username, user);
	}
}
