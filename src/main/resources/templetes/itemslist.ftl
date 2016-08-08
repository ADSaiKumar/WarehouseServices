<div class="input-field col s6 offset-s1">
						    <select id="itemselect" name="itemId">
						    	<#list items as item>
						    	  <option value="${item.itemId}">${item.itemName}</option>
						    	</#list>
						    </select>
						    <label>Select Item Name</label>
						  </div>