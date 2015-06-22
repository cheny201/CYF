package com.cy.cyf.net.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPSClient;

import com.cy.cyf.core.Constant;
import com.cy.cyf.log.CYFLog;
import com.cy.cyf.net.dto.AuthDTO;
import com.cy.cyf.util.IOUtil;

public class FTPUtil {
	
	private FTPSClient client;
	
	public boolean connect(String host, int port, AuthDTO authDTO,int timeout){
		boolean flag = false;
		try {
			client = new FTPSClient();
			if(timeout == -1){
				timeout = Constant.FTP_TIMEOUT;
			}
			client.setConnectTimeout(timeout);
			client.connect(host, port);
			if(authDTO != null){
				client.login(authDTO.getUserName(), authDTO.getPassword());
			}
			flag = true;
		} catch (Exception e) {
			CYFLog.error("连接FTP异常",e);
		}
		return flag;
	}
	
	
    /**
     * 上传文件
     * 
     * @param directory 上传的目录
     * @param uploadFile 要上传的文件
     * @param sftp
     */
    public boolean upload(String directory, String uploadFile){
    	boolean flag = false;
        FileInputStream in = null;
        try {
            File file = new File(uploadFile);
            in = new FileInputStream(file);
            client.appendFile(directory, in);
            CYFLog.debug("上传文件路径["+directory+ "/" +file.getName()+"]");
            flag = true;
        }
        catch (Exception e) {
        	CYFLog.error("上传文件异常",e);
        }finally{
        	IOUtil.closeInputStream(in);
        }
        return flag;
    }
	
	public void disconnect(){
		try {
			if(client != null){
				client.disconnect();
			}
		} catch (IOException e) {
			CYFLog.error("断开FTP异常", e);
		}
	}
	
	public static void main(String[] args) {
		FTPUtil ftp = new FTPUtil();
		ftp.connect("ftp://ftp.globalscape.com", 21, null, -1);
	}

}
