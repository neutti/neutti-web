package com.neutti.webframe.core.storage;

public enum SessionType {
	CREATE, LOGIN, LOGOUT, DESTORY
	;


	public static SessionType find(String str) {
		for (SessionType c : values()) {
			if (c.toString().equals(str)) {
				return c;
			}
		}
		return null;
	}

}
