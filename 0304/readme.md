spring 2일차

* 화면에 DataBase의 시간을 표출해 본다 
1. 순수 JDBC 연결 확인

//		JDBC 드라이버 확인

		Class.forName("com.mysql.cj.jdbc.Driver");
		
		log.info("1------------------------");
		
		String url = "jdbc:mysql://localhost:3306/dclass?serverTimezone=UTC";
		String username = "springuser";
		String password = "springuser";
		
//		커넥션 확인

		Connection con = DriverManager.getConnection(url, username, password);
		
		log.info(con);
		
		con.close();

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
