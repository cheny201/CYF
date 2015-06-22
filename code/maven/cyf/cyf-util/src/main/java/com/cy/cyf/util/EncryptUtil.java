package com.cy.cyf.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import com.cy.cyf.core.exception.EncryptException;
import com.cy.cyf.log.CYFLog;

public class EncryptUtil {
	
	public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA1";
    public static final String HmacMD5 = "HmacMD5";
    public static final String HmacSHA1 = "HmacSHA1";
    public static final String DES = "DES";
    public static final String AES = "AES";
    public static final String BASE64 = "BASE64";
	
    
    public static void main(String[] args) {
    	EncryptDTO encryptDTO = new EncryptDTO(SHA1,"cyf");
		System.out.println(encode(encryptDTO).length());
	}
    
    public static String encode(EncryptDTO encryptDTO) throws EncryptException{
    	try {
			String result = null;
			
			switch (encryptDTO.getType()) {
				case MD5:
					result = DigestUtils.md5Hex(encryptDTO.getStr());
					break;
				case SHA1:
					result = DigestUtils.sha1Hex(encryptDTO.getStr());
					break;
				case BASE64:
					result = new Base64().encodeToString(encryptDTO.getStr().getBytes(encryptDTO.getEncoding()));
					break;
				default:
					break;
			}
			return result;
		} catch (Exception e) {
			CYFLog.debug("加密失败.......");
			CYFLog.debug(encryptDTO == null?"":encryptDTO.toString());
			throw new EncryptException(e);
		}
    }
    

}
