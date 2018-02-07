<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增星球类别表</title>
    <meta name="keywords" content="${setting.siteName}">
  	<meta name="description" content="${setting.siteName}">
  	<!-- BEGIN HEADER -->
		[#include "/admin/include/style.ftl"]
	<!-- END HEADER -->
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                    <h5>新增星球类别表</h5>
                     	<div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/planet_category/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                         </div>
                    	 <div class="form-group">
                  		<div class="row">
                  			<div class="col-sm-3">
					        		<div class="col-sm-6 m-b-xs">
					        			<input type="text" placeholder="请输入星球英文名" name="titleEn" id="titleEn" >
                            		</div>
                                <div class="input-group">
                                     <button type="button" id="check" class="btn btn-sm btn-primary">check</button>
                                      <span id="done"></span>
                                </div>
                    	   </div>
                    	   </div>
                  
                    	 </div>    
                    </div>  
                           	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="save.jhtml" method="post">
                      
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">中文名称</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="nameZh" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">英文名称</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="nameEn" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">中文描述</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="describeZh" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">英文描述</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="describeEn" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">排序</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="sort" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
						
							<!--/row-->
						
                        <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                            <button class="btn btn-success" type="submit">保存内容</button>
	                        </div>
	                    </div>
	                    </form>
                  	</div>
                </div>
            </div>
        </div>
    </div>
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    <script>
    
        $(document).ready(function () {
        	[@flash_message /]
        	$(".chosen-select").chosen({width:"100%"}); 
        	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        	
        	$(".browserButton").browser({callback:function(url){
				$("#"+$(this).attr('for')).val(url);
			}});
        	
    		$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});
        	$("#inputForm").validate({
                rules: {
                },
                messages: {
				}
            });
            
            
            $("#check").click(function(){
            
            var titleEn = $('#titleEn').val();
            
            //alert(titleEn);
            
            if(!titleEn){
            	alert("不能为空！");
            }else{
                $.ajax({ 
            	url: "check.jhtml", 
            	data:{titleEn : titleEn},
            	dataType:"json",
            	success: function(data){
            		$("#done").text(data.titleEn + " : "+ data.totalHits)
            	
       				//alert(data.totalHits);
      			}
      		  });
             
         
           
			}
            });
           
			
        });
    </script>
</body>
</html>
