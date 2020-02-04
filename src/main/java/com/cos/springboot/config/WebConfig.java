package com.cos.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

//web.xml 파일을 자바파일로 바꾼거 configuration 이랑 webmvcconfigurer 붙이기

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${file.path}")
	private String fileRealPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);

		// 파일 경로 인식하게 하기
		registry.addResourceHandler("/media/**").addResourceLocations("file:///" + fileRealPath).setCachePeriod(3600)
				.resourceChain(true).addResolver(new PathResourceResolver());
		// 이미지 받은지 얼마 안됫는데 또 받을 때 다운안받고 캐쉬쓰게하기- setCachePeriod 이거 제외하곤 필수
		//여기서 핸들러 경로..정도만 알고잇으면됨

	}

}
