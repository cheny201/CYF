package com.cy.cyf.net.tcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cy.cyf.exception.TCPException;
import com.cy.cyf.log.CYFLog;
import com.cy.cyf.net.Constant;


/**
 * TCP工厂
 * 功能：创建、关闭TCP服务端
 * 
 * @author ChenY201
 * @date 2015年6月5日
 */
public class TCPFactory {
	
	private static Map<Integer,TCPServerInfo> serverMap = new HashMap<Integer,TCPServerInfo>();
	private static ExecutorService SERVER_POOL = Executors.newFixedThreadPool(Constant.TCP_SERVER_SIZE);
	
	public static void createServer(Class<? extends TCPHandler> tcpHandler, int port,int size) throws TCPException{
		try {
			if(size <= 0){
				size = Constant.TCP_POOL_SIZE;
			}
			ExecutorService pool = Executors.newFixedThreadPool(size);
			TCPServer server = new TCPServer(tcpHandler,port,pool);
			server.check();
			SERVER_POOL.execute(server);
			serverMap.put(port, new TCPServerInfo(pool,server.getServerSocket(),server));
			CYFLog.info("TCP服务启动成功，监听端口["+port+"],最大处理数["+size+"].............");
		} catch (TCPException e) {
			throw e;
		} catch (Exception e) {
			throw new TCPException("创建tcp服务异常["+e.getMessage()+"]",e);
		}
	}
	
	public static void shutdownServer(int port){
		try {
			TCPServerInfo tcpServerInfo = serverMap.get(port);
			if(tcpServerInfo == null){
				CYFLog.info("该端口的tcp服务不存在["+port+"]");
			}else{
				tcpServerInfo.getTcpServer().interrupt();
				tcpServerInfo.getExecutorService().shutdown();
				tcpServerInfo.getServerSocket().close();
				CYFLog.info("关闭端口["+port+"]成功");
				serverMap.remove(port);
			}
		} catch (Exception e) {
			CYFLog.error("关闭tcp监听["+port+"]异常",e);
		}
	}
	
	public static void shutdownServerNow(int port){
		try {
			TCPServerInfo tcpServerInfo = serverMap.get(port);
			if(tcpServerInfo == null){
				CYFLog.info("该端口的tcp服务不存在["+port+"]");
			}else{
				tcpServerInfo.getTcpServer().interrupt();
				tcpServerInfo.getExecutorService().shutdownNow();
				tcpServerInfo.getServerSocket().close();
				CYFLog.info("关闭端口["+port+"]成功");
				serverMap.remove(port);
			}
		} catch (Exception e) {
			CYFLog.error("关闭tcp监听["+port+"]异常",e);
		}
	}
	
	public static void shutdownAll(){
		Iterator<Integer> it = serverMap.keySet().iterator();
		List<Integer> ports = new ArrayList<Integer>();
		while(it.hasNext()){
			ports.add(it.next());
		}
		for (int port:ports) {
			shutdownServer(port);
		}
		shutdown();
		CYFLog.info("TCP服务全部关闭完成...............");
	}
	
	public static void shutdownAllNow(){
		Iterator<Integer> it = serverMap.keySet().iterator();
		List<Integer> ports = new ArrayList<Integer>();
		while(it.hasNext()){
			ports.add(it.next());
		}
		for (int port:ports) {
			shutdownServerNow(port);
		}
		shutdown();
		CYFLog.info("TCP服务全部关闭完成...............");
	}
	
	
	public static void shutdown(){
		try {
			if(SERVER_POOL != null){
				if(!SERVER_POOL.isShutdown()){
					SERVER_POOL.shutdownNow();
					CYFLog.info("关闭TCP服务池成功");
				}else{
					CYFLog.warn("该TCP服务池已关闭");
				}
			}
		} catch (Exception e) {
			CYFLog.error("关闭TCP服务池异常",e);
		}
	}

}
