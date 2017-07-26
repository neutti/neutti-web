package com.neutti.webframe.core.authentication;



import com.neutti.webframe.core.bean.UserVO;

import java.util.Map;


public interface Authentication {

	void setUser(Object obj);

	UserVO getUser();

	void removeUser();

	boolean isLogin();

	Map<String, Object> getUserMap();

}
