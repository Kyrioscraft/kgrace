package cn.kyrioscraft.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author kyrioscraft
 */
@Configuration
//@EnableWebMvc   //该注解自定义配置资源路径，开启时会影响webjar导入
@EnableAutoConfiguration
@ComponentScan("cn.kyrioscraft")
@MapperScan(basePackages = "cn.kyrioscraft.data.repository.mybatis")
@EnableJpaRepositories(basePackages = "cn.kyrioscraft.data.repository.jpa")
@EntityScan(basePackages = "cn.kyrioscraft.data.model.entity")
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //websocket controller
        registry.addViewController("/ws").setViewName("/ws");
        //index config
//        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/").setViewName("login");
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/loginPage").setViewName("login");
        registry.addViewController("/accessDenied").setViewName("accessDenied");
        super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
    }

    //跨域支持，也可以在接口上添加注解@CrossOrigin(origins = "*", maxAge = 3600)
    @Override
    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/service/*").allowedOrigins("*");
//        registry.addMapping("/system/*").allowedOrigins("*");
    }
//    @Bean
//    MappingJackson2HttpMessageConverter converter() {
//        //Set HTTP Message converter using a JSON implementation.
//        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
//        // Add supported media type returned by BI API.
//        List supportedMediaTypes = new ArrayList();
////        supportedMediaTypes.add(new MediaType("text", "plain",Charset.forName("UTF-8")));
//        supportedMediaTypes.add(new MediaType("application", "json", Charset.forName("UTF-8")));
//        jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
//        return jsonMessageConverter;
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(converter());
//    }



}
