package hw7.server;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
	 * Dummy mail sender, actually creates a file with email address, subject and timestamp as title
	 * @param address
	 * @param subject
	 * @param content
	 */
	public void sendMail(String address, String subject, String content) {
		String stamp = new Timestamp(new Date().getTime()).toString().substring(0, 19);
		String title = address + "-" + subject + "-" + stamp;
		title = title.replace("@", "-at-");
		title = title.replace(':', '-');
		try {
			FileWriter fw = new FileWriter(title);
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to save mail to file: " + title);
			return;
		}
	}
	
	/**
	 * 
	 * @param username
	 * @param email
	 * @return errorCode, 0 for success
	 */
	int addUser(String username, String email) {
		String password = engine.addUser(username, email);
		sendMail(email, "Your login details", "Your login details are:\n\nUsername: " + username +
				"\nPassword: " + password);
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
