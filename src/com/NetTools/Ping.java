package com.NetTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ping {
	String Remote_addr = null ;
	String CMD = null ;
	
	
	
	Ping(String addr , int count){
		this.Remote_addr = addr ;
		this.CMD = "ping " + this.Remote_addr + " -n " + count ;
	}
	
	public void start(){
		Process pro;
		String line = null ;
		try {
			pro = Runtime.getRuntime().exec(CMD);
			BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = br.readLine()) != null){
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Ping ping = new Ping("www.baidu.com", 10);
		ping.start();
	}
}
