 	<table class="bordered striped centered" >
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