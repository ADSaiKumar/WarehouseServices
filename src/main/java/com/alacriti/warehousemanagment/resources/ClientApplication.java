package com.alacriti.warehousemanagment.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.alacriti.warehouseservices.resources.HelloWorld;

@ApplicationPath("/manager")
public class ClientApplication extends Application {
	@Override
    public Set<Class<?>> getClasses() {
		Set<Class<?>> set=new HashSet<Class<?>>();
		set.add(ViewResource.class);
		set.add(PostResource.class);
		return set;
    }
}