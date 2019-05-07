layer && layer.config({
	extend: ['skin/osa/style.css'], //加载新皮肤
	skin: 'layer-ext-osa' //一旦设定，所有弹层风格都采用此主题。
});

function getChildContent(iframeNo) {
	if (null == iframeNo || undefined == iframeNo) {
		return parent.window.frames[0].frameElement.contentWindow;
	}
	return parent.window.frames[iframeNo].frameElement.contentWindow;
}

Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
		// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
}

function formatNumber(num, precision, separator) {
	var parts;
	// 判断是否为数字
	if (!isNaN(parseFloat(num)) && isFinite(num)) {
		// 把类似 .5, 5. 之类的数据转化成0.5, 5, 为数据精度处理做准, 至于为什么
		// 不在判断中直接写 if (!isNaN(num = parseFloat(num)) && isFinite(num))
		// 是因为parseFloat有一个奇怪的精度问题, 比如 parseFloat(12312312.1234567119)
		// 的值变成了 12312312.123456713
		num = Number(num);
		// 处理小数点位数
		num = (typeof precision !== 'undefined' ? num.toFixed(precision) : num).toString();
		// 分离数字的小数部分和整数部分
		parts = num.split('.');
		// 整数部分加[separator]分隔, 借用一个著名的正则表达式
		parts[0] = parts[0].toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1' + (separator || ','));

		return parts.join('.');
	}
	return NaN;
}

Number.prototype.format=function(decimals, dec_point, thousands_sep){
	var num = (this + '')
		.replace(/[^0-9+\-Ee.]/g, '');
	var n = !isFinite(+num) ? 0 : +num,
		prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
		sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
		dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
		s = '',
		toFixedFix = function(n, prec) {
			var k = Math.pow(10, prec);
			return '' + (Math.round(n * k) / k)
				.toFixed(prec);
		};
	// Fix for IE parseFloat(0.55).toFixed(0) = 0;
	s = (prec ? toFixedFix(n, prec) : '' + Math.round(n))
		.split('.');
	if (s[0].length > 3) {
		s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
	}
	if ((s[1] || '')
			.length < prec) {
		s[1] = s[1] || '';
		s[1] += new Array(prec - s[1].length + 1)
			.join('0');
	}
	return s.join(dec);
}