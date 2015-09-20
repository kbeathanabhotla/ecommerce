package com.crossover.ecommerce.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogUtil {

	private static Logger logger = Logger.getLogger(LogUtil.class.getName());

	public static void log(String message) {
		logger.log(MyLevel.HADOOP, message);
	}

	public static class MyLevel extends Level {

		public static final Level HADOOP = new MyLevel(Level.INFO.toInt() + 1, "HADOOP", 10);

		protected MyLevel(int level, String levelStr, int syslogEquivalent) {
			super(level, levelStr, syslogEquivalent);
		}
	}
}
