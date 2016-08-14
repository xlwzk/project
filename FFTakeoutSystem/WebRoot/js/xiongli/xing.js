
$(function(){
	
});

function assess(id){
	 var color= $("#"+id).find("font").attr("color"); 
	 if(color=='#ccc'){
         for ( var i = 1; i <= id; i++) {
		  $("#"+i).find("font").attr("color","red");
		 }
         $("#shopAssess").show();
     }else{    	 
    	 for ( var i = id; i <=5; i++) {
   		  $("#"+i).find("font").attr("color","#ccc");
   		 }
     }
	 var colors= $("#1").find("font").attr("color");
	 if(colors=='#ccc'){
		 $("#shopAssess").attr("hidden","true")
	 }
	 
}