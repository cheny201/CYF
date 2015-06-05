package com.cy.cyf.net.tcp;

import java.net.Socket;

import com.cy.cyf.log.CYFLog;

public class TcpTest extends TCPHandler{
	
	public TcpTest(Socket socket) {
		super(socket);
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 5; i++) {
			TCPFactory.createServer(TcpTest.class, 10000+i, 50);
		}
		Thread.sleep(1000);
		for (int i = 0; i < 2; i++) {
			String resp = TCPClient.sendMessage("127.0.0.1", 10003, "测试"+i);
			CYFLog.info(resp);
//			Thread.sleep(1000);
		}
		TCPFactory.shutdown();
		TCPFactory.shutdownAll();
	}

	@Override
	protected String doHandler(String requestStr) {
		CYFLog.info(requestStr);
		return requestStr+"===============ok";
	}

}
