package com.maxim.web;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class startAppOnServer  {
	public static void main(String[] args) throws Exception{
		String webappDirLocation = "web/src/main/webapp/";
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("",
				new File(webappDirLocation).getAbsolutePath());

		//declare an alternate location for your "WEB-INF/classes" dir:
		File additionWebInfClasses = new File("web/target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		ctx.setResources(resources);

		tomcat.start();
		tomcat.getServer().await();
	}
}