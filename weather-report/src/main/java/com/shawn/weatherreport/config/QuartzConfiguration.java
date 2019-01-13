package com.shawn.weatherreport.config;


import com.shawn.weatherreport.quartz.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Date: 2019/1/13 0013 12:57
 * @Author: shawn
 * @Description: TODO
 */
@Configuration
public class QuartzConfiguration {

    private static final int TIME = 1800; // 更新频率
    //JobDetail Bean
    @Bean
    public JobDetail weatherDataSyncJob(){
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob")
                .storeDurably().build();
    }

    // Trigger
    @Bean
    public Trigger weatherDataSyncTrigger() {

        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(TIME).repeatForever();

        return TriggerBuilder.newTrigger().forJob( weatherDataSyncJob())
                .withIdentity("weatherDataSyncTrigger").withSchedule(simpleScheduleBuilder).build();
    }

}
