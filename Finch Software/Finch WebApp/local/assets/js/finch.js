var finchAddress = $("#finch-address").val();
var finchAddresslh = "localhost:22179";
var automaticLights = false;

function finchLED(red, green, blue) {
    $.get(`http://${finchAddress}/finch/out/led/${red}/${green}/${blue}`, function(data){
          
	})
	.fail(function(error) {
		console.log(error); 
	});
}

function finchMotor(left,right) {
    $.get(`http://${finchAddress}/finch/out/motor/${left}/${right}`, function(data){
          
	})
	.fail(function(error) {
		console.log(error); 
	});
}
        
        
function finchBuzzer(frequency,duration) {
    $.get(`http://${finchAddress}/finch/out/buzzer/${frequency}/${duration}`, function(data){
          
	})
	.fail(function(error) {
		console.log(error); 
	});
}
        
function finchSpeak(message) {
    $.get(`http://${finchAddresslh}/speak/${message}`, function(data){
          
	})
	.fail(function(error) {
		console.log(error); 
	});
}

function finchTemperature() {
    $.get(`http://${finchAddress}/finch/in/temperature`, function(data){
          $("#temperature").html(data);
    })
	.fail(function(error) {
		console.log(error); 
	});
}

function finchBrightness() {
    $.get(`http://${finchAddress}/finch/in/lights`, function(data){
		  var sensors = data.split(" ");
          $("#light-level-1").val(sensors[0]);
          $("#light-level-2").val(sensors[1]);
        
          if (sensors[0] < 10 && sensors[1] < 10) {
              automaticLights = true;
              turnOffLED();
              $("#finch-lights").prop('checked', true); 
			  finchLED(255, 255, 0);   
          }
          else if (automaticLights) {
              automaticLights = false;
              $("#finch-lights").prop('checked', false); 
			  turnOffLED();
          }
    })
	.fail(function(error) {
		console.log(error); 
	});
}

function finchCrashDetection() {
	$.get(`http://${finchAddress}/finch/in/obstacles`, function(data){
		  var sensors = data.split(" ");
          $("#finch-obs-left > div").attr("class", sensors[1] == "true"  ? "led-red" : "led-green");
		  $("#finch-obs-right > div").attr("class", sensors[0] == "true" ? "led-red" : "led-green");
		  finchMotor(0,0);
		  
		  if ($("#finch-collisions").is(":checked") && (sensors[0] == "true"|| sensors[1] == "true")) {
			  finchBuzzer(1500, 500);
		  }
    })
	.fail(function(error) {
		console.log(error); 
	});
}

function finchSensors() {
	finchTemperature();
	finchBrightness();
	finchCrashDetection();
}


function turnOffLED() {
	$("#finch-led").prop("checked", false);
	$("#led-red").val(0)
	$("#led-green").val(0)
	$("#led-blue").val(0)
			
	$("#led-color").html("#000000");
	$("#led-color-sample").css("background-color", "#000000");
        
	finchLED(0, 0, 0);   
}


$(document).ready(function() {
            
	$("#finch-ip").dblclick(function() {
	  $("#finch-address").removeAttr("readonly");
	  $("#finch-address").val("");
	});		
		
	$("#finch-address").change(function() {
		finchAddress = $("#finch-address").val();
		$("#finch-address").attr("readonly", "readonly");
	});	
		
    finchSensors();
    setInterval(function(){ 
       finchSensors();
    }, 500);
    
	$("#finch-led").change( function(){
		if($(this).is(':checked') ) {
			$("#led-red").val(255)
			$("#led-green").val(0)
			$("#led-blue").val(0)
			
			$("#led-color").html("#FF0000");
			$("#led-color-sample").css("background-color", "#FF0000");
			$("#finch-lights").prop("checked", false);
			
			finchLED(255, 0, 0);   
		}
		else {
			turnOffLED();
		}
	});
	
    $(".led").on("input change", function() { 
        
        let red = parseInt($("#led-red").val());
        let green = parseInt($("#led-green").val());
        let blue = parseInt($("#led-blue").val());
		var color = sprintf("#%02X%02X%02X", red, green, blue);
        
		$("#finch-led").prop('checked', red !== 0 || green !== 0 || blue !== 0);
        $("#finch-lights").prop("checked", false);
		$("#led-color").html(color);
        $("#led-color-sample").css("background-color", color);
                
        finchLED(red, green, blue);      
    });
            
	$("#finch-lights").change( function(){
        if($(this).is(':checked') ) {
            turnOffLED();
			finchLED(255, 255, 0);   
		}
		else  {
            turnOffLED();
		}
	});
	
	$("#finch-says").change(function() {
		finchSpeak($(this).val());
	});
     
    var keys = {32: "pressed", 37: "left", 38: "up", 39: "right", 40: "down"};
    $(".pad").keydown(function (e) {
        var keyCode = e.which;
        if (!keys[keyCode]) return;
		e.preventDefault();
                
        $("#joystick").addClass(keys[keyCode]);
        if (keyCode == 38) {
			if ($("#finch-obs-right > div").attr("class") != "led-red" && $("#finch-obs-left > div").attr("class") != "led-red") finchMotor(100, 100);
		}			
        if (keyCode == 40) {
			finchMotor(-100, -100);
		}
        if (keyCode == 39) {
			if ($("#finch-obs-right > div").attr("class") != "led-red" && $("#finch-obs-left > div").attr("class") != "led-red") finchMotor(100, -100);
		}			
        if (keyCode == 37) {
			if ($("#finch-obs-right > div").attr("class") != "led-red" && $("#finch-obs-left > div").attr("class") != "led-red") finchMotor(-100, 100);
		}			
        if (keyCode == 32) {
            finchBuzzer(2000, 100);
            finchBuzzer(1000, 100);
			finchBuzzer(2000, 100);
		}
                  
    });
     
	$(".pad").keyup(function (e) {
        var keyCode = e.which;
        if (!keys[keyCode]) return;
        e.preventDefault();
                
        $("#joystick").removeClass(keys[keyCode]);
        if ($("#joystick").attr("class") === "") finchMotor(0,0);
    });
	
	
	
            
});
        
            

