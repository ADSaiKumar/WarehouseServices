
<table class="bordered striped centered">
        <thead>
          <tr>
              <th data-field="id">Name</th>
              <th data-field="name">place</th>
              <th data-field="price">Stock</th>
              <th data-field="price">Storage</th>
          </tr>
        </thead>

        <tbody>
          <#list items as item>
			<tr>
		        <td>${item.itemName}</td>
		        <td>${item.placeholder}</td>
		        <td>${item.stock}</td>
		        <td>${item.storage}</td>
		    </tr>
		</#list>
        </tbody>
    </table>
<section class="row">
<section class="col s3 offset-s5">
	<ul class="pagination ${hidden}">
    <li class="arrow left ${left}" id="nexpage"><i class="material-icons">chevron_left</i></li>
    	<#list pages as page>	
    	<li class="<#if page?counter == activepage>active<#else>waves-effect</#if> pagebutton" data-offset="${page.offset}" data-uid="${page.uniqueId}" data-limit="${page.limit}"><a>${page.pageNo}</a></li>
    	</#list>
    <li class="arrow right ${right}" id="prevpage"><i class="material-icons">chevron_right</i></li>
</ul>
</section>
</section>