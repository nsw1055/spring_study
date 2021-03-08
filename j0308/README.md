# 스프링 5일차

* 게시판의 종류
1. Form 태그 중심  
2. Ajax기반  

* 파라미터
1. 기본자료형
   (1) @RequestParam -> Default값을 주기 위해
   (2) @ModelAttribute
2. 객체형
   -> 생성자 / setter
   -> DTO
3. Model -> attribute
4. RecirectAttribute -> addFlashAttribute  

* 리턴타입

1. void -> url자체가 jsp이름
2. String -> 분기 / Redirect
3. ModelAndView
4. 순수객체 : JSON처리 @ResponseBody  

---  
이번 게시판에서는 JAVA configuration을 사용하며 Ajax를 혼용 할 예정이다.  
* 패키지  
1. dto
2. domain
3. mapper
4. service
5. controller  

* 순서
1. DB연결
2. MyBatis 세팅
3. Mapper 작성
4. 서비스 계층
5. 컨트롤러 계층
(1 ~ 3은 테스트 기반으로 작성후 junit 테스트를 징행한다. )  

* 실제 작업
1. pom.xml수정
   (1) spring-test
   (2) log4j/lombok 수정
   (3) jdk버전 수정
   (4) 플러그인 추가  
   ```
   <plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-war-plugin</artifactId>
			<version>3.2.0</version>
			<configuration>
            <failOnMissingWebXml>false</failOnMissingWebXml>
			</configuration>
	</plugin>
   ```
