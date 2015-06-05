package com.cy.cyf.net.tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import com.cy.cyf.exception.TCPException;
import com.cy.cyf.log.CYFLog;

/**
 * TCP服务端示例
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:24:22
 */
public class TCPServer extends Thread{
	
	private Class<? extends TCPHandler> tcpHandler;
	private int port;
	private ExecutorService pool;
	private ServerSocket serverSocket;
	
	public TCPServer(Class<? extends TCPHandler> tcpHandler, int port,ExecutorService pool){
		this.tcpHandler = tcpHandler;
		this.port = port;
		this.pool = pool;
	}
	
	public void check() throws TCPException {
		try {
			serverSocket = new ServerSocket(port);
		} catch (Exception e) {
			throw new TCPException("端口["+port+"]已被占用",e);
		}
	}
	
	public ServerSocket getServerSocket(){
		return serverSocket;
	}
	
	@Override
	public void run() {
		try {
			while (!serverSocket.isClosed()) {
				Socket s = serverSocket.accept();
				pool.execute(tcpHandler.getConstructor(Socket.class).newInstance(s));
			}
		} catch (Exception e) {
			CYFLog.error("监听端口["+port+"]处理异常",e);
		}
	}
}
