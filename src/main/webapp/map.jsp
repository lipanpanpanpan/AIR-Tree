<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
	<!--  
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	-->
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wDYEcxgRRheZwyC9jpN1Tt7fzr2zjosZ"></script>
	<title>POI搜索</title>
	<style type="text/css">
				body, html{
			width: 100%;
			/*height: 100%;*/
			margin:0;
			font-family:"微软雅黑";
			padding: 0;
	        font-size: 13px;
			/*overflow: hidden;*/
			}
		.header{
			width:100%
			height:40px;
			/*background-color: lightgreen;*/
			background-color: #d6d6d6;
			/**设置居中**/
			margin:5px auto;
			font-size:30px;
			color:blueviolet;
			padding:1px 0;
			text-align: center;
			/*设置为背景图片*/
 /*
			background-image:url(img/bg.gif);
			/*设置水平方向重复*/
			/*background-repeat: repeat-x;*/
			
		}
		#allmap{
			height:550px;
			width:100%;
			}

	    #outer-box {
	        /*height: 100%;*/
	        padding-right: 300px;
	        width: 100%;
	        
	    }
	    
	    #container {
	        width: 100%;
	        overflow:auto;
	    }

	    #POI-result{
	    	height: 30px;
	        background: #ccc;
	    }
	    
	    #r-result {
	        /*height: 30px;
	        background: #ccc;*/
			font-size:14px;
	        position: absolute;
	        top: 50px;
	        /*left:1000px;*/
	        bottom: 0;
	        right: 0;
	        height: 60px;
	        overflow: auto;
	        zoom:1;
	        width: 300px;
	        z-index: 999;
	        border-left: 1px solid #cccccc;
	        background: #fff;
	        color:deeppink;
	        font-weight: bold;
	    }

	    #suggestId {
	        width: 100%;
	        height: 30px;
	        line-height: 20%;
	        -webkit-box-sizing: border-box;
	        box-sizing: border-box;
	        border:none;
	        border-bottom: 1px solid #ccc;
	        padding: 0 5px;
	    }
	    
	    #searchResultPanel {
	        overflow: auto;
	        zoom:1;
	        height: calc(100% - 60px);  
	    }
		   
	</style>

</head>
<body>


	<div class="header">POI空间兴趣点语义近似查询</div>
	<div id="allmap"></div>
	
	<div id="POI-result">
		经度: <input id="longitude" type="text" style="width:100px; margin-right:10px;" />
		纬度: <input id="latitude" type="text" style="width:100px; margin-right:10px;" />
		<input type="button" value="查询"  id="query"  onclick="theLocation()" />
		<button type="button"  id="go_back_btn">返回菜单页</button>
	</div>
	<div id="outer-box">
		<div id="container" class="map" tabindex="0">
			<div id="r-result" class="scrollbar1" >
		    	输入关键字搜索POI:
		    	<br />
		    	<input type="text" id="suggestId" size="20" placeholder="百度"  style="height: 100%;"/>
			</div>
		</div>
	  </div>
	  <div id="searchResultPanel"></div>

	 </body>
	<script type="text/javascript">
	

		// 百度地图API功能
		function G(id) {
			return document.getElementById(id);
		}
	
		
	 function init_map(){
		 map.centerAndZoom(new BMap.Point(116.404, 39.915),11);
			
			//开启滚轮缩放地图
			map.enableScrollWheelZoom(true);
			//开启地图惯性拖拽，默认禁用
			map.enableContinuousZoom(true);
			
			map.addControl(new BMap.NavigationControl());   
			map.addControl(new BMap.ScaleControl());   
			map.addControl(new BMap.OverviewMapControl());
			map.addControl(new BMap.MapTypeControl());
			
			var data_info;

			   $.ajax({
				   url:"${APP_PATH}/poiDatas",
				   type:"POST",
				   success:function(result){
					  data_info=new Array();
					   var poiDatas=result.extend.pageInfo.list;
					   
					   function addClickHandler(content,marker){
						   marker.addEventListener("click",function(e){
						   openInfo(content,e)}
						   );
						   }
						   function openInfo(content,e){
						   var p = e.target;
						   var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
						   var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
						   map.openInfoWindow(infoWindow,point); //开启信息窗口
						   }
					   
					   
					   $.each(poiDatas,function(index,item){ 
							   data_info[index]=new Array();
							   var poilng=item.poilng;
							   var poilat=item.poilat;
							   var description=item.description;
							   data_info[index][0] = poilng;
							   data_info[index][1]=poilat;
							   data_info[index][2]=description;
					   });
					   //console.log(data_info);
					   
					   var opts = {
							   width : 400,     // 信息窗口宽度
							   height: 100,     // 信息窗口高度
							   title : "描述信息" , // 信息窗口标题
							   enableMessage:true//设置允许信息窗发送短息
							   };
							   for(var i=0;i<data_info.length;i++){
							   var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
							   var content =data_info[i][2]+"坐标为：【"+data_info[i][0]+","+data_info[i][1]+"】";
							   map.addOverlay(marker);               // 将标注添加到地图中
							   addClickHandler(content,marker);
							   }
					   
				   }
			   });
			 
			
	 }

	 var map = new BMap.Map("allmap");//创建map实例
		init_map();
		//addMarker(ponits);
		
	/*
		//进行浏览器定位
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			// 定位成功事件
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				//alert('您的位置：'+r.point.lng+','+r.point.lat);
				var point = new BMap.Point(r.point.lng, +r.point.lat);
			}     
		},{enableHighAccuracy: true})
		//addEventListener--添加事件监听函数
		//click--点击事件获取经纬度
		map.addEventListener("click",function(e){
		    prompt("鼠标单击地方的经纬度为：",e.point.lng + "," + e.point.lat);
		});
*/
			// 用经纬度设置地图中心点
		function theLocation(){
			if(G("longitude").value != "" && G("latitude").value != ""){
				//map.clearOverlays(); 
				var new_point = new BMap.Point(G("longitude").value,G("latitude").value);
				//创建小狐狸
				var myIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/fox.gif", new BMap.Size(300,157));
				
				var marker = new BMap.Marker(new_point,{icon:myIcon});  // 创建标注
				map.addOverlay(marker);              // 将标注添加到地图中
				map.panTo(new_point);      
			}
		}
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "suggestId"
				,"location" : map
			});
		
			ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
			var str = "";
				var _value = e.fromitem.value;
				var value = "";
				if (e.fromitem.index > -1) {
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
				
				value = "";
				if (e.toitem.index > -1) {
					_value = e.toitem.value;
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
				G("searchResultPanel").innerHTML = str;
			});
		
			var myValue;
			ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
			var _value = e.item.value;
				myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
				
				setPlace();
			});
		
		
			function setPlace(){
				//map.clearOverlays();    //清除地图上所有覆盖物
				function myFun(){
				
					var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
					
					map.centerAndZoom(pp, 18);
					map.addOverlay(new BMap.Marker(pp));    //添加标注
				}
		
				var local = new BMap.LocalSearch(map, { //智能搜索
				  //onSearchComplete: myFun
				  renderOptions:{map:map,panel:"searchResultPanel"}
				});
				local.search(myValue);
			}		
			
			
		$("#go_back_btn").click(function(){
			//2、发送ajax请求
			$.ajax({
				url:"${APP_PATH}/",
				type:"POST",
				success:function(result){
					//alert(result.msg);
					window.location.href="Menu.jsp";
				}
			});
		});
		
	
	
	</script>

</html>