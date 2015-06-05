package com.cy.cyf.net.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import com.cy.cyf.log.CYFLog;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;


public class HttpServerUtil implements HttpHandler{

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 创建并启动HTTPServer
	 * @param action
	 * @param httpHandler
	 * @param port
	 * @param executor
	 * @param maxConnection
	 */
	public static void createServer(String action,HttpHandler httpHandler,int port,int maxConnection,Executor executor){
		try {
			HttpServerProvider httpServerProvider = HttpServerProvider
					.provider();
			InetSocketAddress addr = new InetSocketAddress(port);// 监听端口
			HttpServer httpServer = httpServerProvider.createHttpServer(addr,
					maxConnection);
			httpServer.createContext(action, httpHandler);
			httpServer.setExecutor(executor);
			httpServer.start();
			CYFLog.info("==========HTTPServer启动成功======");
			CYFLog.info("action["+action+"]");
			CYFLog.info("port["+port+"]");
			CYFLog.info("maxConnection["+maxConnection+"]");
		} catch (Exception e) {
			CYFLog.error("==========HTTPServer启动失败======",e);
		}
	}
	
	public static void main(String[] args) {
		createServer("/test",new HttpServerUtil(),8080,50,null);
	}

}
