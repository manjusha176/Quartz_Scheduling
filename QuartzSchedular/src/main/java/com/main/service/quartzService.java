package com.main.service;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import com.main.HelloJob;

@Service
public class quartzService 
{
    public void tringgerAndSchedular(String seconds)
    {
    	try {
    		
    		System.out.println(seconds+ "type:  "+ seconds.getClass().getName());
    		
    		//String crondata = "20 * * * * ?";
    		String crondata = seconds;

    		Date startTime = DateBuilder.nextGivenSecondDate(null, 10);
    		
	    	//specify jobs details
	    	JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("TestHelloJob").build();
	    	
	    	//specify running period of job (trigger)
	    	//simple trigger
//	    	Trigger trigger = TriggerBuilder.newTrigger()
//	    									.withSchedule(SimpleScheduleBuilder.simpleSchedule()
//	    																		.withIntervalInSeconds(seconds)
//	    																		.repeatForever())
//	    									.build();
	    	
	    	//run every 20 seconds infinite loop
	    	//cron trigger
	    	CronTrigger crontrigger = TriggerBuilder.newTrigger()
	    											.withIdentity("cron trigger test")
	    											.startAt(startTime)
	    											.withSchedule(CronScheduleBuilder.cronSchedule(crondata))
	    											.build();
	    	
	    	
	    	SchedulerFactory scFactory = new StdSchedulerFactory();
			Scheduler sch = scFactory.getScheduler();
			sch.start();
			
			//simple trigger call
			//sch.scheduleJob(job,trigger);
			//cron trigger call
			sch.scheduleJob(job,crontrigger);
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
