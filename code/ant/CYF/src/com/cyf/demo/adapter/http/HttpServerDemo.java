package com.cyf.demo.adapter.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cyf.config.Constants;
import com.cyf.util.Tools;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

/**
 * HTTP服务端示例
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:23:37
 */
public class HttpServerDemo implements HttpHandler {

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			Tools.FULLFORMAT);
	private long seq = 0;

	public void handle(HttpExchange httpExchange) {
		String encoding = Constants.ENCODING;
		String reStr = "数据接收成功";
		seq = System.currentTimeMillis();
		InputStream in = httpExchange.getRequestBody(); // 获得输入流
		printlnLog("", 0);
		printlnLog("接收数据开始====================" + seq, 0);
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, encoding));
			int len = 0;
			char[] b = new char[1024 * 1024];
			while ((len = reader.read(b)) != -1) {
				System.out.println(new String(b, 0, len));
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		printlnLog("响应消息：" + reStr, 0);
		printlnLog("接收数据结束====================" + seq, 0);
		try {
			httpExchange.sendResponseHeaders(200, reStr.getBytes().length); // 设置响应头属性及响应信息的长度
			OutputStream out = httpExchange.getResponseBody(); // 获得输出流
			out.write(reStr.getBytes());
			out.flush();
		} catch (IOException e) {
			printlnLog("返回数据失败", 1);
			e.printStackTrace();
		}
		httpExchange.close();
	}

	/**
	 * 输出日志
	 * 
	 * @param str
	 *            输出内容
	 * @param level
	 *            日志级别
	 */
	private void printlnLog(String str, int level) {
		if (level == 0) {
			System.out.println(sdf.format(new Date()) + "  " + this.seq + "  "
					+ str);
		} else if (level == 1) {
			System.err.println(sdf.format(new Date()) + "  " + this.seq + "  "
					+ str);
		}
	}

	public static void main(String[] args) {
		try {
			HttpServerProvider httpServerProvider = HttpServerProvider
					.provider();
			InetSocketAddress addr = new InetSocketAddress(27167);// 监听端口
			HttpServer httpServer = httpServerProvider.createHttpServer(addr,
					50);
			httpServer.createContext("/httpServer.do", new HttpServerDemo());
			httpServer.setExecutor(null);
			httpServer.start();
			System.out.println("==========HTTPServer启动成功======");
		} catch (Exception e) {
			System.out.println("==========HTTPServer启动失败======");
			e.printStackTrace();
		}
	}
}
