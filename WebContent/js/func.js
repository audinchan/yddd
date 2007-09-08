String.prototype.trim=function() {return this.replace(/(^\s*)|(\s*$)/g,"");}
String.prototype.ltrim=function() {return this.replace(/(^\s*)/g,"");}
String.prototype.rtrim=function() {return this.replace(/(\s*$)/g,"");}
String.prototype.isInteger=function() {return /^(-|\+)?\d+$/.test(this);}
String.prototype.isPositiveInteger=function() {return /^\d+$/.test(this);}
String.prototype.isNegativeInteger=function() {return /^-\d+$/.test(this);}
//   date (13:04:06)
String.prototype.isTime=function() {
	var a = this.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
	if (a == null) return false;
	if (a[1]>24 || a[3]>60 || a[4]>60) return false;
	return true;
}
//   short date (13:04)
String.prototype.isShortTime=function() {
	var a = this.match(/^(\d{1,2})(:)?(\d{1,2})$/);
	if (a == null) return false;
	if (a[1]>24 || a[3]>60) return false;
	return true;
}
//   date (2003-12-05)
String.prototype.isDate=function() {
         var r = this.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
         if(r==null)return false;
         var d= new Date(r[1], r[3]-1, r[4]);
         return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
}
//   short date (2003-12)
String.prototype.isShortDate=function() {
         var r = this.match(/^(\d{1,4})(-|\/)(\d{1,2})$/);
         if(r==null)return false;
         var d= new Date(r[1], r[3]-1, 1);
         return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]);
}
//   date (2003-12-05 13:04:06)
String.prototype.isDateTime=function() {
        var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
        var r = this.match(reg);
        if(r==null)return false;
        var d= new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
        return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
}

String.prototype.onlyChar=function() {
	return /[^a-zA-Z]/g.test(this);
}

String.prototype.onlyCharNumber=function() {
	return /[^0-9a-zA-Z]/g.test(this);
}

//   char, number, underline dot CharNumberUnderlineDot
String.prototype.onlyCNUD=function() {
	return /^([a-zA-z_]{1})([\w]*)$/g.test(this);
}

//   mail
String.prototype.isEmail=function() {
	return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(this);
}

//zip
String.prototype.isZipCode = function()
{
return /^\d{6}$/.test(this);
}

//   hanzi
String.prototype.existChinese = function() {
	return /^[\x00-\xff]*$/.test(this);
}

//   to int
String.prototype.toInt = function() {
	return parseInt(this);
}

//   char length
String.prototype.charLen = function() {
	var length = 0;
	for (var i = 0; i < this.length; i++) {
		if (this.charCodeAt(i) > 10000) {
			length++;
		}
		length++;
	}
	return length;
}

String.prototype.isEmpty = function() {
	return this.trim().length == 0;
}

String.prototype.isNotEmpty = function() {
	return !this.isEmpty();
}

Date.prototype.dateFormat = function(format) {
    var result = "";
    for (var i = 0; i < format.length; ++i) {
        result += this.dateToString(format.charAt(i));
    }
    return result;
}

Date.prototype.dateToString = function(character) {
    switch (character) {
    case "Y":
        return this.getFullYear();
    case "y": return this.getYear();
    case "m": return this.getMonth() + 1;
    case "d": return this.getDate();
    case "h": return this.getHours();
    case "i": return this.getMinutes();
    case "s": return this.getSeconds();
    default:
        return character;
    }
}

/**
  diff: time different(milliseconds).
*/
Date.prototype.friendlyFormat = function(diff) {
	var currDate = new Date().getTime();
	
	var t = (currDate - this.getTime() - diff) / 1000 / 60; 
	var r = Math.ceil(t);
	if (r < 60) {
		return  r + "分钟前";
	}
	t = t / 60;
	r = Math.ceil(t);
	if (r < 24) {
		return r + "小时前";
	}
	t = t / 24;
	r = Math.ceil(t);
	if (r == 7) {
		return "一个星期前";
	} else if (r == 14) {
		return "两个星期前";
	} else if (r < 50) {
		return r + "天前";
	}
	
	var day = r;
	t = t / 30;
	r = Math.ceil(t);
	
	if (day < 365) {
		return r + "个月前";
	}
	
	return this.dateFormat("Y-m-d");

}


$ = function (id) {
	return document.getElementById(id);
}

function isIE() {
	return (navigator.userAgent.indexOf("MSIE") != -1);
}

function d0(id) {
	$(id).style.display = "none";
}

function d1(id) {
	$(id).style.display = "";
}

function d2(id) {
	$(id).style.display = "block";
}

function showHide(id) {
	var el = $(id);
	if (el) {
		if (el.style.display == "none") {
			el.style.display = "";
		} else {
			el.style.display = "none"
		}
	}
}

function getWindowHeight() {
	if (window.self && self.innerHeight) {
		return self.innerHeight;
	}
	if (document.documentElement && document.documentElement.clientHeight) {
		return document.documentElement.clientHeight;
	}
	return 0;
}

function getWindowWidth() {
	if (window.self && self.innerWidth) {
		return self.innerWidth;
	}
	if (document.documentElement && document.documentElement.clientWidth) {
		return document.documentElement.clientWidth;
	}
	return 0;
}

function errh(msg) {
  alert(msg);
}