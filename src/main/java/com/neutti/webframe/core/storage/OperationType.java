package com.neutti.webframe.core.storage;

public enum OperationType {
	SELECT,INSERT,UPDATE,DELETE;

	OperationType() {
	}
	public static OperationType find(String str) {
		for(OperationType c:values()){
			if(c.toString().equals(str)){
				return c;
			}
		}
		return null;
	}
}
