<div id="dashboard" class="col s12">
	<ul class="collapsible popout" data-collapsible="expandable">
		<#list floors as floor>
	    <li>
	      <section class="collapsible-header active"><i class="material-icons">view_day</i>${floor.floorId} (${floor.floorName})</section>
	      <section class="collapsible-body">
	    	<section class="row">
	    		<#list floor.placeholders as placeholder>
	    		<section class="col s1">
	    			<div class="card" id="1">
						<blockquote>
					      <h5>${placeholder.placeholderId}</h5>
					    </blockquote>
					    <p class="type">${placeholder.item.itemName}</p>
					   	<section class="card teal quantity center-text">${placeholder.stock}</section>
				  	</div>
	    		</section>
	    		</#list>
	    	</section>	
	      </section>
	    </li>
	   </#list>					    
	</ul>
	</list>
</div>

