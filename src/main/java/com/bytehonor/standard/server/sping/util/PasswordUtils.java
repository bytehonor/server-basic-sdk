package com.bytehonor.standard.server.sping.util;

import com.bytehonor.protocol.common.server.util.MD5Utils;

/**
 * 密码加密
 * 
 * @author lijianqiang
 *
 */
public class PasswordUtils {
	
	private static final String TXT = "huajietaojin";
	
	/**
	 * @param clearText
	 * @param salt
	 * @return
	 */
	public static String encrypt(String clearText, String salt) {
		StringBuilder sb = new StringBuilder();
		sb.append(clearText).append(TXT).append(salt);
		return MD5Utils.md5(sb.toString());
	}

}