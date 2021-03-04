package org.zerock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//패키지 안의 어노테이션이 붙어있는 애들을 불러옴
@ComponentScan(basePackages = "org.zerock.service")
public class RootConfig {

}
