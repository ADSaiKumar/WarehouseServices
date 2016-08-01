package com.alacriti.warehouseservices.vo;

import org.apache.log4j.Logger;

public class LoggerObject {
	private static Logger eLogger=Logger.getLogger("ErrorLog");
	private static Logger iLogger=Logger.getLogger("InfoLog");
	public static void errorLog(Object o){
		eLogger.error(o);
	}
	public static void infoLog(Object o){
		iLogger.info(o);
	}
}