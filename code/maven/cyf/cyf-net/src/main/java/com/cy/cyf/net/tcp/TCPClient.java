package com.cy.cyf.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.cy.cyf.log.CYFLog;

/**
 * TCP客户端示例
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:24:02
 */
public class TCPClient{

	public static String sendMessage(String ip, int port, String msg)
			throws UnknownHostException, IOException {
		StringBuffer resp = new StringBuffer();
		try {
			Socket s = new Socket(ip, port);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();

			os.write(msg.getBytes());
			os.flush();

			s.shutdownOutput();

			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				resp.append(new String(buf, 0, len));
			}
			s.shutdownInput();
			s.close();
		} catch (Exception e) {
			CYFLog.error("发送信息异常",e);
		}
		return resp.toString();
	}
}
