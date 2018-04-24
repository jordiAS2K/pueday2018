var finchAddress = "localhost:22179";

function finchBuzzer(frequency,duration) {
	$.get(`http://${finchAddress}/finch/out/buzzer/${frequency}/${duration}`, function(data){
    })
	.fail(function(error) {
		console.log(error); 
	});
}

function freq(note, octave) {
  //http://latecladeescape.com/h/2015/08/frecuencia-de-las-notas-musicales
  return 440.0*Math.exp(((octave-4)+(note-10)/12.0) * Math.log(2.0));
}


function playNote(note, octave, el){
	el.className = el.className + ' down';
	finchBuzzer(freq(note, octave), 1000);
}

function pauseNote(el){
    el.className = el.className.replace(/ down/g, '');
}

function toggle3D(){
	var p = document.getElementById('piano');
	if(p.className === 'p3d'){
		p.className = '';
	}
	else{
		p.className = 'p3d';
	}
}

