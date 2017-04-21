package cn.kyrioscraft.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author kyrioscraft
 */
@Configuration
@ComponentScan("cn.kyrioscraft.web.task")
@EnableScheduling//开启对计划任务的支持
public class ScheduleConfig {
}
