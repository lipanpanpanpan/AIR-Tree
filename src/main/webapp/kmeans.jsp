<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>kmeans算法</title>
		
		<%
		pageContext.setAttribute("APP_PATH",request.getContextPath());
		%>
		<script type="text/javascript" src="${APP_PATH }/static/js/echarts.min.js"></script>
		<script type="text/javascript"
			src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
		<link
			href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
			rel="stylesheet">
		<script
			src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>	
			<style type="text/css">
					.header{
					width:100%
					height:40px;
					/*background-color: lightgreen;*/
					background-color: #D6D6D6;
					/**设置居中**/
					margin:5px auto;
					font-size:30px;
					color:blueviolet;
					padding:1px 0;
					text-align: center;
					background-image:url(${APP_PATH }/image/0.jpg);
					background-repeat:repeat-x;
					}
				   canvas {
			            display:block;
			            margin:10px auto;
			            border:1px solid black;
			        }
			        .s1{
				margin-left:1200px;
			}
			</style>
	</head>
<body>
	<div class="header">K-means兴趣点聚类</div>
	<canvas id="canvas" height="500" width="1200"></canvas>
	
	<span class="s1" ></span>
		<button type="button"  id="go_back_btn" >返回菜单页</button>
	
	
</body>

<script>
	//K-means算法

   $.ajax({
	   url:"${APP_PATH}/poidatas",
	   type:"GET",
	   success:function(result){
		  var data;
		  var canvas; 
		  var ctx;
		  var height = 500;
		  var width = 1200;
		  var means = [];
		  var assignments = [];
		  var dataExtremes;
		  var dataRange;
		  var drawDelay = 2000;
		  data=new Array();
		  
		  function setup() {
		       canvas = document.getElementById('canvas');
		       ctx = canvas.getContext('2d');
		      
		     //  ctx.translate(-100,-100);

		       dataExtremes = getDataExtremes(data);
		      //console.log(data);
		       //console.log(dataExtremes);
		       dataRange = getDataRanges(dataExtremes);
		       //console.log(dataRange);
		       means = initMeans(8);
		       //console.log(means);

		       makeAssignments();
		       
		       draw();

		       setTimeout(run, drawDelay);
		   }

		  //获取范围
		   function getDataRanges(extremes) {
		       var ranges = [];

		       for (var dim in extremes) {
		           ranges[dim] = (extremes[dim].max - extremes[dim].min);
		       }

		       return ranges;

		   }

		  //获取极限
		   function getDataExtremes(points) {
		       
		       var extremes = [];

		       for (var i in data) {
		           var point = data[i];

		           for (var dim in point) {
		               if ( ! extremes[dim] )  {
		                   extremes[dim] = {min: 100000, max: 0};
		               }

		               if (point[dim] < extremes[dim].min) {
		                   extremes[dim].min = point[dim];
		               }

		               if (point[dim] > extremes[dim].max)  {
		                   extremes[dim].max = point[dim];
		               }
		           }
		       }

		       return extremes;

		   }

		   function initMeans(k) {
		       if ( ! k ) {
		           k = 8;
		       }

		       while (k--){
		           var mean = [];
		           for (var dim in dataExtremes) {
		        	   //console.log(dataExtremes[dim].min);
		        	  // console.log(dataRange[dim]);
		        	   //console.log( (10*Math.random()+1) * dataRange[dim]);
		               mean[dim] = dataExtremes[dim].min + ( (10*Math.random()+1) * dataRange[dim] );
		           }
		          // console.log(mean);
		           means.push(mean);
		       }
		       return means;

		   };

		   function makeAssignments() {
		       for (var i in data) {
		           var point = data[i];
		           //console.log(i+","+point);
		           var distances = [];

		           for (var j in means) {
		               var mean = means[j];
		              //console.log(mean);
		               var sum = 0;
		               for (var dim in point){
		            	   var difference= point[dim] - mean[dim];
		            	   //console.log(point[dim]);
		            	  // console.log(mean[dim]);
		                  //console.log(difference);
		                   difference *= difference;
		                   //console.log(difference);
		                   sum += difference;
		               }
		          

		               distances[j] = Math.sqrt(sum);
		              // console.log(distances[j]);
		           }

		           assignments[i] = distances.indexOf( Math.min.apply(null, distances) );
		           //Math.min.apply   求一个数组中的最小值
		       }

		   }

		   function moveMeans() {

		       makeAssignments();

		       var sums = Array( means.length );
		       var counts = Array( means.length );
		       var moved = false;

		       //初始化数组
		       for (var j in means){
		           counts[j] = 0;
		           sums[j] = Array( means[j].length );
		           for (var dim in means[j]) {
		               sums[j][dim] = 0;
		           }
		       }

		       //赋值
		       for (var point_index in assignments) {
		           var mean_index = assignments[point_index];
		           var point = data[point_index];
		           var mean = means[mean_index];

		           counts[mean_index]++;

		           for (var dim in mean){
		               sums[mean_index][dim] += point[dim];
		           }
		       }

		       for (var mean_index in sums) {
		           //console.log(counts[mean_index]);
		           if ( 0 === counts[mean_index] )  {
		               sums[mean_index] = means[mean_index];
		               console.log("Mean with no points");
		               console.log(sums[mean_index]);

		               for (var dim in dataExtremes) {
		                   sums[mean_index][dim] = dataExtremes[dim].min + ( Math.random() * dataRange[dim] );
		               }
		               continue;
		           }

		           for (var dim in sums[mean_index]) {
		               sums[mean_index][dim] /= counts[mean_index];
		           }
		       }

		       if (means.toString() !== sums.toString()){
		           moved = true;
		       }

		       means = sums;

		       return moved;

		   }

		   /**
		    *运行
		    **/
		   function run() {
		       var moved = moveMeans();
		       draw();

		       if (moved){
		           setTimeout(run, drawDelay);
		       }
		   }
		   
		   /**
		   * 随机生成0-255的整数
		   **/
		   function rnd255(){
		   return Math.floor(Math.random()*256);
		   }
		   //绘制
		   function draw() {
		       ctx.clearRect(0,0,width, height);
		       ctx.globalAlpha = 0.3;
		       for (var point_index in assignments) {
		           var mean_index = assignments[point_index];
		           var point = data[point_index];
		           var mean = means[mean_index];

		           ctx.save();

		           ctx.strokeStyle = 'blue';
		           ctx.beginPath();
		           ctx.moveTo(
		               (point[0] - dataExtremes[0].min + 1) * (width / (dataRange[0] + 2) ),
		               (point[1] - dataExtremes[1].min + 1) * (height / (dataRange[1] + 2) )
		           );//把路径移动到画布中的指定点，不创建线条
		           ctx.lineTo(
		               (mean[0] - dataExtremes[0].min + 1) * (width / (dataRange[0] + 2) ),
		               (mean[1] - dataExtremes[1].min + 1) * (height / (dataRange[1] + 2) )
		           );//添加一个新点，然后在画布中创建从该点到最后指定点的线条
		           ctx.stroke();//绘制已定义的路径
		           ctx.closePath();//创建从当前点回到起始点的路径
		       
		           ctx.restore();//返回之前保存过的路径状态和属性
		       }
		       ctx.globalAlpha = 1;//设置或返回绘图的当前 alpha 或透明值

		       for (var i in data){
		           ctx.save();//保存当前环境的状态

		           var point = data[i];

		           var x = (point[0] - dataExtremes[0].min + 1) * (width / (dataRange[0] + 2) );
		           var y = (point[1] - dataExtremes[1].min + 1) * (height / (dataRange[1] + 2) );

		         //  ctx.strokeStyle = '#333';//路径
		           ctx.strokeStyle ='rgb('+rnd255()+', '+rnd255()+', '+rnd255()+')';
		           ctx.translate(x, y);
		           ctx.beginPath();
		           ctx.arc(0, 0, 5, 0, Math.PI*2, true);//创建弧/曲线（用于创建圆形或部分圆）
		           //context.arc(x,y,r,sAngle,eAngle,counterclockwise);
		           ctx.stroke();//进行绘制
		           ctx.closePath();//创建从当前点回到起始点的路径

		           ctx.restore();
		       }

		       for (var i in means) {
		           ctx.save();

		           var point = means[i];

		           var x = (point[0] - dataExtremes[0].min + 1) * (width / (dataRange[0] + 2) );
		           var y = (point[1] - dataExtremes[1].min + 1) * (height / (dataRange[1] + 2) );

		           ctx.fillStyle = 'red';//	设置或返回用于填充绘画的颜色、渐变或模式
		           ctx.translate(x, y);
		           ctx.beginPath();
		           ctx.arc(0, 0, 5, 0, Math.PI*2, true);
		           ctx.fill();//填充当前绘图（路径）
		           ctx.closePath();

		           ctx.restore();

		       }

		   }
		 
		  for(var i in data){
			  data[i]=new Array();
		  }
		   var poidatas=result.extend.pageInfo.list;
		   
		  $.each(poidatas,function(index,item){ 
			   data[index]=new Array();
			  
			   var poilng=item.poilng;
			   var poilat=item.poilat;
			   data[index][0] = poilng*10;
			   data[index][1]=poilat*10;
			  
	 	 });
		 // console.log(data);
		   setup();
		 
  		 }
	 });

   $("#go_back_btn").click(function(){
		//2、发送ajax请求
		$.ajax({
			url:"${APP_PATH}/",
			type:"GET",
			success:function(result){
				//alert(result.msg);
				window.location.href="Menu.jsp";
			}
		});
	});
	
 
</script>

</html>