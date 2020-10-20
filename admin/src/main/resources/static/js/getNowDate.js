//获取当前时间
function getNowDate() {
    var date = new Date();
    var sign1 = "-";
    var sign2 = ":";
    var year = date.getFullYear() // 年
    var month = date.getMonth() + 1; // 月
    var day  = date.getDate(); // 日
    var hour = date.getHours(); // 时
    var minutes = date.getMinutes(); // 分
    var seconds = date.getSeconds() //秒
    // 给一位数数据前面加 “0”
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
    }
    var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds;
    return currentdate;
}

//获取当前日期
function getCurrentDate() {
    var date = new Date();
    var sign1 = "-";
    var year = date.getFullYear(); // 年
    var month = date.getMonth() + 1; // 月
    var day  = date.getDate(); // 日

    // 给一位数数据前面加 “0”
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    var currentdate = year + sign1 + month + sign1 + day;
    return currentdate;
}

//获取当前时间
function getCurrentTime() {
    var date = new Date();
    var sign2 = ":";
    var hour = date.getHours(); // 时
    var minutes = date.getMinutes(); // 分
    var seconds = date.getSeconds(); //
    // 给一位数数据前面加 “0”

    if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
    }
    var currentTime = hour + sign2 + minutes + sign2 + seconds;
    return currentTime;
}

//时间转换函数
function transferTime(cTime) {
    //将json串的一串数字进行解析
    var jsonDate = new Date(parseInt(cTime));
    //为Date对象添加一个新属性，主要是将解析到的时间数据转换为我们熟悉的“yyyy-MM-dd hh:mm:ss”样式
    Date.prototype.format = function(format) {
        var o = {
            //获得解析出来数据的相应信息，可参考js官方文档里面Date对象所具备的方法
            "y+" : this.getFullYear(),//得到对应的年信息
            "M+" : this.getMonth() + 1, //得到对应的月信息，得到的数字范围是0~11，所以要加一
            "d+" : this.getDate(), //得到对应的日信息
            "h+" : this.getHours(), //得到对应的小时信息
            "m+" : this.getMinutes(), //得到对应的分钟信息
            "s+" : this.getSeconds(), //得到对应的秒信息
        };

        //将年转换为完整的年形式
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1,
                (this.getFullYear() + "")
                    .substr(4 - RegExp.$1.length));
        }

        //连接得到的年月日 时分秒信息
        for ( var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1,
                    RegExp.$1.length == 1 ? o[k] : ("00" + o[k])
                        .substr(("" + o[k]).length));
            }
        }
        return format;
    };
    var newDate = jsonDate.format("yyyy-MM-dd hh:mm:ss");
    return newDate;
}

