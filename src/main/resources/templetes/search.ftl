<section id="search" class="col s12">
	<section class="row no-bottom">
		<section class="col s12 center-text">
			<h5>Select Criterea</h5>
		</section>
	</section>
	<section class="row no-bottom">
		<section class="col s5 offset-s5">
			<section class="input-field col s12 no-top">
			    <p class="inline">
			      <input name="searchby" type="radio" id="item" />
			      <label for="item" class="black-text">Item</label>
			    </p>
			    <p class="inline">
			      <input name="searchby" type="radio" id="floor" />
			      <label for="floor" class="black-text">Floor</label>
			    </p>
			 </section>
		</section>
	</section>
	<section class="row">
		<section class="col s5 offset-s4">
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
	<section class="row">
		<section class="col s12" id="searchresult">
		</section>
	</section>
</section>