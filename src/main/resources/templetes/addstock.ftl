<div id="add-stock" class="col s12">
				<form method="POST" action="post/addstock">
				<section class="row">
						<section id="select-container">
						<div class="input-field col s6 offset-s1">
						    <select id="itemselect" name="itemId">
						    	<#list items as item>
						    	  <option value="${item.itemId}">${item.itemName}</option>
						    	</#list>
						    </select>
						    <label>Select Item Name</label>
						  </div>
						  </section>
						  <a class='btn col s2 offset-s1 modal-trigger' href='#items-list' >Add New Item</a>
						  <div id="items-list" class="modal">
						    <div class="modal-content">
						      <h4>Add New Item</h4>
					    			<section class="row">
					    			<div class="input-field col s10 offset-s1">
					    					<i class="material-icons prefix">playlist_add</i>
											<input id="newItem" type="text" class="validate">
											<label for="text">Enter Item Name</label>
								    </div>
								    <div class="modal-footer">
								      <a class=" modal-action modal-close waves-effect waves-green btn-flat" id="addNewItem">Agree</a>
								    </div>
					    		</section>
						    </div>
						  </div>
				</section>
				<section class="row">
				<section class="col s6">
					<#list ['10','20','30','40','50','60','70','80','90','100'] as x>
						<section class="inline card light-blue accent-2" draggable="true" ondragstart="drag(event)"><h2>${x}</h2></section>
					</#list>
				</section>
				<section class="col s6" ondrop="drop(event)" ondragover="allowDrop(event)">
				<section class="row">
						<section class="col s9"><h1 id="itemname">Item :</h1></section>
						<section class="col s3"><section class="card light-blue accent-4" ><h2 id="quantity">0</h2></section></section>
				</section>
				<input type="hidden" value="" name="itemQuantity">
				<button class="white btn waves-effect waves-light col s3 offset-s5" style="color:black;">Confirm<i class="material-icons right" style="color:black;">send</i>
				</button>
			
				</section>
				</form>
</div>