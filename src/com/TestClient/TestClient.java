package com.TestClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestClient {
	private String RemoteIP;
	private int port ;
	private Socket connection ;
	private DataInputStream input ;
	private DataOutputStream output ;

	public TestClient(String RemoteIP, int port) {
		this.RemoteIP = RemoteIP ;
		this.port = port ;
		connect() ;
	}

	public  void connect() {
		if (connection == null) {
			connection = new Socket() ;
			InetSocketAddress address = new InetSocketAddress(RemoteIP, port) ;
			try {
				connection.connect(address);
				input = new DataInputStream(connection.getInputStream());
				output = new DataOutputStream(connection.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// End connect

	public DataInputStream getInput() {
		return input;
	}

	public DataOutputStream getOutput() {
		return output;
	}
	
	public Socket getConnection(){
		if (connection == null){
			return null ;
		}
		return connection ;
	}

}
