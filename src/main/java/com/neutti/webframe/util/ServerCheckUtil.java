package com.neutti.webframe.util;

import java.io.File;

public class ServerCheckUtil {
	/**
	 * 실제운영서버에서 구동 확인
	 * cafe24
	 * @return
	 */
	public static boolean isActualServer() {
		String cafePath = "/home/hosting_users/neuttti";
		File path = new File(cafePath);
		return path.exists();
	}

}
