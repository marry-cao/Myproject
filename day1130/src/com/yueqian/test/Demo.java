package com.yueqian.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class Demo {
	static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	public static void main(String[] args) {
	  //使用 流程引擎配置管理对象
//			ProcessEngineConfiguration processEngineConfiguration = 
//					ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
//			
//			
//			
//			// 设置  jdbc 的一些属性
//			processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
//			processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_demo?characterEncoding=utf-8");
//			processEngineConfiguration.setJdbcUsername("root");
//			processEngineConfiguration.setJdbcPassword("");
//			
//			
//			// 设置建表策略
//			
//			
//			/*
//			 * 
//			 * 
//			 * 
//			 *  如果表不存在,那么会直接抛出异常 !
//			 * DB_SCHEMA_UPDATE_FALSE = "false";
//			 * 
//			 * 
//			 * 先创建表 再去删除  ->废物 
//			 * DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";
//			 * 
//			 * 
//			 * 
//			 *  如果有表就直接使用,如果没有表就创建
//			 * public static final String DB_SCHEMA_UPDATE_TRUE = "true";
//			 * 
//			 **/
//			
//			
//			processEngineConfiguration.setDatabaseSchemaUpdate("true");
//			
//			
//			
//			
//			// 通过流程殷勤配置管理对象获取到 流程引擎对象
//			ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
//			System.out.println(processEngine);
//			
//		RepositoryService repositoryService =processEngine.getRepositoryService();
//		Deployment deploy = repositoryService.createDeployment().addClasspathResource("dirgrm/ExclusiveGateway.bpmn").addClasspathResource("dirgrm/ExclusiveGateway.png").name("流程测试-网关2").deploy();
//		System.out.println("name:"+deploy.getName());
//		System.out.println("id:"+deploy.getId());
//			fun11();
//		fun();
//		fun8();
//		try {
//			fun9();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		fun3();
//		fun4();
//		fun5(); 
//		fun6();  
//		fun7(); 
//		fun12();
	}
//	@Test 
//	public  void fun2() {
//		 
//		R epositoryService repositoryService =processEnginea.getRepositoryService();
//		De ployment deploy = repositoryService.createDeployment().addClasspathResource("dirgrm/MyProcess.png.bpmn").addClasspathResource("dirgrm/MyProcess.png.png").name("流程设置测试").deploy();
//		Sys tem.out.println("name:"+deploy.getName());
//		Syst em.out.println("id:"+deploy.getId());
//		 
//		 
//	}
//	
	public static void fun3() {
		 String processDefinitionKey = "exclusiveGateway";
		  ProcessInstance processInstance = processEngine.getRuntimeService()
		  .startProcessInstanceByKey(processDefinitionKey);
//流程定义id
		  System.out.println("流程定义id:"+processInstance.getProcessDefinitionId()); //
		  // 流程实例id
		  System.out.println("流程实例id:"+processInstance.getProcessInstanceId());
	}
	public static void fun4() {
		String name="小明";
		List<Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee(name).list();
		if(list!=null&&list.size()>0) {
			for (Task task : list) {
				System.out.println(""+task.getId());
				System.out.println(""+task.getAssignee());
				System.out.println(""+task.getName());
				System.out.println(""+task.getCreateTime());
			}
		}
		
		
	}
	public static void fun5() {
		String id="3904";
		Map<String,Object> map=new HashMap<String,Object >();
		map.put("money", "10000");
		processEngine.getTaskService().complete(id,map);
		System.out.println("任务已完成");
	}
	public static void fun6() {
		InputStream in = Demo.class.getClassLoader().getResourceAsStream("dirgrm/helloworld.zip");
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ZipInputStream zipInputStream=new ZipInputStream(in);
		Deployment deploy = repositoryService.createDeployment().name("zip部署流程").addZipInputStream(zipInputStream).deploy();
		System.out.println("id:"+deploy.getId());
		System.out.println("name:"+deploy.getName());
	}                                                  

	
	
public static void fun7() {
	List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc().list();
	if(list!=null&&list.size()>0) {
		for (ProcessDefinition pd : list) {
			System.out.println("版本:"+pd.getVersion());
			System.out.println("id:"+pd.getId());
			System.out.println("name:"+pd.getName());
		}
		
	}
}
public static void fun8() {
	processEngine.getRepositoryService().deleteDeployment("2701",true);
	System.out.println("流程定义删除成功");
}
public static void fun9() throws Exception {
	String id="1";
	String name="";
	List<String> names = processEngine.getRepositoryService().getDeploymentResourceNames(id);
	if(names!=null&&names.size()>0) {
		for (String s : names) {
			if(s.indexOf(".png")>-1) {
				name=s;
			}
		}
	}
	InputStream resourceAsStream = processEngine.getRepositoryService().getResourceAsStream(id, name);
	FileUtils.copyInputStreamToFile(resourceAsStream, new File("D:"+name));
}
public static void fun() {
	String Ann="小明";
	List<HistoricTaskInstance> list = processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskAssignee(Ann).list();
	if(list!=null&&list.size()>0) {
		for (HistoricTaskInstance hs : list) {
			System.out.println(hs.getName());
			System.out.println(hs.getAssignee());
			System.out.println(hs.getStartTime());
		}
	}
	
}
public static void fun10(){
	String taskId="1004";
	String variableName="money";
	Object value="1200";
	TaskService taskService = processEngine.getTaskService();
	taskService.setVariable(taskId, variableName, value); 
}
public static void fun11() {
	String taskId="1202";
	String variableName="money";
	TaskService taskService = processEngine.getTaskService();
	Object variable = taskService.getVariable(taskId, variableName);
	System.out.println(variable);
}
public static void fun12() {
	String str="a,b,c,,e,,f";  
	String []a= str.split(",");
	System.out.println(a.length);
	Long  l =200L;
	Long l1=200L;
	System.out.println(l.equals(l1));
}
}
