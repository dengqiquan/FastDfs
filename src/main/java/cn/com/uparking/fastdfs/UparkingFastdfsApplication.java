package cn.com.uparking.fastdfs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by QinMing on 2017/06/27.
 */
@SpringBootApplication
@EnableSwagger2
public class UparkingFastdfsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UparkingFastdfsApplication.class, args);
    }

    @Value("${MAX_FILE_SIZE}")
    private String MAX_FILE_SIZE;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(MAX_FILE_SIZE);
        factory.setMaxRequestSize(MAX_FILE_SIZE);
        return factory.createMultipartConfig();
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/fdfs.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Uparking FastDFS API")
                .contact("qinming@uparking.com")
                .version("1.0")
                .build();
    }
}
