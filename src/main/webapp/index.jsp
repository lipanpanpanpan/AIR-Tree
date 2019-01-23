<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>注册</title>
		<style type="text/css">
			.row{
			color:#6666FF;
			}
		</style>
		<%
		pageContext.setAttribute("APP_PATH",request.getContextPath());
		%>
		<script type="text/javascript"
			src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
		<link
			href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
			rel="stylesheet">
		<script
			src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		
	</head>
	<!--  
	<body style="width:100%;
			height:100%;
			background-image:url(${APP_PATH }/image/2.png);" >
	-->
	<body>
		<div class="row">
			<div class="col-md-12">
				<h1>用户注册
					<span><img alt="" src="${APP_PATH }/image/air-balloon.png" ></span>
				</h1>
			</div>
		</div >
		<p align="center">
			<img alt="" src="${APP_PATH }/image/line.png" >
		</p>
		 <form class="form-horizontal" id="form">
		  <div class="form-group">
		    <label for="username_input" class="col-sm-2 control-label">用户名</label>
		    <div class="col-sm-10">
		      <input type="text" class="username"  name="username" id="username_input" placeholder="username">
		   	  <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="password_input" class="col-sm-2 control-label">密码</label>
		    <div class="col-sm-10">
		      <input type="password" class="password" name="password" id="password_input" placeholder="password">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="type_input" class="col-sm-2 control-label" >用户类型</label>
		    <div class="col-sm-10">
		     	<label class="radio-inline">
				  <input type="radio" name="type"  id="type1_input user-type" value="0" checked="checked"> 用户
				</label>
				<label class="radio-inline">
				  <input type="radio" name="type" id="type2_input user-type"  value="1"> 管理员
				</label>
				<div>
		        	<button type="button" class="btn btn-primary" id="regist_btn">注册</button>
		       		<input type="reset" class="btn btn-info" value="重置" />
				</div>
				
		     </div>
		  </div> 
		</form>
	
		
			<p align="right"><img alt="" src="${APP_PATH }/image/2.jpg" ></p>
		
		<script type="text/javascript">
			//校验表单数据
			function validate_add_form(){
				//1、拿到要校验的数据，使用正则表达式
				//获取输入文本框的value值
				var userName=$("#username_input").val();
				var regName = /(^[a-zA-Z0-9_-]{4,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
				//如果校验失败
				if(!regName.test(userName)){
					//alert("用户名可以是2-5位中文或者4-16位英文和数字的组合");
					//给该元素的父元素添加has-error的class属性使其提示
					show_validate_msg("#username_input","error","用户名可以是2-5位中文或者4-16位英文和数字的组合");
					return false;
				}else{
					show_validate_msg("#username_input","success","");
					
				};
				//2、校验密码信息
				var password=$("#password_input").val();
				var regPsw=/(^[a-zA-Z0-9_-]{6,18}$)|(^[\u2E80-\u9FFF]{2,5})/;
					//如果校验失败
					if(!regPsw.test(password)){
						//alert("密码必须是2-5位中文或者6-18位英文和数字的组合");
						show_validate_msg("#password_input","error","密码必须是2-5位中文或者6-18位英文和数字的组合");
						return false;
					}else{
					
						show_validate_msg("#password_input","success","");
					}
				return true;
			}
	
			
			//抽取出来的显示校验结果的提示信息
			function show_validate_msg(ele,status,msg){
				//清除当前元素的校验状态
				$(ele).parent().removeClass("has-success has-error");
				$(ele).next("span").text("");
				if("success"==status){
					$(ele).parent().addClass("has-success");
					$(ele).next("span").text(msg);
				}else if("error"==status){
					$(ele).parent().addClass("has-error");
					$(ele).next("span").text(msg);
				}
			}
			
			//校验用户名是否可用
			$("#username_input").change(function(){
				
				//发送ajax请求保存用户信息
				var username=this.value;//输入框的值
				$.ajax({
					url:"${APP_PATH}/checkuser",
					data:"username="+username,
					type:"POST",
					success:function(result){
						if(result.code==100){
							show_validate_msg("#username_input","success","用户名可用");
							$("#login_btn").attr("ajax-va","success");
						}else{
							show_validate_msg("#username_input","error",result.extend.va_msg);
							$("#login_btn").attr("ajax-va","error");
						}
					}
				});
			});
			
			//点击保存，保存用户信息
			$("#regist_btn").click(function(){
				//1、模态框中填写的表单数据提交给服务器进行保存
				//先对要提交给服务器的数据进行校验(前端校验)
				if(!validate_add_form()){
				 return false;	
				};
				
				//1、判断之前的ajax用户名校验是否成功，如果成功，则不继续判断下一个
				if($(this).attr("ajax-va")=="error"){
					return false;
				}
		
				//2、发送ajax请求保存用户
				$.ajax({
					url:"${APP_PATH}/user",
					type:"POST",
					//表格序列化后的数据
					data:$("#form").serialize(),
					success:function(result){
						//alert(result.msg);
						if(result.code==100){
							//用户保存成功；
							alert("保存成功");
							window.location.href="Menu.jsp";
						}else{
							//显示失败信息
							//console.log(result);
							//有哪个字段的错误信息就显示哪个字段的；
							if(undefined != result.extend.errorFields.username){
								//显示用户名错误信息
								show_validate_msg("#username_input", "error", result.extend.errorFields.username);
							}
							if(undefined != result.extend.errorFields.password){
								//显示密码的错误信息
								show_validate_msg("#password_input", "error", result.extend.errorFields.password);
							}
						}
						
					}
				});
			});
		
			

			
		</script>

</body>
</html>