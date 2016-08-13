$(document).ready(function(){
	$('.dp').height($('.dp').width());
	 var width=$('.placeholder-layout').parent().width();
	    $('.placeholder-layout').width((width/10)-15);
	    $('.placeholder-layout').height((width/10)-10);
	$('.collapsible').collapsible({
      accordion : false 
    });
    $('.fixed-action-btn').openFAB();
    $('.fixed-action-btn').closeFAB();
    $('.modal-trigger').leanModal();
    $('select').material_select();
    $('.select-wrapper').find('li').addClass("blue-grey darken-4");
    $('.select-wrapper').find('span').addClass("white-text");
    $('.btn').hover(function(){
    	$(this).removeClass('white');
    	$(this).addClass("blue-grey darken-4");
    	$(this).addClass("white-text");
    	$(this).removeClass("black-text");
    },function(){
    	$(this).addClass('white');
    	$(this).removeClass("blue-grey darken-4");
    	$(this).removeClass("white-text");
    	$(this).addClass("black-text");
    });
    $('#additemselect').change(function(sel){
    	var data=$('option[value='+$(this).val()+']').text();
    	var quantity=$('option[value='+$(this).val()+']').data("quantity");
    	$('#additemname').text(data);
    	$('#oldstock').text(quantity);
    });
    $('#outitemselect').change(function(sel){
    	var data=$('option[value='+$(this).val()+']').text();
    	var quantity=$('option[value='+$(this).val()+']').data("quantity");
    	$('#outitemname').text(data);
    	$('#oldstock').text(quantity);
    });
    $('#resetstock').click(function(ev){
    	
    	ev.preventDefault();
    	$('#newstock').addClass('teal');
    	$('#newstock').removeClass('red darken-1');
    	var newStock=0;
        var oldStock=parseInt($('#oldstock').text())
        $('input[name=itemQuantity]').attr('value',newStock)
        $('#newstock').text(newStock);
        $('#totalstock').text(oldStock-newStock);
        $('#submitbutton').show();
    	$('#resetstock').hide();
    });
    
$('#resetaddstock').click(function(ev){
    	ev.preventDefault();
    	var newStock=0;
        var oldStock=parseInt($('#oldstock').text())
        $('input[name=itemQuantity]').attr('value',newStock)
        $('#newstock').text(newStock);
        $('#totalstock').text(oldStock+newStock);
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
    $('#addNewItem').click(function(){
    	var item=$('#newItem').val();
    	var xmlData="<itemVo><itemName>"+item+"</itemName></itemVo>";
    	$.ajax({
		    url: "http://localhost:8080/WarehouseServices/manager/post/item",
		    data: xmlData, 
		    type: 'POST',
		    contentType: "application/xml",
		    dataType:"html",
		    success : function (response){  
		        console.log(response);
		        var section=$('#select-container');
		        section.html(response);
		        $('select').material_select();
		        return true;
		    },
		    error : function (xhr, ajaxOptions, thrownError){  
		        alert(xhr.status);          
		        alert(thrownError);
		        return false;
		    } 
		});
    });
    $('.pagebutton').click(function(){
    	console.log($(this).data("offset"));
    	console.log($(this).data("uid"));
    	console.log($(this).data("limit"));
    });
    $("#searchresult").on('click','.pagebutton',function(){
    	var endval = $('#floorlist').val();
        var pageURL = $(location). attr("href");
        var endUrl=pageURL+"/floor/"+endval+"?uniqueId="+$(this).data("uid")+"&offset="+$(this).data("offset")+"&limit="+$(this).data("limit");
        console.log(endUrl);
        loadResults(endUrl);
    });
    $("#searchresult").on('click','.arrow',function(){
    	if(!$(this).hasClass("disabled")){
    		var endval = $('#floorlist').val();
            var pageURL = $(location). attr("href");
            var element;
            if($(this).hasClass("left")){
    			element=$('.active.pagebutton').prev();
    		}
    		if($(this).hasClass("right")){
    			element=$('.active.pagebutton').next();
    		}
    		var endUrl=pageURL+"/floor/"+endval+"?uniqueId="+element.data("uid")+"&offset="+element.data("offset")+"&limit="+element.data("limit");
            console.log(endUrl);
            loadResults(endUrl);
    	}
    });
});
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.firstChild.innerHTML);
}

function dropadd(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    console.log(data)
    var q=parseInt($('#newstock').text())+parseInt(data);
    $('input[name=itemQuantity]').attr('value',q)
    $('#newstock').text(q);
    $('#totalstock').text(q+parseInt($('#oldstock').text()));
}
function dropout(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    //console.log(data)
    var newStock=parseInt($('#newstock').text())+parseInt(data);
    var oldStock=parseInt($('#oldstock').text())
    $('input[name=itemQuantity]').attr('value',newStock)
    if(oldStock<newStock){
    	$('#newstock').removeClass('teal');
    	$('#newstock').addClass('red darken-1');
    	$('#submitbutton').hide();
    	$('#resetstock').show();
    }else{
    	$('#newstock').addClass('teal');
    	$('#newstock').removeClass('red darken-1');
    	$('#submitbutton').show();
    	$('#resetstock').hide();
    }
    $('#newstock').text(newStock);
    $('#totalstock').text(oldStock-newStock);
}
function loadResults(Url){
	console.log(Url)
	$.ajax({
   	 url:Url,
   	 success: function(result){
   		 $("#searchresult").show();
   	        $("#searchresult").html(result);
   	        },
	 error : function (xhr, ajaxOptions, thrownError){  
	        console.log(xhr.status);          
	        console.log(thrownError);
	        return false;
	    }
    });
}