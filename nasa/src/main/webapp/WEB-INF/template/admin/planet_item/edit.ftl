<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑星球资源表</title>
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
                    	 <h5>编辑星球资源表</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/planet_item/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/planet_item/update.jhtml" method="post">
                  		<input type="hidden" value="${planetItem.id}" name="id"/>
                       
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">NASAID</label>
	                                <div class="col-sm-9">
	                                    <input type="text" disabled="disabled" name="nasaId" class="form-control" maxlength="200" value="${(planetItem.nasaId)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
                        
                        <!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">英文标题</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="titleEn" class="form-control" maxlength="200" value="${(planetItem.titleEn)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">中文标题</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="titleZh" class="form-control" maxlength="200" value="${(planetItem.titleZh)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
                        
                        <!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">英文描述</label>
	                                <div class="col-sm-9">
	                                    <textarea type="text" name="descriptionEn" class="form-control" maxlength="200" value="${(planetItem.descriptionEn)!''}">${(planetItem.descriptionEn)!''}</textarea>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">中文描述</label>
	                                <div class="col-sm-9">
	                                    <textarea  name="descriptionZh" class="form-control" maxlength="200" value="${(planetItem.descriptionZh)!''}">${(planetItem.descriptionZh)!''}</textarea>
	                                </div>
                       			</div>
							</div>
                        </div>
                        
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">获取该照片所有图片链接</label>
	                                <div class="col-sm-9">
	                                    <input type="text" disabled="disabled" name="totalHref" class="form-control" maxlength="200" value="${(planetItem.totalHref)!''}"/>
	                                </div>
                       			</div>
                       			
							</div>
                        </div>
							
						
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">缩略图</label>
	                                <div class="col-sm-9">
	                                	<img alt="${(planetItem.titleEn)!''}" style="height:450px;width:450px;" 
					                        			src="${(planetItem.hrefThumb)!''}"> 
	                                    
	                                </div>
                       			</div>
                       			
							</div>
                        </div>
						
							
						
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">资源来源中心</label>
	                                <div class="col-sm-9">
	                                    <input type="text" disabled="disabled" name="center" class="form-control" maxlength="200" value="${(planetItem.center)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">数据发布时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" disabled="disabled" name="dateCreated" class="form-control" maxlength="200" value="${(planetItem.dateCreated?string('yyyy-MM-dd HH:mm:ss'))!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">是否已经爬取</label>
	                                <div class="col-sm-9">
	                                	[#if planetItem.published = 1]
	                                		<input type="text" disabled="disabled" value="是"/></input>
	                                	[#else]	
	                                		<input type="text" disabled="disabled" value="否"/></input>
	                                	[/#if]
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">启用状态</label>
	                                <div class="col-sm-9">
	                                	[#if planetItem.status = 1]
	                                		 <input type="text" disabled="disabled" value="正常"/></input>
	                                	[#else]	
	                                		<input type="text" disabled="disabled" value="禁用"/>
	                                	[/#if]
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">更新时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" disabled="disabled" name="updateTime" class="form-control" maxlength="200" value="${(planetItem.updateTime?string('yyyy-MM-dd'))!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">创建时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" disabled="disabled" name="createTime" class="form-control" maxlength="200" value="${(planetItem.createTime?string('yyyy-MM-dd'))!''}"/>
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
        });
    </script>
</body>
</html>
