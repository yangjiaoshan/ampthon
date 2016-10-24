package com.NetTools;

public class NetUtil {
	public static String htonsip(long ip) {
		return ((ip >> 0) & 0x0ff) + "." + ((ip >> 8) & 0x0ff) + "." 
				+ ((ip >> 16) & 0x0ff) + "." +((ip >> 24) & 0x0ff) ;
	}
	
	
	public static long iphtons(String ip) {
		if (ip == null || ip == "")
			return 0;
		else if (ip.equals(0))
			return 0;
		long ip10 =0 ;
		String[] ss = ip.trim().split("\\.") ;
		if (ss.length !=4 ){
			return 0 ;
		}
		for (int i = 0 ;i < 4; i++) {
			ip10 |= Long.parseLong(ss[i]) << ((i) * 8) ;
		}
		return ip10 ;
	}
	
	public static void main(String[] args) {
		System.out.println(htonsip(212303882));
		System.out.println(iphtons("106.186.29.35"));
	}
	
	
}
