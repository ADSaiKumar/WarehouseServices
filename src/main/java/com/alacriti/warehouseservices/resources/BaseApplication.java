package com.alacriti.warehouseservices.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/services")
public class BaseApplication extends Application {
	@Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> set=new HashSet<Class<?>>();
		set.add(WarehouseResource.class);
		return set;
    }
}