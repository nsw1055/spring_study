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
* 순서
1. DB연결
2. MyBatis 세팅
3. Mapper 작성
4. 서비스 계층
5. 컨트롤러 계층
(1 ~ 3은 테스트 기반으로 작성후 junit 테스트를 징행한다. )
