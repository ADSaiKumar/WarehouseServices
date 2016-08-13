<div id="add-stock" class="col s12">
		<form method="POST" action="post/checkoutitem">
				<section class="row">
						<div class="input-field col s6 offset-s3">
						    <select id="outitemselect" name="itemId">
						    	<#list items as item>
						    	  <option value="${item.itemId}" data-quantity="${item.quantity}">${item.itemName}</option>
						    	</#list>
						    </select>
						    <label>Select Item Name</label>
						  </div>
				</section>
				<section class="row">
				<section class="col s12">
					<#list ['1','2','5','10','20','30','40','50','60','70','80','90','100'] as x>
						<section class="inline card z-depth-5 blue-grey darken-4 white-text" draggable="true" ondragstart="drag(event)"><h5>${x}</h5></section>
					</#list>
				</section>
				<section class="row">
					<table class="bordered striped centered" >
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
						        <td id="outitemname" >Item</td>
						        <td id="oldstock" >0</td>
						        <td id="newstock" class="teal" ondrop="dropout(event)" ondragover="allowDrop(event)">0</td>
						        <td id="totalstock" >0</td>
						    </tr>
				        </tbody>
				    </table>
				</section>
				<section class="row">
					<input type="hidden" value="" name="itemQuantity">
					<section class="col s4 offset-s4">
						<button class="white btn waves-effect waves-light col s4 offset-s5" style="color:black;" id="submitbutton">Confirm<i class="material-icons right" style="color:black;">send</i>
						</button><button class="white btn waves-effect waves-light col s4 offset-s5" style="color:black;display:none;" id="resetstock">Reset<i class="material-icons right" style="color:black;">send</i>
						</button>
					</section>
				</section>
		</form>
</div>