package com.cyf.demo.adapter.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TCP客户端示例
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:24:02
 */
public class TCPClient implements Runnable{
	private int id = -1;
	
	public TCPClient(int id){
		this.id = id;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(50);

		for (int i = 0; i < 50; i++) {
			pool.execute(new TCPClient(i));
		}
	}

	public static void sendMessage(String ip, int port, String msg)
			throws UnknownHostException, IOException {
		try {
			Socket s = new Socket(ip, port);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();

			os.write(msg.getBytes());
			os.flush();

			s.shutdownOutput();

			byte[] buf = new byte[1024];
			int len = 0;
			StringBuffer resp = new StringBuffer();
			while ((len = is.read(buf)) != -1) {
				resp.append(new String(buf, 0, len));
			}
			System.out.println("响应消息：" + resp);

			s.shutdownInput();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			TCPClient.sendMessage("127.0.0.1", 6000, "测试"+id);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
