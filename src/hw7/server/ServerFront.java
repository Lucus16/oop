package hw7.server;

import hw7.client.FrontEnd;
import hw7.server.engine.Engine;
import hw7.server.engine.User;

public class ServerFront {
	private FrontEnd frontEnd;
	private Engine engine;
	private User user;
	
	public ServerFront(FrontEnd frontEnd, Engine engine) {
		this.frontEnd = frontEnd;
		this.engine = engine;
		this.user = null;
	}
	
	/**
	 * 
	 * @param username
	 * @param email
	 * @return errorCode, 0 for success
	 */
	int addUser(String username, String email) {
		return 0;
	}
	
	byte[] getSalt(String username) {
		return engine.getSalt(username);
	}
	
	int login(String username, byte[] hash) {
		User user = engine.getUser(username);
		if (user == null) {
			return 2;
		}
		return engine.login(user, hash);
	}
	
	
}
