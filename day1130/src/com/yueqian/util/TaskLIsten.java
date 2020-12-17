package com.yueqian.util;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskLIsten implements TaskListener {
	

	@Override
	public void notify(DelegateTask delegateTask) {
		
		delegateTask.setAssignee("小白杨");
	}

}
