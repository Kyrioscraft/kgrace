package cn.kyrioscraft.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Author Kyrioscraft
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(this.getClass());
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
