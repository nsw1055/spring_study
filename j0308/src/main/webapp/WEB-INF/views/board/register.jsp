<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800">Blank Page</h1>


<div class="modal" id="registerModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Modal body text goes here.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary">Save changes</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="movePage()">Close</button>
			</div>
		</div>
	</div>
</div>

<script>
function movePage() {
    self.location="/board/list"
}

    function sendAjax(data){
        console.log("sendAjax....", data);

        return fetch("/board/register",  
        		{method:"post",
        		headers:{'Content-Type':'application/json'},
            	body: JSON.stringify(data)})
            .then(res => {
            if(!res.ok){
                throw new Error(res)
                return;
            }
            return res.text()
        })
            .catch(res => {
            console.log("catch............................")
            console.log(res)
        })
    }
    const  data = {title:"한글제목", content:"게시물 내용", writer:"user00"};

    const fnResult = sendAjax(data);

    fnResult.then(result=>{
    	console.log("RESULT:" + result)
    	$("#registerModal").modal('show')
    })
</script>

<h3>${pageMaker }</h3>

	
<%@include file="../includes/footer.jsp"%>
