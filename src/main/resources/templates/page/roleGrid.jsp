<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/inc.jsp" %> 
<table id="roleData" data-options="fit:true"></table> 

<div id="roleGrid_bar" style="padding:5px;height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addRole()">添加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateRole()">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delRole()">删除</a>
</div>
<div id="role_panl">
	<div style="padding:10px 60px 10px 60px">
			<label>角色名:</label>
	    	<input id="rname"  class="easyui-textbox" type="text" name="rname" data-options="required:true"></input>
	    	
	</div>
	<ul id="functionTree"></ul>  
	<div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" id="addRoleButton" onclick="submitRoleForm()">确定</a>
	  </div>
</div>  

<script type="text/javascript">

var editIngStatus=undefined;/* 正在进行的操作 */
$('#role_panl').dialog({ 
	title:"添加",
    width:450,  
    height:400,  
    modal:true,
    closed:true,
    top:100,
    left:700
   
});

var roleData=undefined;
roleData=$('#roleData').datagrid({
	singleSelect:true,
	url:'list/findRolesByPages.do',
	striped:true,
	loadMsg:"正在加载中...",
	pagination:true,
	pageNumber:1,
	pageSize:50,
	pageList:[10,20,30,40,50],
	remoteSort:false,
	toolbar:"#roleGrid_bar",
	columns:[[
	  		{field:'rid',sortable:true,align:'center',checkbox:true},
	  		{field:'rname',title:'角色名',width:200,align:'center'},
	  		{field:'addtime',title:'注册时间',width:180,align:'center'},
	  		{field:'fname',title:'资源权限',width:800,align:'center'} 
	  		/* ,
	  		{field:'updatetime',title:'上次修改时间',width:180,align:'center'} */]]
	
}); 

function addRole(){
	editIngStatus="add";
	$('#role_panl').dialog({
		title:'添加'
	});
	$('#role_panl').dialog('open');
	$('#rname').textbox({
		value:"",
		editable:true
	});
	$('#functionTree').tree({
		cascadeCheck:true,
		checkbox:true,
	    url:'function/functionTree.do'  
	});
	 $('#addRoleButton').linkbutton({plain:true,iconCls:'icon-add'});
}

function submitRoleForm(){
	if(	editIngStatus=="update"){
		var row=$('#roleData').datagrid('getSelected');
		var nodes = $('#functionTree').tree('getChecked','indeterminate');  
	    var nodes1 = $('#functionTree').tree('getChecked');  
	    var s = '';  
	    for(var i=0; i<nodes.length; i++){  
	        if (s != ''){ s += ','};  
	        s += nodes[i].id;  
	    }  
	    for(var i=0; i<nodes1.length; i++){  
	        if (s != ''){ s += ','};  
	        s += nodes1[i].id;  
	    }  
	    
	    $.messager.confirm('温馨提示','确定更改角色权限吗?',function(r){  
	        if (r){  
	           $.post("roleFunction/updateRoleGrant.do",{rid:row.rid,fids:s},function(data){
	        	  if(data){
	        		  $('#role_panl').dialog('close');
	        		  $.messager.show({
							title:'成功提示',
							msg:'角色修改成功！',
							timeout:5000,
							showType:'slide'
						});
						 editIngStatus=undefined;
	        	  }else{
	        		  $.messager.alert('错误','更新失败，权限不足!','error');
	        	  }
	        	   
	           },"json");
	        }  
	    });  
   
	}else if(editIngStatus=="add"){
			var rname= $('#rname').val();
			var nodes = $('#functionTree').tree('getChecked','indeterminate');  
		    var nodes1 = $('#functionTree').tree('getChecked');  
		    var s = '';  
		    for(var i=0; i<nodes.length; i++){  
		        if (s != ''){ s += ','};  
		        s += nodes[i].id;  
		    }  
		    for(var i=0; i<nodes1.length; i++){  
		        if (s != ''){ s += ','};  
		        s += nodes1[i].id;  
		    }  
				
			if(rname.length<2||rname.length>8){
				$.messager.alert('提示','请输入角色名长度为2到8!','info');
			}else if(s==""){
				$.messager.alert('提示','请选择角色相应的权限！','info');
			}else{
				$.post("list/addRole.do",{fids:s,rname:rname},function(data){
					if(data){
						 $('#role_panl').dialog('close');
						$.messager.show({
							title:'成功提示',
							msg:'角色添加成功！',
							timeout:5000,
							showType:'slide'
						});
						 $('#roleData').datagrid('reload');
						 editIngStatus=undefined;
					}else{
						$.messager.alert('错误','添加失败，请联系开发人员!','error');
					}
				},"json")
			}
	}
}
//修改角色权限
function updateRole(){
	editIngStatus="update";
	var row=$('#roleData').datagrid('getSelected');
	if(row==undefined||row==""||row==null){
		$.messager.alert('提示','请选择要修改的行!','info');
	}else{
		$('#role_panl').dialog({
			title:'修改'
		});
		$('#role_panl').dialog('open');
		
		$('#rname').textbox({
			value:row.rname,
			editable:false
		});
		$('#functionTree').tree({
			cascadeCheck:true,
			checkbox:true,
			method:'GET',
		    url:'function/updateFunctionTree.do?rid='+row.rid
		});
		 $('#addRoleButton').linkbutton({plain:true,iconCls:'icon-edit'});
	}
}
//删除角色
function delRole(){
	var row=$('#roleData').datagrid('getSelected');
	if(row==undefined||row==""||row==null){
		$.messager.alert('提示','请选择要删除的行!','info');
	}else{
		$.messager.confirm('确定', '你确定要删除角色<b>'+row.rname+'</b>吗?', function(r){
			if (r){
				$.get("list/delRole.do",{rid:row.rid},function(data){
					if(data>0){
						$.messager.show({
							title:'成功提示',
							msg:'角色删除成功！',
							timeout:5000,
							showType:'slide'
						});
						$('#roleData').datagrid('reload');
					}else{
						$.messager.alert('提示','权限不足!','info');
					}
				},"json");
			}
		});
	}		
}
</script>
	