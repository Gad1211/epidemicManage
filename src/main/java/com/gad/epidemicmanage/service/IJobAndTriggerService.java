package com.gad.epidemicmanage.service;

import org.quartz.JobDataMap;

/**
 * @Description quartz定时任务service类
 */
public interface IJobAndTriggerService {


	/**
	 * 添加任务
	 * @param jobName
	 * @param jobClassName
	 * @param jobGroupName
	 * @param cronExpression
	 * @return
	 */
	boolean addJob(String jobName, String jobClassName, String jobGroupName, String cronExpression,JobDataMap map);

	/**
	 * 添加单次任务
	 * @param jobName
	 * @param jobClassName
	 * @param jobGroupName
	 * @return
	 */
	boolean addOnceJob(String jobName, String jobClassName, String jobGroupName, JobDataMap map);

	/**
	 * 暂停任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @return
	 */
	boolean pauseJob(String jobClassName, String jobGroupName);


	/**
	 * 从暂停中恢复任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @return
	 */
	boolean resumeJob(String jobClassName, String jobGroupName);


	/**
	 * 修改更新任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @param cronExpression
	 * @return
	 */
	boolean rescheduleJob(String jobClassName, String jobGroupName, String cronExpression);


	/**
	 * 删除任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @return
	 */
	boolean deleteJob(String jobClassName, String jobGroupName);

}
