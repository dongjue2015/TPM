<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="views/scripts/jquery/jquery-1.7.1.js"></script>
<link href="views/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="views/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="views/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="views/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="views/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="views/style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="views/scripts/artDialog/artDialog.js?skin=default"></script>
<title>TPM信息管理系统</title>
<script type="text/javascript">
	
</script>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="20"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" />
							</th>
							<th>序号</th>
							<th>设备编号</th>
							<th>设备名称</th>
							<th>规格型号</th>
							<th>出厂厂家</th>
							<th>使用地点</th>
							<th>种类</th>
							<th>录入时间</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${list}" var="equipment">
							    <tr>
								<td><input type="checkbox" name="IDCheck" value="14458579642011" class="acb" /></td>
								<td>1</td>
								<td>${equipment.encodingEquipment}</td>
								<td>${equipment.numberEquipment}</td>
								<td>${equipment.nameEquipment}</td>
								<td>${equipment.numberMateriel}</td>
								<td>${equipment.factory}</td>
								<td>${equipment.useLocation}</td>
								<td>${equipment.kindEquipment}</td>
								<td>
									<a href="edit.jsp?fyID=14458579642011" class="edit">编辑</a> 
									<a href="javascript:del('14458579642011');">删除</a>
									<a href="javascript:del('14458579642011');">详情</a>
								</td>
							</tr>
						</c:forEach>
							
							
								<tr>
								<td><input type="checkbox" name="IDCheck" value="14458579642011" class="acb" /></td>
								<td>2</td>
								<td>TPM200</td>
								<td>TPM200</td>
								<td>TPM200</td>
								<td>TPM200</td>
								<td>TPM200</td>
								<td>TPM200</td>
								<td>TPM200</td>
								<td>
									<a href="edit.jsp?fyID=14458579642011" class="edit">编辑</a> 
									<a href="javascript:del('14458579642011');">删除</a>
									<a href="javascript:del('14458579642011');">详情</a>
								</td>
							</tr>
							
					</table>
				
</body>
</html>
