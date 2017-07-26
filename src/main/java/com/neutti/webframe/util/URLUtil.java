package com.neutti.webframe.util;


import com.neutti.webframe.core.bean.PagingVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;


public class URLUtil {

	public static final Log logger = LogFactory.getLog(URLUtil.class);



	public static String encode(String value) throws UnsupportedEncodingException {
		return encode(value, "utf-8");
	}
	public static String encode(String value, String charset) throws UnsupportedEncodingException {
		if(value == null) return "";
		if(charset == null) return value;
		return URLEncoder.encode(value, charset);
	}

	public static String decode(String value) throws UnsupportedEncodingException {
		return decode(value, "utf-8");
	}
	public static String decode(String value, String charset) throws UnsupportedEncodingException {
		if(value == null) return null;
		return URLDecoder.decode(value, "utf-8");
	}

	/**
	 * GET방식 한글 파라미터 가져올경우 사용
	 * @param value
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String value, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String charSet = (request.getHeader("referer") == null
				&& (request.getHeader("user-agent").matches(".*Firefox.*") || request
						.getHeader("user-agent").matches(".*MSIE.*")) ? "ms949"
				: "utf-8");
		value = (value == null ? null : new String(
				value.getBytes("ISO-8859-1"), charSet));
		return value;
	}

	/**
	 * 파라미터 문구 생성(복원)
	 * @param
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String generationPageingParamsStr(HttpServletRequest request){
		String paramStr = "";
		Enumeration<String> param = request.getParameterNames();
		while(param.hasMoreElements()){
			String key = param.nextElement();
			if(key.equals("pageNum") || key.equals("pageMax")){
				String value = request.getParameter(key);
				paramStr = paramStr + (paramStr.equals("") ?  "" : "&") + key + "=" + value;
			}
		}
		return paramStr;
	}
	public static String generationPagingParamsStr(PagingVO data){
		PagingVO vO = new PagingVO();
		BeanUtils.copyProperties(data, vO);
		String paramsStr = "";
		Method[] methods = vO.getClass().getMethods();
		for(Method m:methods){
			String fname = m.getName();
			if (fname.startsWith("get") && !fname.equals("getType") && !fname.equals("getFirst") && !fname.equals("getClass")){
				Object val = null;
				try {
					val = m.invoke(vO, new Object[0]);
					if(val != null){
						paramsStr = paramsStr + "&" + Character.toLowerCase(fname.replaceFirst("get", "").charAt(0)) + fname.replaceFirst("get", "").substring(1)  + "=" + URLUtil.encode(val.toString());
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return paramsStr;
	}
	public static String generationParamsStr(HttpServletRequest request) {
		String paramStr = "";
		Enumeration<String> param = request.getParameterNames();
		while(param.hasMoreElements()){
			String key = param.nextElement();
			String value = request.getParameter(key);
			paramStr = paramStr + (paramStr.equals("") ?  "" : "&") + key + "=" + value;
		}
		return paramStr;
	}
}