/*! sprintf-js v1.1.1 | Copyright (c) 2007-present, Alexandru Mărășteanu <hello@alexei.ro> | BSD-3-Clause */
!function(){"use strict";function e(e){return r(n(e),arguments)}function t(t,r){return e.apply(null,[t].concat(r||[]))}function r(t,r){var n,i,a,o,p,c,u,f,l,d=1,g=t.length,b="";for(i=0;i<g;i++)if("string"==typeof t[i])b+=t[i];else if(Array.isArray(t[i])){if((o=t[i])[2])for(n=r[d],a=0;a<o[2].length;a++){if(!n.hasOwnProperty(o[2][a]))throw new Error(e('[sprintf] property "%s" does not exist',o[2][a]));n=n[o[2][a]]}else n=o[1]?r[o[1]]:r[d++];if(s.not_type.test(o[8])&&s.not_primitive.test(o[8])&&n instanceof Function&&(n=n()),s.numeric_arg.test(o[8])&&"number"!=typeof n&&isNaN(n))throw new TypeError(e("[sprintf] expecting number but found %T",n));switch(s.number.test(o[8])&&(f=n>=0),o[8]){case"b":n=parseInt(n,10).toString(2);break;case"c":n=String.fromCharCode(parseInt(n,10));break;case"d":case"i":n=parseInt(n,10);break;case"j":n=JSON.stringify(n,null,o[6]?parseInt(o[6]):0);break;case"e":n=o[7]?parseFloat(n).toExponential(o[7]):parseFloat(n).toExponential();break;case"f":n=o[7]?parseFloat(n).toFixed(o[7]):parseFloat(n);break;case"g":n=o[7]?String(Number(n.toPrecision(o[7]))):parseFloat(n);break;case"o":n=(parseInt(n,10)>>>0).toString(8);break;case"s":n=String(n),n=o[7]?n.substring(0,o[7]):n;break;case"t":n=String(!!n),n=o[7]?n.substring(0,o[7]):n;break;case"T":n=Object.prototype.toString.call(n).slice(8,-1).toLowerCase(),n=o[7]?n.substring(0,o[7]):n;break;case"u":n=parseInt(n,10)>>>0;break;case"v":n=n.valueOf(),n=o[7]?n.substring(0,o[7]):n;break;case"x":n=(parseInt(n,10)>>>0).toString(16);break;case"X":n=(parseInt(n,10)>>>0).toString(16).toUpperCase()}s.json.test(o[8])?b+=n:(!s.number.test(o[8])||f&&!o[3]?l="":(l=f?"+":"-",n=n.toString().replace(s.sign,"")),c=o[4]?"0"===o[4]?"0":o[4].charAt(1):" ",u=o[6]-(l+n).length,p=o[6]&&u>0?c.repeat(u):"",b+=o[5]?l+n+p:"0"===c?l+p+n:p+l+n)}return b}function n(e){if(i[e])return i[e];for(var t,r=e,n=[],a=0;r;){if(null!==(t=s.text.exec(r)))n.push(t[0]);else if(null!==(t=s.modulo.exec(r)))n.push("%");else{if(null===(t=s.placeholder.exec(r)))throw new SyntaxError("[sprintf] unexpected placeholder");if(t[2]){a|=1;var o=[],p=t[2],c=[];if(null===(c=s.key.exec(p)))throw new SyntaxError("[sprintf] failed to parse named argument key");for(o.push(c[1]);""!==(p=p.substring(c[0].length));)if(null!==(c=s.key_access.exec(p)))o.push(c[1]);else{if(null===(c=s.index_access.exec(p)))throw new SyntaxError("[sprintf] failed to parse named argument key");o.push(c[1])}t[2]=o}else a|=2;if(3===a)throw new Error("[sprintf] mixing positional and named placeholders is not (yet) supported");n.push(t)}r=r.substring(t[0].length)}return i[e]=n}var s={not_string:/[^s]/,not_bool:/[^t]/,not_type:/[^T]/,not_primitive:/[^v]/,number:/[diefg]/,numeric_arg:/[bcdiefguxX]/,json:/[j]/,not_json:/[^j]/,text:/^[^\x25]+/,modulo:/^\x25{2}/,placeholder:/^\x25(?:([1-9]\d*)\$|\(([^\)]+)\))?(\+)?(0|'[^$])?(-)?(\d+)?(?:\.(\d+))?([b-gijostTuvxX])/,key:/^([a-z_][a-z_\d]*)/i,key_access:/^\.([a-z_][a-z_\d]*)/i,index_access:/^\[(\d+)\]/,sign:/^[\+\-]/},i=Object.create(null);"undefined"!=typeof exports&&(exports.sprintf=e,exports.vsprintf=t),"undefined"!=typeof window&&(window.sprintf=e,window.vsprintf=t,"function"==typeof define&&define.amd&&define(function(){return{sprintf:e,vsprintf:t}}))}();
//# sourceMappingURL=sprintf.min.js.map
        