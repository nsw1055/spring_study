<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800">List Page</h1>

<div class = 'searchDiv dropdown'>
    <select name='stype' class='stype'>
        <option value =''>-----------</option>
        <option value ='t' ${pageDTO.type == "t"?"selected":""}>제목</option>
        <option value ='c' ${pageDTO.type == "c"?"selected":""}>내용</option>
        <option value ='w' ${pageDTO.type == "w"?"selected":""}>작성자</option>
        <option value ='tc' ${pageDTO.type == "tc"?"selected":""}>제목/내용</option>
        <option value ='tcw' ${pageDTO.type == "tcw"?"selected":""}>제목/내용/작성자</option>
    </select>
    <input name="skeyword" type="text" value="<c:out value=''/>">
    <button class="searchBtn">검색</button>
</div>


<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">제목</th>
        <th scope="col">작성자</th>
        <th scope="col">날짜</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list }" var="board">
        <tr class='listA' data-value='<c:out value="${board.bno }"/>'>
            <td>${board.bno }</td>
            <td>${board.title }</td>
            <td>${board.writer }</td>
            <td>${board.regDate }</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<ul class="pagination">
    <c:if test="${pageMaker.prev}">
        <li class="page-item"><a class="page-link"
                                 href="${pageMaker.start - 1}" tabindex="-1">Previous</a></li>
    </c:if>

    <c:forEach begin="${pageMaker.start }" end="${pageMaker.end }"
               var="num">

        <li class="page-item ${num == pageMaker.pageDTO.page? "active":"" }"><a
            class="page-link" href="${num}">${num }</a></li>

    </c:forEach>

    <c:if test="${pageMaker.next}">
        <li class="page-item"><a class="page-link"
                                 href="${pageMaker.end + 1 }">Next</a></li>
    </c:if>
</ul>

<form class='actionForm' action="/board/list" method="get">
    <input type="hidden" name="page" value="${pageDTO.page}">
    <input type="hidden" name="perSheet" value="${pageDTO.perSheet}">
    <input type="hidden" name="type" value="${pageDTO.type}">
    <input type="hidden" name="keyword" value="${pageDTO.keyword}">
</form>

<script>

    document.querySelector(".pagination").addEventListener("click" , e => {

        e.preventDefault()

        const target = e.target
        // console.log(target)
        const pageNum = target.getAttribute("href")
        console.log(pageNum)

        document.querySelector(".actionForm input[name='page']").value=pageNum
        document.querySelector(".actionForm").submit();
    },false)


    document.querySelectorAll('.listA').forEach(a => {
        a.addEventListener("click", function (e) {
            e.preventDefault()
            const bno = e.currentTarget.getAttribute("data-value");
            const actionForm = document.querySelector(".actionForm")
            actionForm.setAttribute("action", "/board/read")
            actionForm.innerHTML += "<input type='hidden' name='bno' value='"+bno+"'>";
            actionForm.submit();
        }, false);
    });

    document.querySelector(".searchBtn").addEventListener("click", function (e) {
        const actionForm = document.querySelector(".actionForm");

        // 검색타입 / 키워드
        const stype = document.querySelector(".stype")
        const idx = stype.selectedIndex
        const type = stype[idx].value
        actionForm.querySelector("input[name='page']").value = 1
        actionForm.querySelector("input[name='type']").value = type
        actionForm.querySelector("input[name='keyword']").value = document.querySelector("input[name='skeyword']").value
        actionForm.submit();

    });
</script>

<%@include file="../includes/footer.jsp"%>