package com.ssafy.happyhouse.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.ssafy.happyhouse.model.mapper")
public class WebMvcConfiguration implements WebMvcConfigurer {

//	@Autowired
//	private LoginConfirmInterceptor loginConfirm;
//	@Autowired
//	private AdminInterceptor adminConfirm;
//
//	private final List<String> patterns = Arrays.asList("/bookmark/*", "/member/mypage/*", "/member/update/*",
//			"/member/delete/*", "/notice/write", "/notice/update/*", "/notice/delete/*");
//	private final List<String> admin = Arrays.asList("/notice/write", "/notice/update/*", "/notice/delete/*");
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//
//		registry.addInterceptor(loginConfirm).addPathPatterns(patterns);
//		registry.addInterceptor(adminConfirm).addPathPatterns(admin);
//
//	}
}
