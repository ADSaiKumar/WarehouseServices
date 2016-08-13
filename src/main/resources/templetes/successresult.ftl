<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Warehouse Manager</title>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="/WarehouseServices/css/materialize.min.css">
		<link rel="stylesheet" href="/WarehouseServices/css/skin.css">
		<link rel="icon" type="image/png" href="images/favicon.png">
		<script type="text/javascript" src="/WarehouseServices/js/jquery-2.2.4.min.js"></script>
		<script src="/WarehouseServices/js/materialize.min.js"></script>
		<script type="text/javascript" src="/WarehouseServices/js/mind.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				window.setInterval(relocate,3000);
			});
			function relocate(){
				window.location="/WarehouseServices/manager/dashboard";
			}
		</script>
	</head>
	<body>
	<div class="z-depth-5 row blue-grey darken-4 white-text" id="header">
				<section class="col s6">
					<h3 >Warehouse Management</h3>
				</section>
		</div>
		 <section class="row">
		 	<section class="col s1 offset-s4">
		 		<i class="material-icons large">done</i>
		 	</section>
		 	<section class="col s4">
		 		<h1>done</h1>
		 	</section>
		 </section>
		 <section class="row">
			${table}
		 </section>
	</body>
</html>	
