package hw7.server;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import hw7.client.FrontEnd;
import hw7.server.engine.Engine;
import hw7.server.engine.User;

/**
 * 
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class ServerFront {
	private Engine engine;
	private User user;
	
	public ServerFront(Engine engine) {
		this.engine = engine;
		this.user = null;
	}

	/**
	 * Dummy mail sender, actually creates a file with email address, subject and timestamp as title
	 * @param address
	 * @param subject
	 * @param content
	 */
	public static void sendMail(String address, String subject, String content) {
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
	public int addUser(String username, String email) {
		String password = engine.addUser(username, email);
		sendMail(email, "Your login details", "Your login details are:\n\nUsername: " + username +
				"\nPassword: " + password + "\n");
		return 0;
	}
	
	public byte[] getSalt(String username) {
		return engine.getSalt(username);
	}
	
	public int login(String username, byte[] hash) {
		User user = engine.getUser(username);
		if (user == null) {
			return 2;
		}
		int r = engine.login(user, hash);
		if (r == 0) {
			this.user = user;
		}
		return r;
	}
}
