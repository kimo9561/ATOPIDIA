$(function() {
	// ajax를 통해 hospital.xml 파일 받아오기
	var hos_list = new Array();
	$(function() {
		$.ajax({
			url:"hospital.xml",
			method:"post",
			dataType:"xml",
			success:function(data) {
				var list = $(data).find("row");
				hos_list = makeList(list);
				
				var form = document.hos;
				
				$("form").append(
					"<input type='hidden' name='hos_list' value='"+hos_list+"'"+">"	
				);
				
				form.submit();
			},
			error:function() {
				alert("fail");
			}
		});
});

	
});

function makeList(elem) {
	var name = "";
	var x = "";
	var y = "";
	var addr = "";
	var tel = "";
	var hos_no = 0;
	var list = "";
	
	 for(var i=0; i<elem.length; i++) {
		
		for(var j=0; j<elem.eq(i).children().length; j++) {
			if(elem.eq(i).children().eq(j).is("bplcNm")) {
				name = elem.eq(i).children().eq(j).text();
			}else if(elem.eq(i).children().eq(j).is("x")) {
				x = elem.eq(i).children().eq(j).text();
			}else if(elem.eq(i).children().eq(j).is("y")) {
				y = elem.eq(i).children().eq(j).text();
			}else if(elem.eq(i).children().eq(j).is("rdnWhlAddr")) {
				addr = elem.eq(i).children().eq(j).text();
			}else if(elem.eq(i).children().eq(j).is("siteTel")) {
				tel = elem.eq(i).children().eq(j).text();
			}else if(elem.eq(i).children().eq(j).is("rowNum")) {
				hos_no = elem.eq(i).children().eq(j).text();
			}
		}
		
		list += [hos_no+"/"+x.trim()+"/"+y.trim()+"/"+name+"/"+addr+"/"+tel+"#"];

	}
	
	return list;
	
}
