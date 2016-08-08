package com.alacriti.warehousemanagment.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.alacriti.warehousemanagment.delegates.ManagerSecurityFilter;
import com.alacriti.warehouseservices.resources.HelloWorld;

@ApplicationPath("/manager")
public class ClientApplication extends Application {
	@Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> set=new HashSet<Class<?>>();
		set.add(ViewResource.class);
		set.add(PostResource.class);
		set.add(ManagerSecurityFilter.class);
		return set;
    }
}