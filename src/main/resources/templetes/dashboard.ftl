<div id="dashboard" class="col s12">
<section class="col s7">
					<section class="row">
						<section class="center-text col s12">
							<p class="flow-text" style="margin-bottom:0px !important;">Warehouse Layout</p>
						</section>
					</section>
	<section class="row">
			<section class="col s12">
	<ul class="collapsible popout" data-collapsible="expandable">
		<#list floors as floor>
	    <li >
	      <section class="collapsible-header active"><i class="material-icons">view_day</i>${floor.floorId} (${floor.floorName})</section>
	      <section class="collapsible-body">
	    	<section class="row">
								<section class="col s12 floor-layout">
								<#list floor.placeholders as placeholder>
								<section class="placeholder-layout modal-trigger" href="#${placeholder.placeholderId}">
								<span>${placeholder.placeholderId}</span>
								<span><#if placeholder.item.itemName??>${placeholder.item.itemName}<#else>None</#if></span>
								<span>${placeholder.stock}</span>
								</section>
								<div id="${placeholder.placeholderId}" class="modal">
								    <div class="modal-content">
								      <h4>Placeholder: ${placeholder.placeholderId}</h4>
								      <p>Item Name : 
								      <#if placeholder.item.itemName??>${placeholder.item.itemName}<#else>None</#if>
								      </p>
								      <p>Stock : ${placeholder.stock}</p>
								      <p>Capacity : ${placeholder.capacity}</p>
								    </div>
								    <div class="modal-footer">
								      <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Close</a>
								    </div>
								  </div>
								  </#list>
							</section>
							</section>
	      </section>
	    </li>
	   </#list>					    
	</ul>
	</section>
</section>
</section>
	<section class="col s5">
	<section class="row">
						<section class="center-text col s12">
							<p class="flow-text" style="margin-bottom:0px !important;">Storage</p>
						</section>
	</section>
	<section class="row">
		<section class="col s12 card-panel blue-grey darken-4 white-text z-depth-5">
			<#list items as item>
			<section class="col s10"><h5>${item.itemName}</h5></section>
			<section class="col s2"><h5>${item.quantity}</h5></section>
			</#list>
		</section>
	</section>
	</section>
	
</div>

