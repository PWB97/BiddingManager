function deleteCompany(btn){
    var value;
    var ts = $(":disabled");
    for (var i = 0; i < ts.length; i++) {
        if (ts[i].parentNode.parentNode.childNodes[9].childNodes[1].childNodes[1] === btn) {
            value = ts[i].getAttribute("value");
        }
    }
    var parser = new DOMParser();
    $.ajax({
        type: "get",
        url: "deleteCompany.jsp",
        data:{ //发送给数据库的数据
            cName:value
        },
        success: function(data, textStatus){
            var ele = parser.parseFromString(data, "application/xml");
            var msg = ele.getElementsByTagName("body")[0].textContent.replace(/[\r\n ]/g, "");
            alert(msg);
            location.reload();
        }
    });
}

function doChange(btn) {
    document.form2.action="do.jsp";
    document.form2.submit();
}

function changeCompany(btn) {
    var value;
    var row;
    var ts = $(":disabled");
    for (var i = 0; i < ts.length; i++) {
        if (ts[i].parentNode.parentNode.childNodes[9].childNodes[1].childNodes[3] === btn) {
            value = ts[i].getAttribute("value");
            row = ts[i].parentNode.parentNode;
        }
    }
    var parser = new DOMParser();
    $.ajax({
        type: "get",
        url: "deleteCompany.jsp",
        data:{ //发送给数据库的数据
            cName:value
        }
    });
    row.innerHTML =
        "<td><input name='cName'></td>" +
        "<td><input name='cPerson'></td>" +
        "<td><input name='address'></td>" +
        "<td><input name='tel'></td>" +
        "<td><button type='button' class='btn btn-link' onclick='doChange(this)'>确定</button></td>";
}

function checkData(){
    var tmp=document.form1.cName.value;
    if(tmp==null||tmp===""){
        alert("名称不能为空");
        document.form1.cName.focus();
        return;
    }
    tmp=document.form1.cPerson.value;
    if(tmp==null||tmp===""){
        alert("法人不能为空");
        document.form1.cPerson.focus();
        return;
    }
    document.form1.action="do.jsp";
    document.form1.submit();

}

function checkName(){
    $.ajax({
        type: "get",
        url: "checkName.jsp",
        data:{ //发送给数据库的数据
            cName:$("#cName").val()
        },
        success: function(data, textStatus){
            $("#nameinfo").html(data);
        }
    });
}