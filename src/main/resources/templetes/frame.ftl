<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Warehouse Manager</title>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="/WarehouseServices/css/materialize.min.css">
		<link rel="stylesheet" href="/WarehouseServices/css/skin.css">
		<script type="text/javascript" src="/WarehouseServices/js/jquery-2.2.4.min.js"></script>
		<script src="/WarehouseServices/js/materialize.min.js"></script>
		<script type="text/javascript" src="/WarehouseServices/js/mind.js"></script>
	</head>
	<body>
		<div class="row">
				<section class="col s3">
					<h3 id="userName">${userName}</h3>
				</section>
			    <div class="col s9">
			      <a href="${dLink}"><section class="col s2 nav-tab ${dClass}"><h5>Dashboard</h5></section></a>
			      <a href="${aLink}"><section class="col s2 nav-tab ${aClass}"><h5>Add Stock</h5></section></a>
			      <a href="${cLink}"><section class="col s2 nav-tab ${cClass}"><h5>Check Out</h5></section></a>
			      <a href="${sLink}"><section class="col s2 nav-tab ${sClass}"><h5>Search</h5></section></a>
			      <a href="${oLink}"><section class="col s2 nav-tab ${oClass}"><h5>Order</h5></section></a>
			    </div>
		</div>
		<section class="row">
				${divData}
		</section>
	</body>
</html>