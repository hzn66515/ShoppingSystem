var name = 'goods';
// var goods = [{id: "2", number: 20}];
var $ = function (id) {
    return document.getElementById(id);
}

$('newTable').onclick = function (e) {
    var e = arguments[0] || window.event;
    target = e.srcElement ? e.srcElement : e.target;
    if (target.nodeName == "SPAN" && target.className == "moreNum") {
        var num = target.parentElement.children[1].textContent;
        var id = target.parentElement.children[2].textContent;
        num++;
        target.parentElement.children[1].textContent = num;
        util.modifyOne(goods, id, num);
    } else if (target.nodeName == "SPAN" && target.className == "lessNum") {
        var num = target.parentElement.children[1].textContent;
        var id = target.parentElement.children[2].textContent;
        num--;
        if (num < 0) {
            alert("该商品数量为0");
        } else {
            target.parentElement.children[1].textContent = num;
            util.modifyOne(goods, id, num);
        }
    }
    return false;
};


var loading = new Loading();
var layer = new Layer();
$('Account').onclick = function (e) {
    var newTable = $('newTable');
    var goods = [];
    for (var i = 1, rows = newTable.rows.length; i < rows; i++) {
        if (!goods[i - 1]) {
            goods[i - 1] = new Array();
        }
        goods[i - 1]['id'] = newTable.rows[i].cells[1].getElementsByTagName('SPAN')[2].innerHTML;
        goods[i - 1]['goodsId'] = newTable.rows[i].cells[1].getElementsByTagName('SPAN')[3].innerHTML;
        goods[i - 1]['num'] = newTable.rows[i].cells[1].getElementsByTagName('SPAN')[1].innerHTML;
        goods[i - 1]['price'] = newTable.rows[i].cells[2].innerText;
    }
    console.log(goods);
    var newProducts = goods.map(function (arr) {
        return {'id': arr.id, 'goodsId': arr.goodsId, 'num': arr.num,'price':arr.price};
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
