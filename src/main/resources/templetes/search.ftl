<section id="search" class="col s12">
	<section class="row">
		<section class="col s6">
			<section class="input-field col s12">
			    <p>
			      <input name="searchby" type="radio" id="item" />
			      <label for="item">Item</label>
			    </p>
			    <p>
			      <input name="searchby" type="radio" id="floor" />
			      <label for="floor">Floor</label>
			    </p>
			 </section>
		</section>
		<section class="col s6">
			<section class="row"></section>
			<section class="row"></section>
			<section class="input-field col s12" id="floorselect">
			    <select id="floorlist">
			      <option value="" disabled selected>Choose your option</option>
			      <option value="1">Floor 1</option>
			      <option value="2">Floor 2</option>
			      <option value="3">Floor 3</option>
			    </select>
			    <label>Select Floor</label>
			</section>
			<section class="input-field col s12" id="itemselect">
		          <input type="text" list="items" id="selecteditem">
		          <label for="autocomplete">Enter Item</label>
		    </section>
		    <datalist id="items">
		    	<#list items as item>
		    	<option data-id="${item.itemId}" value="${item.itemName}" >  
		    	</#list>
		    </datalist>
		</section>
	</section>
	<table class="bordered striped centered" id="searchresult">
        <thead>
          <tr>
              <th data-field="id">Name</th>
              <th data-field="name">place</th>
              <th data-field="price">Stock</th>
              <th data-field="price">Storage</th>
          </tr>
        </thead>

        <tbody>
          
        </tbody>
    </table>
</section>