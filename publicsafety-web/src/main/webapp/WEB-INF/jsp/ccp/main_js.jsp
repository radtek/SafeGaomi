<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<script>
jQuery(document).ready(function() {       
   // initiate layout and plugins
   App.init();
});
</script> 
<script>
	var lineChartData = "";
	
	$(function () {
	
		//当前菜单高亮显示
		addHightLightByMenu("main");
		
		//加载基础数据
		getClueCount();
		getPhotoCount();
		getProviderCount();
		
		//初始化图表数据，并赋值
		initChartData();
		//图表生成
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myLine = new Chart(ctx).Line(lineChartData, {
			responsive: true
		});
	});
	
</script> 
<script>
	//征集线索总数
	var getClueCount = function () {
		$.ajax({
			async : true,
			type : "GET",
			url: "${ctx}/ccp/clueInfo/getClueCount.html",
            success: function(data){
            	if (data.flag == 'success')  {
            		$("#clueCount").text(data.clueCount);
            	} 
            }
		});
	}
	
	//上传图片总数
	var getPhotoCount = function () {
		$.ajax({
			async : true,
			type : "GET",
			url: "${ctx}/ccp/clueInfo/getPhotoCount.html",
            success: function(data){
            	if (data.flag == 'success')  {
            		$("#photoCount").text(data.photoCount);
            	} 
            }
		});
	}
	
	//注册的用户总数
	var getProviderCount = function () {
		$.ajax({
			async : true,
			type : "GET",
			url: "${ctx}/ccp/clueProvider/getProviderCount.html",
            success: function(data){
            	if (data.flag == 'success')  {
            		$("#providerCount").text(data.providerCount);
            	} 
            }
		});
	}
	
	var intoModule = function (name) {
		if (name== 'clue') {
			location.href="${ctx}/ccp/clueInfo/index.html";
		} else if (name== 'clueProvider') {
			location.href="${ctx}/ccp/clueProvider/index.html";
		}
	}
</script>


<script>
	var initChartData = function () {
		$.ajax({
			async : false,
			type : "POST",
			url: "${ctx}/ccp/main/initChartData.html",
            success: function(data){
            	if (data.flag == 'success')  {
            		var dataForX = data.dataForX;
            		var dataForClue = data.dataForClue;
            		var dataForProvider = data.dataForProvider;
            		$("#buttonContent").val(data.buttonContent);
            		initLineChartData(dataForX,dataForClue,dataForProvider);
            	}
            }
		});
	}
	
	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
	var initLineChartData = function (dataForX,dataForClue,dataForProvider) {
		lineChartData = {
			labels : dataForX, //X轴坐标
			datasets : [
				{
					label: "上传线索量",
					fillColor : "rgba(220,220,220,0.2)", //背景色
					strokeColor : "rgba(220,220,220,1)", //线
					pointColor : "rgba(220,220,220,1)", //点
					pointStrokeColor : "#fff", //点的包围圈
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(220,220,220,1)",
					data : dataForClue //Y轴坐标
				},
				{
					label: "用户注册量",
					fillColor : "rgba(151,187,205,0.2)",
					strokeColor : "rgba(151,187,205,1)",
					pointColor : "rgba(151,187,205,1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(151,187,205,1)",
					data : dataForProvider
				}
			]
	
		}
	}
</script>
<script>
	/**
	* 自定义弹出层
	*/
	 Chart.defaults.global.pointHitDetectionRadius = 1;
	Chart.defaults.global.customTooltips = function(tooltip) {
		var tooltipEl = $('#chartjs-tooltip');

        if (!tooltip) {
            tooltipEl.css({
                opacity: 0
            });
            return;
        }
        
        tooltipEl.removeClass('above below');
        tooltipEl.addClass(tooltip.yAlign);

        var innerHtml = '';
        var content_1 = '';
        for (var i = tooltip.labels.length - 1; i >= 0; i--) {
        	
        	if (tooltip.legendColors[i].fill == 'rgba(220,220,220,1)') {
        		content_1 = '线索';
        	} else {
        		content_1 = '用户';
        	}
        	innerHtml += [
        		'<div class="chartjs-tooltip-section">',
        		'	<span class="chartjs-tooltip-key" style="background-color:' + tooltip.legendColors[i].fill + '"></span>',
        		'	<span class="chartjs-tooltip-value">' +content_1+'  '+ tooltip.labels[i] + '</span>',
        		'</div>'
        	].join('');
        }
        tooltipEl.html(innerHtml);

        tooltipEl.css({
            opacity: 1,
            left: tooltip.chart.canvas.offsetLeft + tooltip.x + 'px',
            top: tooltip.chart.canvas.offsetTop + tooltip.y + 'px',
            fontFamily: tooltip.fontFamily,
            fontSize: tooltip.fontSize,
            fontStyle: tooltip.fontStyle,
        });
	}
</script>