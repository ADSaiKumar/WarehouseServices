	<#list items as item>
		<tr>
            <td>${item.itemName}</td>
            <td>${item.placeholder}</td>
            <td>${item.stock}</td>
            <td>${item.storage}</td>
        </tr>
    </#list>