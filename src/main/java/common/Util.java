package common;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 工具类
 */
public class Util {

	/**
	 * 使用MD5加密字符串
	 *
	 * @param str 待加密字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		//返回加密后的字符串
		return base64en.encode(md5.digest(str.getBytes("utf-8")));

	}

	/**
	 * 获取一个UUID
	 *
	 * @return uuid 字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/*
 * 获取文件扩展名
 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot);
			}
		}
		return "";
	}
}
