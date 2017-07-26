package com.neutti.webframe.core.authentication;


import com.neutti.webframe.core.bean.UserVO;

import java.util.Map;

/**
 * 쿠키인증
 * @author PIG
 *
 */

public class CookieAuthentication implements Authentication {

	public void setUser(Object obj) {

	}

	public UserVO getUser() {
		return null;
	}

	public void removeUser() {

	}

	public boolean isLogin() {
		return false;
	}

	public Map<String, Object> getUserMap() {
		return null;
	}




}
