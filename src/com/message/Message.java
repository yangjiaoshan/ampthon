package com.message;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class Message {
	public static final int HEAD_LENGTH = 18;
	
	public byte version ;
	public byte type ;
	public long sequence ;
	public int src ;
	public int length ;
	public List<Integer> destList ;
	
	private byte[] headers = new byte[HEAD_LENGTH];
	
	public void decode(byte[] bytes, int off){
		decode_head(bytes , off) ;
		off += HEAD_LENGTH + 6;
		decode_data(bytes, off, length - HEAD_LENGTH);
	}
	
	public void decode_head(byte[] bytes, int off){
		headers = bytes ;
		version = headers[off++] ;
		type = headers[off++] ;
		ByteBuffer buffer_seq = ByteBuffer.allocate(8);
		ByteBuffer buffer_src = ByteBuffer.allocate(4);
		ByteBuffer buffer_len = ByteBuffer.allocate(4);
		buffer_seq.put(headers, off, 8);
		off += 8;		
		buffer_src.put(headers, off, 4);
		off += 4;
		buffer_len.put(headers, off, 4);
		off += 4;
		buffer_seq.flip();
		buffer_src.flip();
		buffer_len.flip();
		
		sequence = buffer_seq.getLong();
		src = buffer_src.getInt() ;
		length = buffer_len.getInt();
	} // End decode_head
	
	public void decode_data(byte[] bytes, int off , int len){
		byte value_len = bytes[off + 1] ;
		ByteBuffer buffer = ByteBuffer.allocate(value_len);
		buffer.put(bytes, off+2, value_len);
		buffer.flip();
		byte[] value_arry = buffer.array();
		byte[] ip_arry = Arrays.copyOfRange(value_arry, 8, value_len);
		System.out.println((ip_arry[0] & 0xff)+ "." + (ip_arry[1] & 0xff) +
				"." + (ip_arry[2] & 0xff) + "."+ (ip_arry[3] & 0xff) );
	}
	
	
} // End Message
