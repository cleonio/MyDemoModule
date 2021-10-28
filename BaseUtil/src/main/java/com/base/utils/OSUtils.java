package com.base.utils;

public class OSUtils {
	public enum OSType {
		UNKNOWN, MAC, LINUX, WINDOWS
	}

	public static OSType GetOSType() {
		String osName = System.getProperties().getProperty("os.name");
		if (osName.contains("Mac")) {
			return OSType.MAC;
		} else if (osName.contains("Windows")) {
			return OSType.WINDOWS;
		} else if (osName.contains("Linux")) {
			return OSType.LINUX;
		}
		return OSType.UNKNOWN;
	}
}
