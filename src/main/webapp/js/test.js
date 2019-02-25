(function (w, d, u) {
    var settleAccount = util.get('settleAccount');
    if (!settleAccount) {
        return;
    }
    var name = 'goods';
    var goods = [{id:"2",number:20}];
    // util.getCookie(name);
    var $ = function (id) {
        return document.getElementById(id);
    }
    console.log($('newTable').rows.length);
    var newTable = $('newTable');
    var data1 = [];
    for(var i=0,rows=newTable.rows.length; i<rows; i++){
        for(var j=0,cells=newTable.rows[i].cells.length; j<cells; j++){
            if(!data1[i]){
                data1[i] = new Array();
            }
            data1[i][j] = newTable.rows[i].cells[j].innerHTML;
        }
    }
    console.log(data1);
    // var str = "<tr>" +
    //     "<th>" + '内容名称' + "</th>" +
    //     "<th>" + '数量' + "</th>" +
    //     "<th>" + '价格' + "</th>" +
    //     "</tr>";
    //
    // if (document.cookie.indexOf(name)!=-1) {
    //     for (var i = 0; i < goods.length; i++) {
    //         str = str +
    //             "<tr>" +
    //             "<td>" + goods[i].title + "</td>" +
    //             "<td>" +
    //             "<span class=\"lessNum\">" + "-" + "</span>" +
    //             "<span class=\"totalNum\" id=\"allNum\">" + goods[i].num + "</span>" +
    //             "<span id=\"thisId\">" + goods[i].id + "</span>" +
    //             "<span class=\"moreNum\">" + "+" + "</span>" + "</td>" +
    //             "<td>" + goods[i].price + "</td>" +
    //             "</tr>";
    //     }
    // }

    // $("newTable").innerHTML = str;

    window.onload = function () {
        $('newTable').onclick = function (e) {
            var e = arguments[0] || window.event;
            target = e.srcElement ? e.srcElement : e.target;
            if (target.nodeName == "SPAN" && target.className == "moreNum") {
                var num = target.parentElement.children[1].textContent;
                var id = target.parentElement.children[2].textContent;
                num++;
                target.parentElement.children[1].textContent = num;
                // util.modifyOne(goods, id, num);
            } else if (target.nodeName == "SPAN" && target.className == "lessNum") {
                var num = target.parentElement.children[1].textContent;
                var id = target.parentElement.children[2].textContent;
                num--;
                if (num < 0) {
                    alert("该商品数量为0");
                } else {
                    target.parentElement.children[1].textContent = num;
                    // util.modifyOne(goods, id, num);
                }
            }
            return false;
        };
    };

    var loading = new Loading();
    var layer = new Layer();
    $('Account').onclick = function (e) {
        var newProducts = goods.map(function (arr) {
            return {'id': arr.id, 'number': arr.num};
        });
        console.log(newProducts);
        var ele = e.target;
        layer.reset({
            content: '确认购买吗？',
            onconfirm: function () {
                layer.hide();
                loading.show();

                var xhr = new XMLHttpRequest();
                var data = JSON.stringify(newProducts);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4) {
                        var status = xhr.status;
                        if (status >= 200 && status < 300 || status == 304) {
                            var json = JSON.parse(xhr.responseText);
                            if (json && json.code == 200) {
                                loading.result('购买成功', function () {
                                    location.href = '/account';
                                });
                                util.deleteCookie(name);
                            } else {
                                alert(json.message);
                            }
                        } else {
                            loading.result(message || '购买失败');
                        }
                    }
                };
                xhr.open('post', '/api/buy');
                xhr.setRequestHeader('Content-Type', 'application/json');
                xhr.send(data);
            }.bind(this)
        }).show();
        return;
    };
    $('back').onclick = function () {
        location.href = window.history.back();
    }
})(window, document);