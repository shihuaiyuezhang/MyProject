package com.wiserv.util.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static ServerSocket ss = null;
	
	public static void main(String[] args) {
		try {
			ss = new ServerSocket(24000);
			Socket socket = null;
			int count = 0;
			while(true) {
				socket = ss.accept();
				ServerThread st = new ServerThread(socket);
				st.start();
				count++;
				System.out.println("当前用户访问量为:"+count);
				InetAddress address = socket.getInetAddress();
				System.out.println("当前用户的IP:"+address.getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
