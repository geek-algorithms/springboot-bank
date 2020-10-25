package com.geek.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration标注在类上，相当于把该类作为spring的xml配置文件中的<beans>
// 作用为：配置spring容器(应用上下文)
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * 自动转换时间格式
     *
     * @param registry date
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }
}
