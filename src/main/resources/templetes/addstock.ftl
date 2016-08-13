<div id="add-stock" class="col s12">
				<form method="POST" action="post/addstock">
				<section class="row">
						<section id="select-container">
						<div class="input-field col s6 offset-s1">
						    <select id="additemselect" name="itemId" >
						    	<#list items as item>
						    	  <option value="${item.itemId}" data-quantity="${item.quantity}" >${item.itemName}</option>
						    	</#list>
						    </select>
						    <label>Select Item Name</label>
						  </div>
						  </section>
						  <a class='white black-text btn col s2 offset-s1 modal-trigger' href='#items-list' >Add New Item</a>
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
					<section class="col s12">
						<#list ['1','2','5','10','20','30','40','50','60','70','80','90','100'] as x>
							<section class="inline card z-depth-5 blue-grey darken-4 white-text" draggable="true" ondragstart="drag(event)"><h5>${x}</h5></section>
						</#list>
					</section>
				</section>
				<section class="row">
					<table class="bordered striped centered">
				        <thead>
				          <tr>
				              <th data-field="name">Name</th>
				              <th data-field="oldstock">old-stock</th>
				              <th data-field="newstock">new-stock</th>
				              <th data-field="totalstock">total-stock</th>
				          </tr>
				        </thead>
				
				        <tbody>
							<tr>
						        <td id="additemname" >Item</td>
						        <td id="oldstock" >0</td>
						        <td id="newstock" class="cyan" ondrop="dropadd(event)" ondragover="allowDrop(event)">0</td>
						        <td id="totalstock" >0</td>
						    </tr>
				        </tbody>
				    </table>
				</section>
				<section class="row">
					<input type="hidden" value="" name="itemQuantity">
					<section class="col s8 offset-s4">
						<button class="white btn waves-effect waves-light col s3 black-text" >Confirm<i class="material-icons right" style="color:black;">send</i>
						</button><button class="white btn waves-effect waves-light col s3 black-text" id="resetaddstock">Reset
						</button>
						
					</section>
				</section>
	</form>
</div>