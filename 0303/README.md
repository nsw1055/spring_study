# spring 2일차

* 화면에 DataBase의 시간을 표출해 본다 
1. 순수 JDBC 연결 확인

	JDBC 드라이버 확인
```
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		log.info("1------------------------");
		
		String url = "jdbc:mysql://localhost:3306/dclass?serverTimezone=UTC";
		String username = "springuser";
		String password = "springuser";
```		
	커넥션 확인
```
		Connection con = DriverManager.getConnection(url, username, password);
		
		log.info(con);
		
		con.close();
```
2. HikariCP 세팅 - root-context.xml 혹은 Java설정<br>

	```
	<!-- Root Context: defines shared resources visible to all other web components -->
	<bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>
		<property name="jdbcUrl" value="jdbc:mysql://localhost:3306/dclass?serverTimezone=UTC"></property>
		<property name="username" value="springuser"></property>
		<property name="password" value="springuser"></property>
	</bean>

	<!-- HikariCP configuration -->
	<bean id="dataSource" class="com.zaxxer.hikari.HikariDataSource"
		destroy-method="close">
		<constructor-arg ref="hikariConfig" />  
	</bean>	
	```
HikariCP git 주소: https://github.com/brettwooldridge/HikariCP

3. Spring 이용해서 확인

4. DAO제작
-TimeMapper.java

	```
	public interface TimeMapper {

	@Select("select now()")
	String getTime();
	
	}
	```
	```
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
	@Log4j
	public class TimeMapperTests {
	
		@Autowired
		TimeMapper timeMapper;
		
		@Test
		public void testTime() {
			log.info(timeMapper);
		
			log.info(timeMapper.getTime());
		}
	
		
	}
	```
5. HomeController에 주입 / 확인

  <br>
  <br>
* Mybatis(미니멈) 셋팅

1. maven 추가<br>
	(1) https://mvnrepository.com/artifact/org.mybatis/mybatis <br>
	(2) https://mvnrepository.com/artifact/org.mybatis/mybatis-spring<br>
2. root-context.xml 라이브러리 추가
	```
	<bean id="sqlSessionFactory"
		class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	```
3. root-context.xml 상단 변경
	```
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
		xsi:schemaLocation="http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring-1.2.xsd
			http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
			http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">
	
	```  
<br>
<br>  
* Annotation (@)

html의 경우 정해져 있는 속성만 존재했었다 하지만 추가적인 속성을 넣기위해 "data-XXX"라는 속성을 만들었다.
java에서도 비슷한 경우가 있는데
java는 객체를 DB로 옮길경우 회원을 만들때 "Member" class를 생성  
```
	String id, pw;  
	Data birth;  
```
DB의 테이블을 만들때는 cloumn 단위가 varchar2(50)등을 사용하기때문에 단위가 다른것을 알 수 있다.

단위를 다른데 어떻게 객체를 옮길 수 있을까? 에서 나온것이 Annotation이다.(기존에는 XML로 옮겼으나 코드가 복잡해짐)

doA()와 doB()의 타입이 다를때는 인터페이스를 사용해서 해결했다.
하지만 doA에 메서드가 있을때는 인터페이스에 dummy메서드를 생성하여 타입을 맞춰 주어야 했다.
그래서 타입에 사로잡혀있었는데 이것을 어노테이션이 무너뜨렸다.

그럼 어떻게 타입을 무너뜨렸을까?

예를 들어 스타워즈로 비유해보자 등장인물이 나올때 등장인물에 맞는 테마곡이 나온다
그럼 우리가 타입에 상관없이 등장인물이 나오면 테마곡이 나오게 만들어 보자

```
@Target(value={ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface BGM{

	
}

```

```
@BGM(song = "Love Them")
public class Luke {

	//Force를 사용할때는 밑의 테마곡을 사용한다.
	@BGM(song = "Come and Get your Love" )
	public void use Force(){
		System.out.println("");
	}
}
```


```
@BGM(song = "Emperial Match")
public class Vader {

	public void use Force(){
		System.out.prinln("");
	}
}
```

