package org.zerock.board.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "org.zerock.board.mapper")
@ComponentScan(basePackages = "org.zerock.board.service")
public class BoardConfig {

}
