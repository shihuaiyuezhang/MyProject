package com.wiserv.util.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	private Socket socket;
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		OutputStream os = null;
		InputStream is = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			is = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while((info =br.readLine()) != null) {
				System.out.println(info);
			}
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("你好！我是服务器");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {			
			try {
				pw.close();
				os.close();
				br.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
		
	
		
	}
}
