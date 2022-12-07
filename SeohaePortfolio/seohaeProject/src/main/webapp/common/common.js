function addZeros(num, digit) { // 자릿수 맞춰주기
	var zero = '';
	num = num.toString();
	if (num.length < digit) {
		for (i=0; i<digit-num.length; i++) {
	    zero += '0';
	  }
	}
	return zero + num;
}

function gfn_isNull(str) {
    if (str == null) return true;
    if (str == "NaN") return true;
    if (new String(str).valueOf() == "undefined") return true;    
    var chkStr = new String(str);
    if( chkStr.valueOf() == "undefined" ) return true;
    if (chkStr == null) return true;    
    if (chkStr.toString().length == 0 ) return true;   
    return false; 
}
 
function ComSubmit(opt_formId) {
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.url = "";
     
    if(this.formId == "commonForm"){
        $("#commonForm")[0].reset();
    }
     
    this.setUrl = function setUrl(url){
        this.url = url;
    };
     
    this.addParam = function addParam(key, value){
        $("#"+this.formId).append($("<input type='hidden' name='"+key+"' id='"+key+"' value='"+value+"' >"));
    };
     
    this.submit = function submit(){
        var frm = $("#"+this.formId)[0];
        frm.action = this.url;
        frm.method = "post";
        frm.submit();   
    };    
}

function lodingBarStart() {
	var loading = $('<div id="loading" class="loading"></div><img id="loading_img" alt="loading" src="../../images/viewLoading.gif" />').appendTo(document.body).hide();
	loading.show();
}

function ajaxLodingBar() {
	// ajax 로딩바
	var loading = $('<div id="loading" class="loading"></div><img id="loading_img" alt="loading" src="../../images/viewLoading.gif" />').appendTo(document.body).hide();	
	$(window).ajaxStart(function(){
		loading.show();
	}).ajaxStop(function(){
		loading.hide();
	});
}

