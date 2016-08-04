	<#list orders as order>
		<tr>
            <td>${order.orderId}</td>
            <td>${order.item.itemId}</td>
            <td>${order.orderAmount}</td>
        </tr>
    </#list>