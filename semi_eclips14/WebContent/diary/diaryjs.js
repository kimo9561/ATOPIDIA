function selectPhoto(){
    var fileBtn = document.getElementById("file");
    fileBtn.click();            
}

function loadFile(event){

    var img = document.getElementById("photo");
    img.src = URL.createObjectURL(event.target.files[0]);

    var MAX_WIDTH = 500;
    var MAX_HEIGHT = 450;

    var width = img.width;
    var height = img.height;


    if (width > MAX_WIDTH) {
        width = MAX_WIDTH;
    }           
    if (height > MAX_HEIGHT) {
        height = MAX_HEIGHT;
    }

    document.getElementById("photo").width = width;
    document.getElementById("photo").width = height;

}

function resetAll(){

    document.getElementById("photo").src = "photo.png";
    document.getElementById("diaryText").value = "Today's diary: ";

}

function deleteDiary(){
	
	
}

function allChk(){
	var chks = document.getElementsByName("chk");
	for(var i=0; i<chks.length; i++){
		chks[i].checked = true;
	}
}

function allClear(){
	var chks = document.getElementsByName("chk");
	for(var i=0; i<chks.length; i++){
		chks[i].checked = false;
	}
}




document.getElementById('now_date').valueAsDate = new Date();
