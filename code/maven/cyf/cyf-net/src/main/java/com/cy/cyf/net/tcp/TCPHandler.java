package com.cy.cyf.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.cy.cyf.log.CYFLog;

public abstract class TCPHandler implements Runnable{
	
	private Socket socket;
	
	public TCPHandler(Socket socket){
		this.socket = socket;
	}
	
	/**
	 * 请求处理方法
	 * @param requestStr
	 */
	protected abstract String doHandler(String requestStr);
	
	/**
	 * 获取请求信息
	 * @return
	 */
	private String getRequestStr(){
		String resp = null;
		try {
			InputStream is = socket.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			StringBuffer req = new StringBuffer();
			while ((len = is.read(buf)) != -1) {
				req.append(new String(buf, 0, len));
			}
			resp = req.toString();
		} catch (Exception e) {
			CYFLog.error("获取请求信息异常",e);
		}
		return resp;
	}
	
	
	/**
	 * 设置返回信息
	 * @param str
	 */
	private void setResponse(String str){
		try {
			if(!socket.isOutputShutdown()){
				OutputStream os = socket.getOutputStream();
				os.write(str.getBytes());
				os.flush();
			}
		} catch (Exception e) {
			CYFLog.error("设置返回信息异常",e);
		}
	}
	
	private void close(){
		try {
			if(!socket.isInputShutdown()){
				socket.shutdownInput();
			}
			if(!socket.isOutputShutdown()){
				socket.shutdownOutput();
			}
			if(!socket.isClosed()){
				socket.close();
			}
		} catch (IOException e) {
			CYFLog.error("关闭socket异常",e);
		}
	}
	
	@Override
	public void run() {
		try {
			String resp = doHandler(getRequestStr());
			if(resp != null ){
				setResponse(resp);
			}
			close();
		} catch (Exception e) {
			CYFLog.error("处理socket异常",e);
		}
	}
	
}
