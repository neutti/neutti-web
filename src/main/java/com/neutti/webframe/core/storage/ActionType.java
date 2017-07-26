package com.neutti.webframe.core.storage;

public enum ActionType {
	SYSTEM, USER, LINKAGE
	;


	public static ActionType find(String str) {
		for (ActionType c : values()) {
			if (c.toString().equals(str)) {
				return c;
			}
		}
		return null;
	}

}
