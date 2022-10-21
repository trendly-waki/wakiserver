package com.example.wakiserver.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@SuppressWarnings("unchecked")
public class SwaggerConfig  extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket allApi() {
        return getDocket("전체", Predicates.or(
                PathSelectors.regex("/*.*")));
    }

//    @Bean
//    public Docket productApi() {
//        return getDocket("유저", Predicates.or(
//                PathSelectors.regex("/user.*")));
//    }

    public Docket getDocket(String groupName, Predicate<String> predicate) {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("JWT") //헤더 이름
                .description("JWT 입력칸") //설명
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo()).globalOperationParameters(aParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.wakiserver"))
                .paths(predicate)
                .apis(RequestHandlerSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Waki API")
                .version("1.0.0")
                .description("Waki api 리스트 입니다.")
                .build();
    }
}

