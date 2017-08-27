$(function(){
	var page={
			/*加载配送地区*/
			dataInit:function(){
				alert("234");
				alert();
				 $.ajax({
					 url: '/area/getArea.do',
					   type: 'post',
						dataType: 'json',
						success:function(result){
							if(result.success){
								var province = result.area[0].province;
								for(var j = 0; j < province.length; j ++){
									/*目前只配送仰光地区*/
									if(province[j].id=="112"){
										$('#city').append("<option value=" + province[j].id + ">" + province[j].name + "</option>");
									}
									
								}
								$('#city').on('change',function(){
									$('#area').html("");
									if($(this).val() == 0){
										$('#area').append("<option value='0'>请选择</option>");
									}
									
									for(var i = 0; i < result.area.length; i ++){
										for(var j = 0; j < result.area[i].province.length; j ++){
											if($(this).val() == result.area[i].province[j].id){
												for(var k = 0; k < result.area[i].province[j].city.length; k ++){
													$('#area').append("<option value=" + result.area[i].province[j].city[k].id + ">" + result.area[i].province[j].city[k].name + "</option>");
												}
											}
										}	
									}
								});
							}
						}
				   });
			},
	};
	page.dataInit();
});