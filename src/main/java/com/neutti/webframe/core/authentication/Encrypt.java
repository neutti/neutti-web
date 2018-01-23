package com.neutti.webframe.core.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 암호화 관련
 * @author PIG
 *
 */

public class Encrypt {
	public static final Log logger = LogFactory.getLog(Encrypt.class);
	public static String encode(String val, String algorithm){
		if(val == null || algorithm == null) return null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.reset();
			md.update(val.getBytes());
			byte messageDigest[] = md.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public static String encode(String val){
		return encode(val,"SHA-256");
	}
	 /**
		 * BASE64 Encoder
		 * @param str
		 * @return
		 */
		public static String base64Encode(String str)  throws java.io.IOException {
		    //sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		    //byte[] strByte = str.getBytes();
		    //String result = encoder.encode(strByte);
		    return null;//result ;
		}

		/**
		 * BASE64 Decoder
		 * @param str
		 * @return
		 */
		public static String base64Decode(String str)  throws java.io.IOException {
		    //sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		    //byte[] strByte = decoder.decodeBuffer(str);
		    //String result = new String(strByte);
		    return null;//result ;
		}
}
