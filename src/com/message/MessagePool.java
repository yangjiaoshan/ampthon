package com.message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessagePool {
	BlockingQueue<Message> pool = new LinkedBlockingQueue<Message>() ;
	
	public void putIp(Message message){
		try {
			pool.put(message) ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Message takeIp() {
		Message message = null ;
		try {
			message =  pool.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("没有消息数据");
		}
		return message ;
	}
	
	public int getlen() {
		return pool.size();
	}
}
