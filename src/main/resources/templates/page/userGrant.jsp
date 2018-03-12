<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/inc.jsp" %> 
    <table id="userGrantData" data-options="fit:true"></table> 
    <div id="grantGrid_bar" style="padding:5px;height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delGrants()">重置权限</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendGrant()">设置权限</a>
	</div>
	<div id="grantDialog">
	   	<div style="padding:10px 10px 10px 60px">
		<label>用户名:</label>
    	<input id="unameBox"  class="easyui-textbox" type="text" name="userName" data-options="required:true"/>	
		<p></p>
	   <label> 权限名:</label>
	   <input  id="roleNames" class="easyui-combobox" type="text" name="roleNames"   style="width:200px;height:45px"/>
		</div>
		<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" id="addGrantButton" onclick="submitGrantForm()">确定</a>
		</div>
	</div>
    <script type="text/javascript">
    $('#grantDialog').dialog({ 
		title:"设置用户权限",
	    width:350,  
	    height:200,  
	    modal:true,
	    closed:true,
	});
    var grantObj=undefined;
    grantObj=$('#userGrantData').datagrid({
		singleSelect:true,
		url:'front/findUser.do',
		striped:true,
		loadMsg:"正在加载中...",
		pagination:true,
		pageNumber:1,
		pageSize:50,
		pageList:[10,20,30,40,50],
		remoteSort:false,
		toolbar:"#grantGrid_bar",
		columns:[[
		  		{field:'id',sortable:true,align:'center',checkbox:true},
		  		{field:'userName',title:'用户名',width:200,align:'center'},
		  		{field:'rname',title:'角色',width:200,align:'center'},
		  		]]
		  	
	}); 
    function delGrants(){
    	var row=grantObj.datagrid("getSelected");
    	var loginIngUserID=$("#loginIngUserID").val();
    	if(row==undefined||row==""||row==null){
    		$.messager.alert('温馨提示','请选择要重置权限的用户...','info');
    	}else{
    		if(loginIngUserID==row.uid){
    			$.messager.alert('错误提示','权限不足...','error');
    		}else{
    			$.messager.confirm('确认信息','确定要重置<b>'+row.userName+'</b>的全部权限吗?',function(r){  
        		    if (r){  
        		    	$.post("UserRole/delRoleGrant.do",{uid:row.id},function(data){
        		    		if(data){
        		    			$.messager.show({
        							title:'成功提示',
        							msg:'重置权限成功',
        							timeout:2000,
        							showType:'slide'
        						});
        		    		}else{
        		    			$.messager.alert('错误信息','权限不足或该用户没有任何权限...','error');
        		    		}
        		    	},"json");
        		    }  
        		}); 
    		}
    		
    	}
    }
    function appendGrant(){
    	var rows=grantObj.datagrid("getSelected");
    	if(rows!=undefined&&rows!=''){
    		$('#grantDialog').dialog('open');
        	
        	$('#unameBox').textbox({
        		   value:rows.userName,
        		   editable:false
        		});
        	 $('#roleNames').combobox({
        		url:'list/selRoles.do',
        		valueField:'rid',
        		textField:'rname',
        		multiple:true,
        		multiline:true,
        		editable:false,
        	}); 
        	$('#addGrantButton').linkbutton({plain:true,iconCls:'icon-add'});
    	}else{
    		$.messager.alert('温馨提示','请选择要添加权限的用户...','info');
    	}
    	
    	
    	
    }
    
    function submitGrantForm(){
    	var uid=grantObj.datagrid("getSelected").id;
    	var rids=$('#roleNames').combobox('getValues');
    	
    	if(rids.length<=0){
    		$.messager.alert('温馨提示','请选择要赋予的用户权限','info');
    	}else{
    		var ridss="";
    		for(var i=0;i<rids.length-1;i++){
    			ridss+=rids[i]+",";
			}
    		ridss+=rids[rids.length-1];
    		$.post("UserRole/grantUserRole.do",{uid:uid,rids:ridss},function(data){
    			if(data){
    				$('#grantDialog').dialog('close');
    				$.messager.show({
						title:'成功提示',
						msg:'赋予成功',
						timeout:2000,
						showType:'slide'
					});
    			}else{
    				$.messager.alert('错误提示','赋权失败,请联系开发人员...','error');
    			}
    		},"json");
    	}
    	
    }
</script> 