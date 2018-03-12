<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/inc.jsp" %> 
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',split:true,border:false" style="width:200px">
		<ul id="selAllfunctionTree">
				
		</ul>  
	</div>
	<div data-options="region:'center',border:false">
		<table id="functionGrid" data-options="fit:true"></table>  
	</div>
</div>
<div id="functionGrid_bar" style="padding:5px;height:auto">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="acceptFunction()">保存修改</a>
</div>
<script type="text/javascript">
	$('#selAllfunctionTree').tree({
		 lines:true,
		 url:'function/selAllFunctionTree.do' ,
		 onClick: function(node){
				if(!$('#selAllfunctionTree').tree('isLeaf',node.target)){//判断该节点是不是子节点
					 var functionGrid=undefined;
					 		functionGrid=$('#functionGrid').datagrid({
							singleSelect:true,
							queryParams:{parent_id:node.id},
							url:'function/findFunctionByPages.do',
							striped:true,
							loadMsg:"正在加载中...",
							pagination:true,
							pageNumber:1,
							pageSize:50,
							pageList:[10,20,30,40,50],
							remoteSort:false,
							toolbar:"#functionGrid_bar",
							onClickCell: onClickCell,
							columns:[[
							  		{field:'fid',sortable:true,align:'center',checkbox:true},
							  		{field:'fname',title:'资源名',width:200,align:'center',editor:'text'},
							  		{field:'url',title:'URL地址',width:180,align:'center',editor:'text'},
							  		{field:'status',title:'是否可用',width:180,align:'center',
							  			editor:{type:'checkbox',options:{on:'是',off:'否'}}
							  		}]]
							
						}); 
				}
				
				
				
				
			}

	});
	$.extend($.fn.datagrid.methods, {
		editCell: function(jq,param){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field){
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		}
	});
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#functionGrid').datagrid('validateRow', editIndex)){
			$('#functionGrid').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index, field){
		 if (endEditing()){
			$('#functionGrid').datagrid('selectRow', index)
					.datagrid('editCell', {index:index,field:field});
			editIndex = index;
		} 
	} 
	function acceptFunction(){
		if (endEditing()){
			var rows = $('#functionGrid').datagrid('getChanges');
			var length=rows.length;
			if(length>0){
				var obj = undefined;
				var arr =new Array(length);
				for(var i=0;i<length;i++){
					obj=new Object();
					obj.fid=rows[i].fid;
					obj.fname=rows[i].fname;
					obj.url=rows[i].url;
					obj.status=rows[i].status;
					arr[i]=obj;
				}
				$.post("function/updateFunction.do",{functions:JSON.stringify(arr)},function(data){
					if(data){
						$.messager.show({
							title:'成功提示',
							msg:'更新成功。。.',
							timeout:5000,
							showType:'slide'
						});
					}
				},"json")
				$('#functionGrid').datagrid('acceptChanges');//将编辑好的数据写进表格
			}else{
				$('#functionGrid').datagrid('endEdit', editIndex);
				editIndex = undefined;
				$.messager.alert('温馨提示','没有修改数据!','info');
			}
		}
	}
	
</script>