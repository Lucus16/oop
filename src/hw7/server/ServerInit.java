package hw7.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import hw7.server.engine.Engine;

/**
 * 
 * @author Sal Wolffs s4064542
 * @author Lars Jellema s4388747
 */
public class ServerInit {
	private Engine engine;
	private File dataFile;
	
	public ServerInit() {
		dataFile = new File(".shopdata");
		engine = readEngine();
		if (engine == null) {
			engine = new Engine();
		}
	}
	
	public ServerFront getServerFront() {
		return new ServerFront(engine);
	}
	
	private Engine readEngine() {
		if (!dataFile.isFile()) {
			return null;
		}
		ObjectInputStream objectIn;
		try {
			objectIn = new ObjectInputStream(new FileInputStream(dataFile));
		} catch (FileNotFoundException e1) {
			return null;
		} catch (IOException e1) {
			return null;
		}
		try {
			return (Engine)objectIn.readObject();
		} catch (ClassNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			try {
				objectIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeEngine() {
		ObjectOutputStream objectOut;
		try {
			objectOut = new ObjectOutputStream(new FileOutputStream(dataFile));
		} catch (FileNotFoundException e) {
			return;
		} catch (IOException e) {
			return;
		}
		try {
			objectOut.writeObject(engine);
		} catch (IOException e) {
			return;
		} finally {
			try {
				objectOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
