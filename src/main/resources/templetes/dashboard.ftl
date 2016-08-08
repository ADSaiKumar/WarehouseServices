<div id="dashboard" class="col s12">
	<section class="row">
			<section class="col s7">
	<ul class="collapsible popout" data-collapsible="expandable">
		<#list floors as floor>
	    <li >
	      <section class="collapsible-header active"><i class="material-icons">view_day</i>${floor.floorId} (${floor.floorName})</section>
	      <section class="collapsible-body">
	    	<section class="row">
	    		<#list floor.placeholders as placeholder>
				<section class="col s1">
					<section class="z-depth-5 card center-text modal-trigger blue-grey darken-4" href="#${placeholder.placeholderId}"><span class="card-title white-text">${placeholder.placeholderId}</span>
					<p class="white-text">${placeholder.stock}</p>
					</section>
					  <div id="${placeholder.placeholderId}" class="modal">
					    <div class="modal-content">
					      <h4>Placeholder: ${placeholder.placeholderId}</h4>
					      <p>Item Name : ${placeholder.item.itemName}</p>
					      <p>Stock : ${placeholder.stock}</p>
					      <p>Capacity : ${placeholder.capacity}</p>
					    </div>
					    <div class="modal-footer">
					      <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Close</a>
					    </div>
					  </div>
				</section>
				</#list>
			</section>	
	      </section>
	    </li>
	   </#list>					    
	</ul>
	</section>
</section>	
</div>