2. xml파일 삭제 (web, root-context, servlet-context)  
![image](https://user-images.githubusercontent.com/72544949/110262121-02c3c980-7ff6-11eb-8333-d831da42a0e5.png)

3. 패키지 생성  
![image](https://user-images.githubusercontent.com/72544949/110262335-b62cbe00-7ff6-11eb-9a09-fd0a255fa186.png)

4. CommonConfig.java 설계
```
package org.zerock.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j;

@Configuration
@Log4j
public class CommonConfig {
	
	@Bean
	public String sample() {
		log.info("sample...............");
		return "Common";
	}
	
}
```
  
5. CommonConfig.java 테스트  
![image](https://user-images.githubusercontent.com/72544949/110262536-5f73b400-7ff7-11eb-8e44-3b2c829ab9bd.png)
```
package org.zerock.main.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.common.config.CommonConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class})
@Log4j
public class ConfigLoadTests {

	@Test
	public void test1() {
		log.info("test1...................");
	}
}
```
여기까지 실행되면 bean이 생성되었다는 뜻이다.  
  
6. Jdbc관련 라이브러리를 pom.xml에 추가
   (1) mysql
   (2) hikari
   
7. CommonConfig.java에 bean설정
```
@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/dclass?serverTimezone=UTC");
		hikariConfig.setUsername("springuser");
		hikariConfig.setPassword("springuser");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		
		return dataSource;
	}
```

8.Autowired로 주입후 hikari테스트  
```
@Autowired
private DataSource ds;
	
@Test
public void test1() {
	log.info("test1...................");
	assertNotNull(ds);
}
```  
![image](https://user-images.githubusercontent.com/72544949/110264591-04dd5680-7ffd-11eb-8250-6114a66a3a90.png)  


9. mybatis 셋팅
   (1) mybatis 추가
   (2) mybatis-spring 추가
   
10. CommonConfig.java에 SqlSessionFactory빌드(mybatis가 로딩이 가능)
```
@Bean
public SqlSessionFactory sqlSessionFactory() throws Exception {
		
	SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	sessionFactory.setDataSource(dataSource());
		
	return sessionFactory.getObject();
}
```

8.Autowired로 주입후 세션을 맺을 수 있는지 없는지 테스트  
```
@Autowired
private SqlSessionFactory sqlSessionFactroy;

	@Test
	public void testSession() {
		
		SqlSession session = sqlSessionFactroy.openSession();
		log.info(session);
		
		session.close();
	}
```
위의 코드를 테스트하면  
Caused by: java.lang.ClassNotFoundException: org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
이런 오류가 발생

11. spring-jdbc라이브러리를 추가 후 다시 테스트  
![image](https://user-images.githubusercontent.com/72544949/110264638-1f173480-7ffd-11eb-9ac9-01e5fbd35e94.png)
  
  
12. 타임 패키지 추가  
![image](https://user-images.githubusercontent.com/72544949/110264733-4ff76980-7ffd-11eb-846e-5a30148a0456.png)

13. TimeConfig.java 생성  
![image](https://user-images.githubusercontent.com/72544949/110264819-7fa67180-7ffd-11eb-8f02-faaf0b90405d.png)

14. TimeMapper.java 생성  
![image](https://user-images.githubusercontent.com/72544949/110264914-bb413b80-7ffd-11eb-85a5-28e4b8eb8eea.png)

15. TimeConfig.java 작성
```
@Configuration
@Log4j
@MapperScan(basePackages = "org.zerock.time.mapper")
public class TimeConfig {
}
```

16. TimeMapper.java (i)작성
```
public interface TimeMapper {

	@Select("select now()")
	String getNow();
}
```
17. Time테스트  

```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, TimeConfig.class})
@Log4j
public class TimeTests {

	@Autowired
	TimeMapper timeMapper;
	
	@Test
	public void textExist() {
		
		log.info("--------------");
		log.info(timeMapper);
		
		log.info(timeMapper.getNow());
	}
}
```  
![image](https://user-images.githubusercontent.com/72544949/110265558-28a19c00-7fff-11eb-8131-fbd9019c2331.png)

18. TimeMapper.xml 생성  
![image](https://user-images.githubusercontent.com/72544949/110266614-8505bb00-8001-11eb-95ca-9a6119983823.png)

19. TimeMapper.xml 작성
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.zerock.time.mapper.TimeMapper">
	<select id = "getNow2" resultType="string">
		select now()
	</select>
</mapper>
```

20. TimeMapper.java에 코드 추가  
```
String getNow2();
```

21. 위의 코드들을 테스트
```
log.info(timeMapper.getNow2());
```
INFO : org.zerock.time.TimeTests - 2021-03-08 11:34:04  

22. service 패키지 생성  
![image](https://user-images.githubusercontent.com/72544949/110267118-7d92e180-8002-11eb-8dfa-f7bbaabb1f9a.png)
   
23. TimeService.java (i) 생성
```
package org.zerock.time.service;

public interface TimeService {

	String getTime();
}
```

24. TimeServiceImpl.java
```
@Service
@Log4j 
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

	private final TimeMapper timeMapper;
	
	@Override
	public String getTime() {
		log.info("getTime............");
		return timeMapper.getNow();
	}

}
```
25. TimeConfig.java 코드추가
```
@ComponentScan(basePackages = "org.zerock.time.service")
```

26. 위의 코드 테스트
```
@Autowired
TimeService timeService;

@Test
public void testService() {
	
   log.info(timeService.getTime());
}
```  
INFO : org.zerock.time.service.TimeServiceImpl - getTime............  
INFO : org.zerock.time.TimeTests - 2021-03-08 11:43:30  

27.ServletConfig.java 생성 (Servlet-context)  
![image](https://user-images.githubusercontent.com/72544949/110268055-62c16c80-8004-11eb-96ec-dff7739b3cf5.png)  
```
@EnableWebMvc
@ComponentScan(basePackages = "org.zerock.main")
public class ServletConfig implements WebMvcConfigurer {

	@Override
	  public void configureViewResolvers(ViewResolverRegistry registry) {

	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
	    bean.setViewClass(JstlView.class);
	    bean.setPrefix("/WEB-INF/views/");
	    bean.setSuffix(".jsp");
	    registry.viewResolver(bean);
	  }

	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {

	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	  }
}
```

28. WebConfig (Web.xml)
```
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/" };
	
		}
}
```
29. time.controller 패키지 추가  
30. TimeController.java 생성  
```
@Controller
@RequestMapping("/time")
@Log4j
@RequiredArgsConstructor
public class TimeController {

	private final TimeService service;
	
	@GetMapping("/now")
	public void getTime(Model model) {
		log.info("TimeController now..................");
		log.info(service);
		model.addAttribute("time", service.getTime());
	}
}
```

31. ServletConfig에서 componentscan을 해주기 위해 코드 수정
```
@ComponentScan(basePackages = {"org.zerock.main", "org.zerock.time.controller"})
```
32. WebConfig에 RootConfigClass추가
```
@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {CommonConfig.class, TimeConfig.class};
	}
```

33. now.jsp생성  
![image](https://user-images.githubusercontent.com/72544949/110270605-8935d680-8009-11eb-8cfe-174e38b9b142.png)

     
34. board 패키지 생성

35. BoardController.java 작성
```
@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@GetMapping({"/", "/list"})
	public String list() {
		log.info("list.........................");
		
		return "/board/list";
	}
}
```
36. ServletConfig.java에서 ComponentScan등록  
```
@ComponentScan(basePackages = {"org.zerock.main", "org.zerock.time.controller", "org.zerock.board.controller"})
```
  
37. views/board/list.jsp생성

여기까지가 기본셋팅

---

* 게시판 
1. DTO 개발 / VO 개발
2. 서비스 계층 설계 (생략하는경우도 있음)
3. Mapper 인터페이스 추가
4. 테스트
5. 서비스 계층에 주입
6. 테스트

* 작성 순서

1. board 패키지 작성
   (1) config
   (2) domain
   (3) dto
   (4) mapper
   
2. domain/Board.java 작성
```
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Board {
	private Integer bno;
	private String title, content, writer;
	private Date regDate, updateDate;
}
```
변경을 거의 하지 않아 생성자를 이용해 작성하고 getter를 사용해 읽는다.  

3. dto/BoardDTO.java
```
@Data
public class BoardDTO {
	private Integer bno;
	private String title, content, writer;
	private Date regDate, updateDate;	
}
```
getter와 setter에 자유롭다.

4. service/BoardService.java (i) 작성
```
public interface BoardService {

	default Board toDomain(BoardDTO dto) {
		
		return Board.builder().bno(dto.getBno())
		.title(dto.getTitle())
		.content(dto.getContent())
		.writer(dto.getWriter())
		.regDate(dto.getRegDate())
		.updateDate(dto.getUpdateDate()).build();
	}
	
}
```

5. mapper/BoardMapper
```
public interface BoardMapper {

	@Select("select * from tbl_board order by bno desc limit 0,10")
	List<Board> getList();
	
}
```
6. config/BoardConfig
```
@Configuration
@MapperScan(basePackages = "org.zerock.board.mapper")
@ComponentScan(basePackages = "org.zerock.board.service")
public class BoardConfig {

}

```
7. webConfig
```
@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {CommonConfig.class,
							TimeConfig.class,
							BoardConfig.class};
	}
```
8. test
```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, TimeConfig.class, BoardConfig.class})
@Log4j
public class BoardTests {

	@Autowired
	BoardMapper mapper;
	
	@Test
	public void testList() {
		
		mapper.getList().forEach(b -> log.info(b));
	}
}
```
