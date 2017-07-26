package com.neutti.webframe.core.authentication;


import com.neutti.webframe.core.bean.UserVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 세션인증
 * @author PIG
 *
 */

public class SessionAuthentication implements Authentication {

	public static final Log logger = LogFactory.getLog(SessionAuthentication.class);
	private HttpServletRequest request;
	public SessionAuthentication(ServletRequest request) {
		this.request = (HttpServletRequest) request;
	}
	public SessionAuthentication(HttpServletRequest request) {
		this.request = request;
	}

	public void setUser(Object obj) {
		try {
			UserVO user = new UserVO();
			Method[] methods = obj.getClass().getMethods();
			for(Method m:methods){
				String name = m.getName();
				Class<?> returnType = m.getReturnType();
				if (name.equals("getId") && returnType.equals(Long.class)){
					Long val = (Long) m.invoke(obj, new Object[0]);
					user.setId(val);
					logger.debug("setId - " + val);
				}else if (name.equals("getTid") && returnType.equals(String.class)){
					String val = (String) m.invoke(obj, new Object[0]);
					user.setTid(val);
					logger.debug("setId - " + val);
				}else if (name.equals("getPhoneNumber") && returnType.equals(String.class)){
					String val = (String) m.invoke(obj, new Object[0]);
					user.setPhoneNumber(val);
					logger.debug("setId - " + val);
				}else if (name.equals("getName") && returnType.equals(String.class)){
					String val = (String) m.invoke(obj, new Object[0]);
					user.setName(val);
					logger.debug("setName - " + val);
				}else if (name.equals("getEmail") && returnType.equals(String.class)){
					String val = (String) m.invoke(obj, new Object[0]);
					user.setEmail(val);
					logger.debug("setEmail - " + val);
				}else if (name.equals("getIsAdmin") && returnType.equals(Boolean.class)){
					Boolean val = (Boolean) m.invoke(obj, new Object[0]);
					user.setAdmin(val);
					logger.debug("setAdmin - " + val);
				}else if (name.equals("getDeptName") && returnType.equals(String.class)){
					String val = (String) m.invoke(obj, new Object[0]);
					user.setDeptName(val);
					logger.debug("setDeptName - " + val);
				}else if (name.equals("getGender") && returnType.equals(String.class)){
					String val = (String) m.invoke(obj, new Object[0]);
					user.setGender(val);
					logger.debug("setGender - " + val);
				}else if (name.equals("getBirthday") && returnType.equals(String.class)){
					String val = (String) m.invoke(obj, new Object[0]);
					user.setBirthday(val);
					logger.debug("setBirthday - " + val);
				}
			}
			//
			request.getSession().setAttribute(AuthenticationConstants.SESSION_NAME_FOR_LOGIN_USER, user);
		} catch (Exception  e) {
			logger.error(e.getMessage());
		}
	}

	public UserVO getUser() {
		UserVO user = (UserVO)request.getSession().getAttribute(AuthenticationConstants.SESSION_NAME_FOR_LOGIN_USER);
		return user;
	}

	public  void removeUser() {
		request.getSession().setAttribute(AuthenticationConstants.SESSION_NAME_FOR_LOGIN_USER, null);
	}

	public boolean isLogin() {
		UserVO user = getUser();
		if(user == null || user.getId() == null){
			return false;
		}else{
			return true;
		}

	}

	public Map<String, Object> getUserMap() {
		boolean isLogin = isLogin();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(AuthenticationConstants.Attrs.IS_LOGIN, isLogin);
		if(isLogin){
			UserVO user = getUser();
			map.put(AuthenticationConstants.Attrs.USER_ID, user.getId());
			map.put(AuthenticationConstants.Attrs.USER_NAME, user.getName());
			map.put(AuthenticationConstants.Attrs.USER_EMAIL, user.getEmail());
			map.put(AuthenticationConstants.Attrs.USER_DEPT_NAME, user.getEmail());
			return map;
		}else{
			return map;
		}
	}


}
