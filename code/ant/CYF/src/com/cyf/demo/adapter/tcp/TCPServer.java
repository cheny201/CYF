package com.cyf.demo.adapter.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TCP服务端示例
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:24:22
 */
public class TCPServer implements Runnable{

	public Socket sock;

	public TCPServer(Socket sock) {
		this.sock = sock;
	}

	public void run() {
		try {
			OutputStream os = sock.getOutputStream();
			InputStream is = sock.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			StringBuffer req = new StringBuffer();
			while ((len = is.read(buf)) != -1) {
				req.append(new String(buf, 0, len));
			}
			sock.shutdownInput();
			System.out.println("请求消息：" + req);
			os.write(("操作成功" + req.toString()).getBytes());
			os.flush();
			sock.shutdownOutput();
			sock.close();
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(6000);
			ExecutorService pool = Executors.newFixedThreadPool(50);
			while (true) {
				Socket s = ss.accept();
				pool.execute(new TCPServer(s));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
