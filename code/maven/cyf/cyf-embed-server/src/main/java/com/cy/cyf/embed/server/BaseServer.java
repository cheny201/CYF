package com.cy.cyf.embed.server;

public abstract class BaseServer {
	protected int port;
	protected String contextPath;
	protected String path;
	
	public BaseServer(int port, String contextPath, String path) {
		this.port = port;
		this.contextPath = contextPath;
		this.path = path;
	}

	public abstract void start();

	public abstract void stop();
}
