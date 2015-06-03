package com.cy.cyf.net.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import com.cy.cyf.io.IOUtil;
import com.cy.cyf.log.CYFLog;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPUtil {
	
	private ChannelSftp sftp = null;
	
    /**
     * 连接sftp服务器
     * 
     * @param host 主机
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean connect(String host, int port, String username, String password) {
    	boolean flag = false;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            CYFLog.debug("Session created.");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            CYFLog.debug("Session connected.");
            CYFLog.debug("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            CYFLog.debug("Connected to " + host + ".");
            flag = true;
        }
        catch (Exception e) {
        	CYFLog.error("SFTP连接异常",e);
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
            sftp.cd(directory);
            File file = new File(uploadFile);
            in = new FileInputStream(file);
            sftp.put(in, file.getName());
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

    /**
     * 下载文件
     * 
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     * @param sftp
     */
    public void download(String directory, String downloadFile, String saveFile){
        FileOutputStream out = null;
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            out = new FileOutputStream(file);
            sftp.get(downloadFile, out);
        }
        catch (Exception e) {
        	CYFLog.error("下载文件异常",e);
        }finally{
        	IOUtil.closeOutputStream(out);
        }
    }

    /**
     * 删除文件
     * 
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        }
        catch (Exception e) {
        	CYFLog.error("删除文件异常",e);
        }
    }
    
    /**
     * 
     * 创建文件夹
     * 
     * @param
     * @since 2015-5-21 下午3:22:25  
     * @author  ChenYing
     * @return
     */
    public boolean makeDirs(String directory) throws SftpException{
    	boolean flag = false;
        try {
			String[] dirs = directory.split("/");
			if(dirs.length > 1){
			    int i = 0;
			    String temp = "/"+dirs[i];
			    boolean end = false;
			    while(!end){
			        try {
			            sftp.ls(temp);
			        }
			        catch (Exception e) {
			            sftp.mkdir(temp);
			        }
			        i++;
			        if(i>=dirs.length){
			            end=true;
			        }else{
			            temp += "/" + dirs[i];
			        }
			    }
			}
			flag = true;
		} catch (Exception e) {
			CYFLog.error("创建目录异常", e);
		}
        return flag;
    }
    

    /**
     * 列出目录下的文件
     * 
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    @SuppressWarnings("unchecked")
    public Vector<LsEntry> findFiles(String directory) {
    	Vector<LsEntry> ls = null;
        try {
        	ls = sftp.ls(directory);
		} catch (Exception e) {
			CYFLog.error("查找文件异常",e);
		}
        if(ls == null){
        	ls = new Vector<ChannelSftp.LsEntry>();
        }
        return ls;
    }
    
    public void disconnect(){
        try {
            if(sftp!= null){
                sftp.getSession().disconnect();
            }
        }
        catch (Exception e) {
        	CYFLog.error("断开SFTP异常", e);
        }
    }
    
    public static void main(String[] args) {
        SFTPUtil sf = new SFTPUtil();
        if(sf.connect("121.40.63.45", 22, "root", "Msth2012")){
        	Vector<LsEntry> ls = sf.findFiles("/root/test/*.xlsx");
        	for (int i = 0; i < ls.size(); i++) {
				System.out.println(ls.get(i).getFilename());
			}
        	sf.disconnect();
        }
    }

}
