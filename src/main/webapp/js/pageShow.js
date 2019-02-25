
var $ = function(id){
    return document.getElementById(id);
}

$('plusNum').onclick = function(e){
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    if(num > 0){
        num --;
        $('allNum').innerHTML = num;
    }else{
        alert("您没有购买任何商品");
    }
};

$('addNum').onclick = function(e){
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    num ++;
    $('allNum').innerHTML = num;
};

var loading = new Loading();
var layer = new Layer();


$('add').onclick = function(e){
    var ele = e.target;
    var id = ele && ele.dataset.id;
    var title = ele && ele.dataset.title;
    var price = ele && ele.dataset.price;
    var num = $('allNum').innerText;
    var productDetail = {'goodsId':id,'price':price,'title':title,'num':num};
    console.log(productDetail);
    // var productList1 = new Array;
    // var productList = util.getCookie(name);
    // if(productList == "" || productList == null){
    //     productList1.push(productDetail);
    //     util.setCookie(name,productList1);
    // }else if(util.findOne(productList,id)){
    //     util.modifyTwo(productList,id,num);
    //     util.setCookie(name,productList);
    // }else{
    //     productList.push(productDetail);
    //     util.setCookie(name,productList);
    // }
    // console.log(document.cookie);
//		util.deleteCookie(name);
    e == window.event || e;
    layer.reset({
        content:'确认加入购物车吗？',
        onconfirm:function(){
            layer.hide();
            loading.show();
            var xhr = new XMLHttpRequest();
            var data = JSON.stringify(productDetail);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    var status = xhr.status;
                    if (status >= 200 && status < 300 || status == 304) {
                        var json = JSON.parse(xhr.responseText);
                        if (json && json.code == 200) {
                            loading.result('添加购物车成功', function () {
                                //location.href = '/account';
                            });
                            //util.deleteCookie(name);
                        } else {
                            alert(json.message);
                        }
                    } else {
                        // alert(message || '添加购物车失败');
                        loading.result(message || '服务器异常');
                    }
                }
            };
            xhr.open('post', '/api/addShopCar');
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(data);
            // loading.result('添加购物车成功');
        }.bind(this)
    }).show();
    return;
};