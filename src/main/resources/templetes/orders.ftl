<section id="search" class="col s12">
<section class="row">
					<section class="col s6">
						<table class="bordered striped centered">
			        <thead>
			          <tr>
			              <th data-field="id"></th>
			              <th data-field="name">Name</th>
			              <th data-field="price">Stock</th>
			              <th data-field="price">Order</th>
			          </tr>
			        </thead>

			        <tbody>
			        <#list orders as order>
			          <tr>
			          	<td>
			          		<p>
						      <input type="checkbox" id="row${order.item.itemId}" value="${order.item.itemId}" />
						      <label for="row${order.item.itemId}"></label>
						    </p>
			          	</td>
			          	<td id="nameCol" >${order.item.itemName}</td>
			          	<td >${order.item.quantity}</td>
			          	<td id="amountCol">${order.orderAmount}</td>
			          </tr>
			         </#list>
			        </tbody>
			    </table>
					</section>
					<section class="col s6">
						<form method="POST" action="post/orders">
							<section class="row">
							<input type="hidden" name="itemId1" id="itemId" value="">
							<label for="itemId"></label>
							<input type="hidden" name="orderQuantity1" id="orderQuantity" value="">
							<label for="orderQuantity"></label>
							</section>
							<section class="row">
							<input type="hidden" name="itemId2" id="itemId" value="">
							<label for="itemId"></label>
							<input type="hidden" name="orderQuantity2" id="orderQuantity" value="">
							<label for="orderQuantity"></label>
							</section>
							<section class="row">
							<input type="hidden" name="itemId3" id="itemId" value="">
							<label for="itemId"></label>
							<input type="hidden" name="orderQuantity3" id="orderQuantity" value="">
							<label for="orderQuantity"></label>
							</section>
							<section class="row">
							<input type="hidden" name="itemId4" id="itemId" value="">
							<label for="itemId"></label>
							<input type="hidden" name="orderQuantity4" id="orderQuantity" value="">
							<label for="orderQuantity"></label>
							</section>
							<section class="row">
							<input type="hidden" name="itemId5" id="itemId" value="">
							<label for="itemId"></label>
							<input type="hidden" name="orderQuantity5" id="orderQuantity" value="">
							<label for="orderQuantity"></label>
							</section>
							<button class="white btn waves-effect waves-light col s3 offset-s5" style="color:black;">Confirm<i class="material-icons right" style="color:black;">send</i>
							</button>
						</form>
					</section>
				</section>
</section>