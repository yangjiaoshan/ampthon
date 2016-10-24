package com.TestClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;

import com.message.Message;

public class LaunchClient {
	public static String RemoteIP = "202.108.212.156" ;
	public static int port = 60002 ;
	public static byte[] header = new byte[18] ;
	
	
	public static void main(String[] args) {
		new LaunchClient().launch() ; 
	}
	
	public void launch(){
		byte[] header ;
		ByteBuffer buf = ByteBuffer.allocate(18);
		byte version = 0 ;
		byte type = 1 ;
		long sequence = System.currentTimeMillis();
		int src = 0 ;
		int length = 18 ;
		buf.put(version);
		buf.put(type);
		buf.putLong(sequence);
		buf.putInt(src);
		buf.putInt(length);
		
		header = buf.array();
		
		TestClient client = new TestClient(RemoteIP, port) ;
		DataInputStream input = client.getInput() ;
		DataOutputStream output = client.getOutput();
		try {
			output.write(header);
			output.flush();
			while (true){
				input.read(header,0,length);
				Message message = new Message();
				message.decode_head(header, 0);
				byte[] value = new byte[40] ;
				System.out.println(message.length -length);
				input.read(value, 0, message.length -length);
//				for (byte i : value){
//					System.out.print((i & 0xff) +" ");
//				}
				message.decode_data(value, 6, message.length -length);
				System.out.println("version:" + message.version);
				System.out.println("type:" + message.type);
				System.out.println("sequence:" + message.sequence);
				System.out.println("length:" + message.length);
				System.out.println("read again");
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
 }
