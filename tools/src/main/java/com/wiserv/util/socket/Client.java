package com.wiserv.util.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) {
		for(int i = 0;i < 5;i++) {
			try {
				new Client().createClient();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createClient() throws UnknownHostException, IOException {
		OutputStream os = null;
		InputStream is = null;
		Socket socket = new Socket("127.0.0.1",24000);
		os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write("你好！我是客户机");
		pw.flush();
		socket.shutdownOutput();
		is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while((info =br.readLine()) != null) {
			System.out.println(info);
		}
		br.close();
		is.close();
		pw.close();
		os.close();
		socket.close();
	}
}
