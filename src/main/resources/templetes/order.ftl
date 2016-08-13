<table class="bordered striped centered" >
    <thead>
      <tr>
          <th data-field="id">Order-Id</th>
          <th data-field="name">Item</th>
          <th data-field="price">Stock</th>
      </tr>
    </thead>

    <tbody>
		<#list orders as order>
			<tr>
	            <td>${order.orderId}</td>
	            <td>${order.item.itemId}</td>
	            <td>${order.orderAmount}</td>
	        </tr>
	    </#list>
    </tbody>
</table>
