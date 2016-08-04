package com.alacriti.warehousemanagment.bussinesslogics;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alacriti.warehousemanagment.resources.ViewResource;
import com.alacriti.warehouseservices.vo.LoggerObject;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class PageSetter {
	public static final int DASHBOARD=0;
	public static final int ADDSTOCK=1;
	public static final int CHECKOUTSTOCK=2;
	public static final int SEARCH=3;
	public static final int ORDER=4;
	private String[] links;
	private String[] classes;
	private Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
	private Writer writer;
	
	public PageSetter(){
		configuration.setClassForTemplateLoading(ViewResource.class, "/templetes");
		classes=new String[]{"","","","",""};
		links=new String[]{"http://localhost:8080/WarehouseServices/manager/dashboard",
				"http://localhost:8080/WarehouseServices/manager/addstock",
				"http://localhost:8080/WarehouseServices/manager/checkoutstock",
				"http://localhost:8080/WarehouseServices/manager/search",
				"http://localhost:8080/WarehouseServices/manager/orders"};
	}
	public String templateLoader(int activeClass,String templateFile,Map<?,?> dataModel){
		writer=new StringWriter();
		String data;
		try {
			Template template = configuration.getTemplate(templateFile);
			template.process(dataModel, writer);
			String divData=writer.toString();
			writer.close();
			data=frameSetter(activeClass, divData);
			return data;
		} catch (Exception e1) {
			LoggerObject.errorLog(e1);
		}
		return null;
	}
	public String frameSetter(int activeClass,String divData){
		Map<String,String> dataModel = new HashMap<String,String>();
		classes[activeClass]="active-page";
		links[activeClass]="#";
		dataModel.put("userName","SaiKUmar");
		dataModel.put("dClass",classes[0]);
		dataModel.put("aClass",classes[1]);
		dataModel.put("cClass",classes[2]);
		dataModel.put("sClass",classes[3]);
		dataModel.put("oClass",classes[4]);
		dataModel.put("dLink",links[0]);
		dataModel.put("aLink",links[1]);
		dataModel.put("cLink",links[2]);
		dataModel.put("sLink",links[3]);
		dataModel.put("oLink",links[4]);
		dataModel.put("divData",divData);
		writer=new StringWriter();
		String data;
		try {
			Template template = configuration.getTemplate("frame.ftl");
			template.process(dataModel, writer);
			data=writer.toString();
			writer.close();
			return data;
		} catch (Exception e1) {
			LoggerObject.errorLog(e1);
		}
		return null;
	}
	public String getRawTempleteString(String templateFile,Map<?,?> dataModel){
		writer=new StringWriter();
		String data;
		try {
			Template template = configuration.getTemplate(templateFile);
			template.process(dataModel, writer);
			data=writer.toString();
			writer.close();
			return data;
		} catch (Exception e1) {
			LoggerObject.errorLog(e1);
		}
		return null;
	}
}
