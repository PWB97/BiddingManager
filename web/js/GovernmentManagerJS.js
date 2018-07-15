function checkData2(){
    var tmp=document.form1.uName.value;
    if(tmp==null||tmp===""){
        alert("姓名不能为空");
        document.form1.uName.focus();
        return;
    }
    tmp=document.form1.uSex.value;
    if(tmp==null||tmp===""){
        alert("性别不能为空");
        document.form1.uSex.focus();
        return;
    }
    tmp=document.form1.uAge.value;
    if(tmp==null||tmp===""){
        alert("年龄不能为空");
        document.form1.uAge.focus();
        return;
    }
    tmp=document.form1.uTel.value;
    if(tmp==null||tmp===""){
        alert("联系方式不能为空");
        document.form1.uTel.focus();
        return;
    }
    tmp=document.form1.password.value;
    if(tmp==null||tmp===""){
        alert("密码不能为空");
        document.form1.password.focus();
        return;
    }
    document.form1.action="/GovernmentManager/do.jsp";
    document.form1.submit();

}
function checkName2(){
    $.ajax({
        type: "get",
        url: "/GovernmentManager/checkName.jsp",
        data:{ //发送给数据库的数据
            uName:$("#uName").val()
        },
        success: function(data, textStatus){
            $("#nameinfo").html(data);
        }
    });
}
function deleteUser(btn){
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
        url: "/GovernmentManager/deleteUser.jsp",
        data:{ //发送给数据库的数据
            uName:value,
            identify:"GovernmentUser"
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
    document.form2.action="/GovernmentManager/do.jsp";
    document.form2.submit();
}

function changeUser(btn) {
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
        url: "/GovernmentManager/deleteUser.jsp",
        data:{ //发送给数据库的数据
            uName:value,
            identify:"GovernmentUser"
        }
    });
    row.innerHTML =
        "<td><input name='uName'></td>" +
        "<td><input name='uSex'></td>" +
        "<td><input name='uAge'></td>" +
        "<td><input name='uTel'></td>" +
        "<td><button type='button' class='btn btn-link' onclick='doChange(this)'>确定</button></td>" +
        "<td><input type='hidden' value='GovernmentUser' name='identify'></td>";
}