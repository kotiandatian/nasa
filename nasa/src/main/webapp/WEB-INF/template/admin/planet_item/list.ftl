
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 星球资源表管理</title>
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
                	<form id="listForm" action="list.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>星球资源表管理 </h5>
                    	 <div class="ibox-tools">
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="syncUpdate.jhtml"><i class="fa fa-plus"></i>手动同步</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入本地Id" name="filter_id" value="${(paramter.id)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入NASAId" name="filter_nasaId" value="${(paramter.nasaId)!''}" class="input-sm form-control">
                            </div>
                             <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入英文标题" name="filter_titleEn" value="${(paramter.titleEn)!''}" class="input-sm form-control">
                            </div>
					        <!-- <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入中文标题" name="filter_titleZh" value="${(paramter.titleZh)!''}" class="input-sm form-control">
                            </div> -->
                            
                            
					        <div class="col-sm-3 m-b-xs">
					           <select name="filter_categoryId" class="chosen-select">
					           		<option value="">请选择类别(中文)</option>
					            	[#list planetCategoryList as planetCategory] 
                             			<option value="${(planetCategory.id)!''}" [#if paramter.categoryId = "${(planetCategory.id)!''}" ]selected[/#if] >${(planetCategory.nameZh)!''}</option>
                            		[/#list]
                                </select>
                            </div>

					         <div class="col-sm-3 m-b-xs">
                                        <select name="filter_status" class="chosen-select">
                                        	<option value="">类别状态</option>
                                            <option value="1" [#if paramter.status=1]selected[/#if] >启用</option>
                                            <option value="2" [#if paramter.status=2]selected[/#if] >禁用</option>
                                        </select>
                            </div>

					   
                           <div class="col-sm-3 m-b-xs">
                                        <div class="input-daterange input-group " id="datepicker">
                                            <input type="text" class="form-control " placeholder="请选择数据发布时间"
                                                   name="filter_dateCreateds" value="${(paramter.dateCreateds)!''}"/>
                                            <span class="input-group-addon">—</span>
                                            <input type="text" class="form-control  " placeholder="请选择数据发布时间"
                                                   name="filter_dateCreatede" value="${(paramter.dateCreatede)!''}"/>
                                        </div>
                             </div>
                             <div class="col-sm-3 m-b-xs">
                                        <div class="input-daterange input-group " id="datepicker">
                                            <input type="text" class="form-control " placeholder="请选择创建日期"
                                                   name="filter_createTimes" value="${(paramter.createTimes)!''}"/>
                                            <span class="input-group-addon">—</span>
                                            <input type="text" class="form-control  " placeholder="请选择创建日期"
                                                   name="filter_createTimee" value="${(paramter.createTimee)!''}"/>
                                        </div>
                             </div>
                            <div class="col-sm-1">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>Id</th>
                                        <th>NADAId</th>
                                        <th>英文标题</th>
                                        <!-- <th>中文标题</th> -->
                                        <th>资源类型</th>
                                        <th>缩略图</th>
                                        <th>英文类别名称</th>
                                        <th>中文类别名称</th>
                                        <th>数据发布时间</th>
                                        <th>是否已经爬取</th>
                                        <th>是否启用</th>
                                        <th>更新时间</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as planetItem] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${planetItem.id}">
					                        </td>
					                        <td>${(planetItem.id)!''}</td>
					                        <td>${(planetItem.nasaId)!''}</td>
					                         <td>${(planetItem.titleEn)!''}</td>
					                        <!--  <td>${(planetItem.titleZh)!''}</td> -->
					                        <td>${(planetItem.mediaType)!''}</td>
					                        <td>
					                        	<img alt="" id="image" style="height:150px;width:150px;" 
					                        			src="${(planetItem.hrefThumb)!''}"> 
					                        </td>
					                        <td>${(planetItem.categoryEn)!''}</td>
					                        <td>${(planetItem.categoryZh)!''}</td>
					                        <td>${(planetItem.dateCreated?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                          <td>
                                                    [#if planetItem.published = 1]是[/#if]
                                                    [#if planetItem.published = 2]否[/#if]
                                            </td>
					                        <td>
					                        	<label>
					                        		<input type="radio" value="${planetItem.id}" name="disable_'${planetItem.id}'" id="open_${planetItem.id}" 
					                        		[#if planetItem.status = 1 ]checked=checked[/#if] onclick="changeStatus('${planetItem.id}')">上架
					                        		<input type="radio" value="${planetItem.id}" name="disable_'${planetItem.id}'" id="close_${planetItem.id}"
					                        		[#if planetItem.status = 2 ]checked=checked[/#if] onclick="changeStatus('${planetItem.id}')">下架
					                        	</label>
                                            </td>
					                        <td>${(planetItem.updateTime?string('yyyy-MM-dd '))!''}</td>
					                        <td>${(planetItem.createTime?string('yyyy-MM-dd '))!''}</td>
					                       
					                        <td>
					                        	<!-- <a href="${base}/admin/planet_item/view/${planetItem.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>  -->
					                        	
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${planetItem.id}" href="edit/${planetItem.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${planetItem.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
					                        	
					                        </td>
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                       		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
								[#include "/admin/include/pagination.ftl"]
							[/@pagination]
                        </div>
                    </div>
                    </form>
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
        	 $(".chosen-select").chosen({width: "100%"});
            $(".input-daterange").datepicker({
            todayBtn : "linked",
            keyboardNavigation : false,
            keyboardNavigation:false,
            forceParse:false,
            autoclose:true});
        });
        
        
      //商品上架、下架处理函数
        var changeStatus = function(id){
        	$.ajax({
        		url:"${base}/admin/planet_item/changeStatus.jhtml",
        		type:"get",
        		data:{id:id},
        		dataType:"json",
        		success:function(data){
        			if(data.type="success"){
        				
    				  var message = '{"type":"success","content":"操作成功"}'
                      art.message($.parseJSON(message));
    				  window.setTimeout(function() {
							window.location.reload();
						}, 1000);
        			}
        		}
        		
        	})
        	
        }
    </script>
    <!--图片放大   transition：属性变化时间    transform：放大倍数 -->
    <style type="text/css">
			#image{
				cursor: pointer;
				transition: all 0.5s;
			}
			#image:hover{
				transform: scale(2);
			}
		</style>
</body>

</html>