```
public class Main{

	public static void main(String[] args) {
		Vader p1 = new Vader();
		
		Class clz = p1.getClass();
		
		BGM bgm = (BGM) clz.getAnnotation(BGM.class);
		
		Sysyem.out.println(bgm.song());
	}

}
```

```
public class Main{

	public static void main(String[] args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		Class clz = Class.forName(scanner.nextLine());
		
		Object obj = clz.newInstance();
		
		BGM bgm = (BGM) clz.getAnnotation(BGM.class);
		
		Sysyem.out.println(bgm.song());
	}
}
```
Luke와 Vader은 타입이 다르지만 어노테이션을 사용하여 타입을 비슷하게 만들어 사용하였다.

따라서 어노테이션은 인터페이스를 파괴한다.
어노테이션을 사용하면 인터페이스처럼 타입이 정해진 방식이 아니라 유연하게 사용 할 수 있으며 이것을 사용한 것이 spring이다.




# spring 3일차

목표 :  
1. XML제거  
2. 서버를 실행 하지 않고 테스트 코드를 이용하여 데이터 코드를 내보낸다.

* spring mvc  
  
기본적인 구조<br>
![springmvcstructure](https://user-images.githubusercontent.com/72544949/109905261-e0723900-7ce1-11eb-97f3-57ca5a7cbdac.jpg)
<br>
HandleMapping은 request에 해당하는 Controller을 return 한다.(기본적으로 URLHandlerMapping)  
컨트롤러 안에있는 @RequestMapping은 URL을 지정한다.  
컨트롤러는 로직을 수행 하고 결과데이터를 ModelAndView에 반영한다.
```
	@RequestMapping("/doA")
	public void doA(Model model) {
		log.info("doA.....");
		String now = timeMapper.getTime2();
		log.info(now);
		model.addAttribute("time", now); //request.setAttribute와 같다
	}
```
ViewResolver가 해석을 하여 View를 어떻게 할것인지 정한다.  
servlet-context.xml을 보면 어떻게 해석하여 jsp로 넘기는지 알 수 있다.
```
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
```

1. front controller 패턴이란?  

흐름을 강제적으로 만드는 패턴으로 spring 1, 2버전에서 사용하였던 방식으로 모든 리퀘스트를 받아 흐름을 제어하는 역할을 하였다.  
아직도 web.xml(front controller)에는 그때의 잔재가 남아있다.  

"하지만 어노테이션이 등장하고 이제는 상속을 받지 않고 어노테이션만 받아서 사용한다."  

2. web.xml 동작
서블릿이나 jsp는 처음 호출 했을 때 만들어진다는 것을 알고서 밑의 web.xml코드의 동작을 보도록 하자  
  
첫번째로 root-context를 동작시킨다.
```
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param> 
```
  
두번째로는 servlet-context.xml을 동작시킨다..
```
	<servlet>
		<servlet-name>appServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
```
동작된 servlet-context.xml의 코드의 마지막줄을 보면 밑의 코드가 나오는데
```
	<context:component-scan base-package="org.zerock.controller" />
```
compnent-scan을 통해 HomeController와 SampleController을 호출 하여 등록한다.

등록된 컨트롤러를 와이어링 시킨다.
```
@Controller
@Log4j
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

	private final TimeMapper timeMapper;
	
	@RequestMapping("/doA")
	public void doA(Model model) {
		log.info("doA.....");
		String now = timeMapper.getTime2();
		log.info(now);
		model.addAttribute("time", now);
	}
}
```
TimeMapper 인터페이스에는 어노테이션을 걸지 않았는데 어떻게 동작하는가?
```
public interface TimeMapper {

	@Select("select now()")
	String getTime();

	
	String getTime2();
	
}
```
클래스를 만들어 객체를 생성한다. 어노테이션을 걸려면 객체에 걸어야하는데 자동으로 객체를 만들기 때문에 걸 수가 없다. 하지만 프록시 객체로 빈으로 등록되게 된다.

어제는 TimeMapper에 @Autowired를 걸어 TimeMapper의 타입에 해당하는 빈과 객체를 연결하였다.  

root-context.xml
```
	<mybatis-spring:scan base-package="org.zerock.mapper"/>
```
TimeMapperTests.java
```
	@Autowired
	TimeMapper timeMapper;
```
