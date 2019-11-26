package br.com.rest.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.rest.api.LoginApi;

@ApplicationPath("v1")
public class AppConfig extends Application{

	private Set<Class<?>> resources = new HashSet<Class<?>>();
	
	public AppConfig() {
		System.out.println("AppConfig Criado!");
		resources.add(LoginApi.class);
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		return resources;
	}
		

}
