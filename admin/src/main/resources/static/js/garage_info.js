$.ajax({
    url: '/admin/get_all_garage_info',
    type: 'get',
//  dataType: 'json',
//  data: datas,
    success: function (json) {
        console.log(json);
        var html = "";
        for(var i=0;i<json.length;i++) {
            html = html + "<div class='well well-lg'><a href='#' id='"+ json[i].id + "'>"+
                "车库"+ json[i].id + ":" + json[i]['address']
                 + "</a></div>";
             $("#show_garage").html(html);
        }


    },
    error: function () {
        alert('false');
    }
})

$("#show_garage").on('click', function (e) {
    var ee = e.target;//取出来的元素对象
    console.log(ee.tagName);
    if(ee.tagName != "A")//判断是否是点击的按钮
    {
        return; //不是则返回
    }
    var $garage_id = ee.id; //是则传给车库id
    var datas = {
        id: $garage_id
    };

    $.ajax({
        url: 'admin/get_garage_info_by_id',
        type: 'post',
//                    dataType: 'json',
        data: datas,
        success: function (json){
            console.log(json);
            document.getElementById("all_garage").style.display="none";
            document.getElementById("every_garage").style.display="block";


            $("#garage_id").html("车库" + json['id'] + "具体信息");
            var $html1 = "地址:" + json['address']  + "  |  " + "总车位数量:" + json['totalNum'] + "  |  " +
                "空闲车位数量:" + json['freeNum'];
            // $("#total_num").html("总车位数量：" + json['totalNum']);
            // $("#free_num").html("空闲车位数量：" + json['freeNum']);
            $("#address").html($html1);
            var $html2 = "纬度:" + json['latitude'] + "  |  " + "经度:" + json['longtitude'] + "  |  " +
                "每小时停车费用:" + json['pricePerHour'] + "  |  " +  "状态:" + json['status'];
            $("#latitude").html($html2);
            // $("#longtitude").html("经度：" + json['longtitude']);
            // $("#price_per_hour").html("每小时停车费用：" + json['pricePerHour']);
            // $("#status").html("状态：" + json['status']);

        },
        error: function(){
            alert('false');
        }
    })
    $.ajax({
        url: 'admin/get_car_place',
        type: 'post',
//                    dataType: 'json',
        data: datas,
        success: function (json){
            console.log(json);
            var html = "";
            for(var i=0;i<json.length;i++) {
                html = html +
                    "<tr>" +
                    "<td>" +
                    json[i]["cpNum"] +
                    "</td>" +
                    "<td>" +
                    json[i]["status"] +
                    "</td>"+
                   "</tr>";
                $("#car_place").html(html);
            }
        },
        error: function(){
            alert('false');
        }
    })
})

$("#return").on('click', function (e){
    document.getElementById("all_garage").style.display="block";
    document.getElementById("every_garage").style.display="none";
})