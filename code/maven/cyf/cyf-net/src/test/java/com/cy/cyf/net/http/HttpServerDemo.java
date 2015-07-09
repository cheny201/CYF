package com.cy.cyf.net.http;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public class HttpServerDemo extends HttpServerUtil {
	
	public static void main(String[] args) {
		createServer("/test",new HttpServerDemo(),8080,50,null);
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
