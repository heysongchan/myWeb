var obj = require('../js/libs/inflate.min.js');
var Zlib = obj.Zlib;

var getHost = (function () {
    var protocol = document.location.protocol;
    var hostname = document.location.hostname;
    var port = document.location.port;
    var path = document.location.pathname;
    var context = path; context = context.substr(1); var i = context.indexOf('/'); context = context.substr(0, i);
    var host = protocol + "//" + hostname + ":" + port + "/" + context;
    function getHost() {
        return host;
    }
    return getHost;
})();



var compression = {
    inflate: function (blob, callback) {
        var fr = new FileReader();
        fr.readAsArrayBuffer(blob);
        fr.onloadend = function (a) {
            var compressed = fr.result;
            var inflate = new Zlib.Inflate(new Uint8Array(compressed));
            var u8array = inflate.decompress();
            callback(u8array);
        }
    },
    u8arrayToStr: function (u8array, encode, callback) {
        var blob = new Blob([u8array]);
        var fr = new FileReader();
        fr.readAsText(blob, encode);
        fr.onloadend = function (a) {
            var str = fr.result;
            callback(str);
        }
    }
};

function doAjax(method, url, data, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.send(data);
    xhr.onloadend = function () {
        callback(xhr.responseText);
    };
    xhr.onerror = function () {
        callback(null);
    }
}

var utils = {
    encode: function (str) {
        // 对字符串进行编码
        var encode = encodeURI(str);
        // 对编码的字符串转化base64
        var base64 = btoa(encode);
        return base64;
    },

    // base64转字符串
    decode: function (base64) {
        // 对base64转编码
        var decode = atob(base64);
        // 编码转字符串
        var str = decodeURI(decode);
        return str;
    },

    selectFile: function (callback) {
        var i = document.createElement("input");
        i.setAttribute('type', 'file');
        i.click();
        i.onchange = function (e) {
            if (i.files.length > 0) {
                var fileObj = i.files[0];
                callback(fileObj);
            }
        }
    },
    download: function (blob, name) {
        var a = document.createElement('a');
        var cc = URL.createObjectURL(blob);
        a.setAttribute('href', cc);
        a.setAttribute('download', name);
        a.click();
    }
}