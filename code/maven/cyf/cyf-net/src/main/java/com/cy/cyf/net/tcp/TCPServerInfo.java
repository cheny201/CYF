package com.cy.cyf.net.tcp;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class TCPServerInfo {
	
	private ExecutorService executorService;
	private ServerSocket serverSocket;
	private TCPServer tcpServer;
	
	public TCPServerInfo(ExecutorService executorService,
			ServerSocket serverSocket, TCPServer tcpServer) {
		this.executorService = executorService;
		this.serverSocket = serverSocket;
		this.tcpServer = tcpServer;
	}
	
	public TCPServer getTcpServer() {
		return tcpServer;
	}

	public void setTcpServer(TCPServer tcpServer) {
		this.tcpServer = tcpServer;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}
	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
}
