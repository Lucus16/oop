package hw7;

import hw7.client.CLI;
import hw7.server.ServerInit;
import hw7.shared.Price;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class Main {
	public static void main(String[] args) {
		ServerInit serverInit = new ServerInit();
		CLI cli = new CLI(serverInit);
		int r = cli.run();
		serverInit.writeEngine();
		System.exit(r);
	}
}
