package com.yueqian.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

public class Demo1 {
	
	public static void main(String[] args) {
		ProcessEngine processEnginea = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService =processEnginea.getRepositoryService();
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("dirgrm/helloworld.bpmn").addClasspathResource("dirgrm/helloworld.png").name("第一个流程测试").deploy();
		System.out.println("name:"+deploy.getName());
		System.out.println("id:"+deploy.getId());
		
	}

}
