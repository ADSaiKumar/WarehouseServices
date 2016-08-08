package com.alacriti.warehouseservices.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.alacriti.warehouseservices.delegates.ServiceSecurityFilter;

@ApplicationPath("/services")
public class BaseApplication extends Application {
	@Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> set=new HashSet<Class<?>>();
		set.add(WarehouseResource.class);
		set.add(UserResource.class);
		set.add(ServiceSecurityFilter.class);
		return set;
    }
}