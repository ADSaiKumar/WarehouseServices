$(document).ready(function(){
	$('.dp').height($('.dp').width());
	$('.collapsible').collapsible({
      accordion : false 
    });
    $('.fixed-action-btn').openFAB();
    $('.fixed-action-btn').closeFAB();
    $('.modal-trigger').leanModal();
    $('select').material_select();
    $('#itemselect').change(function(sel){
    	var data=$(this[value=$(this).val()-1]).text();
    	$('#itemname').text(data);
    });
    $('#floorlist').change(function(){
    	var endval = $(this).val();
        var pageURL = $(location). attr("href");
        var endUrl=pageURL+"/floor/"+endval;
        loadResults(endUrl);
    });
	$('#selecteditem').change(function(){
   		var x = $(this).val();  
         var z = $('#items');  
         var val = $(z).find('option[value="' + x + '"]');  
         var endval = val.attr('data-id');
         var pageURL = $(location). attr("href");
         var endUrl=pageURL+"/item/"+endval;
         loadResults(endUrl);
   	});
    $("input[name=searchby]:radio").change(function () {
    	if($(this).attr("id")=="item"){
    		$('#floorselect').hide();
    		$('#itemselect').show();
    	}else{
    		$('#itemselect').hide();
    		$('#floorselect').show();
    	}
    });
    $("input[type=checkbox]").change(function () {
    	if(this.checked){
    			var id=$(this).val();
        	var name=$(this).parents("tr").find("#nameCol").html();
        	var amt=$(this).parents("tr").find("#amountCol").html();
    		var fields=$('input#itemId');
    		fields.each(function(index){
    			if(fields.eq(index).attr('value')==""){
    				fields.eq(index).attr('value',id);
        			fields.eq(index).next().html("<h5>"+name+" : </h5>");
        			fields.eq(index).next().next().attr('value',amt);
        			fields.eq(index).next().next().next().html("<h5>"+amt+"</h5>");
        			return false;
    			}
    		});
    	}else{
    		var id=$(this).val();
    		var fields=$('input#itemId');
    		fields.each(function(index){
    			if(fields.eq(index).attr('value')==id){
    				fields.eq(index).attr('value',"");
        			fields.eq(index).next().html("");
        			fields.eq(index).next().next().attr('value',"");
        			fields.eq(index).next().next().next().html("");
        			return false;
    			}
    		});
    	}
    });
});
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.firstChild.innerHTML);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    console.log(data)
    var q=parseInt($('#quantity').text())+parseInt(data);
    $('input[name=itemQuantity]').attr('value',q)
    $('#quantity').text(q);
}
function loadResults(Url){
	$.ajax({
   	 url:Url,
   	 success: function(result){
   		 console.log(result);
   		 $("#searchresult").show();
   	        $("#searchresult").find("tbody").html(result);
   	        }
    });
}