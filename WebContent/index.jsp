<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
		pageContext.setAttribute("path", request.getContextPath());
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<%-- <form enctype="multipart/form-data" action="${path }/user/upload" method="post"> --%>
<form id="formdata1" enctype="multipart/form-data">
头像：<input type="file" class="file" name="file" value="" id="file0"><br>
姓名：<input class="username"  value=""><br>
</form>
<button onclick="submit()"  value="提交 ">提交</button>
<!-- </form> -->
<br>
<br>
<br>

</body>
<script type="text/javascript">
 function submit() {
    var lengt = $("form").length;
    
         var arr_names = new Array();
         var arr_files = new Array();
    	 var formData = new FormData();
    	/*  for(var i=0;i<$(".username").length;i++){ */
    	formData.append("file", $("#file0")[0].files[0]);
        formData.append("username",$(".username").val());
    	 /* } */
    	 $.ajax({
             url: "${path }/user/upload",
             type: "POST",
             data:formData,
             contentType:false,
             processData:false,//这个很有必要，不然不行
             dataType:"json",
             mimeType:"multipart/form-data",
             success: function(data){
				alert("success")
             },
             error: function(res){
                 alert(res.responseText);
             }
         }); 
     
    	
     
 }

</script>
</html>