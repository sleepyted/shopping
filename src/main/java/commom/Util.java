package commom;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 工具类
 */
public class Util {

	/**
	 * 使用MD5加密字符串
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
}
