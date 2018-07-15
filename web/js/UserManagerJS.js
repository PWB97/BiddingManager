function LogCheck()
{
    var tmp=document.form1.uName.value;
    if(tmp==null||tmp===""){
        alert("用户名为空!");
        document.form1.uName.focus();
        return;
    }
    tmp=document.form1.password.value;
    if(tmp==null||tmp===""){
        alert("密码为空!");
        document.form1.password.focus();
        return;
    }
    document.form1.action="/LoginServlet";
    document.form1.submit();
}

function checkData(){
    var tmp=document.form1.uName.value;
    if(tmp==null||tmp===""){
        alert("姓名不能为空");
        document.form1.uName.focus();
        return;
    }
    tmp=document.form1.password.value;
    if(tmp==null||tmp===""){
        alert("密码不能为空");
        document.form1.password.focus();
        return;
    }
    document.form1.action="/RegisterServlet";
    document.form1.submit();
}
function checkName() {
    $.ajax({
        type: "get",
        url: "/UserManager/checkName.jsp",
        data: { //发送给数据库的数据
            uName: $("#uName").val()
        },
        success: function (data, textStatus) {
            $("#nameInfo").html(data);
        }
    });
}
function checkData2(){
    var tmp=document.form1.uName.value;
    if(tmp==null||tmp===""){
        alert("姓名不能为空");
        document.form1.uName.focus();
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
    document.form1.action="/UserManager/do.jsp";
    document.form1.submit();

}
function checkName2(){
    $.ajax({
        type: "get",
        url: "/UserManager/checkName.jsp",
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
        url: "/UserManager/deleteUser.jsp",
        data:{ //发送给数据库的数据
            uName:value,
            identify:"CompanyUser"
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
    document.form2.action="/changeUserServlet";
    document.form2.submit();
}

function changeUser(btn, oName) {
    var value;
    var row;
    var ts = $(":disabled");
    for (var i = 0; i < ts.length; i++) {
        if (ts[i].parentNode.parentNode.childNodes[9].childNodes[1].childNodes[3] === btn) {
            value = ts[i].getAttribute("value");
            row = ts[i].parentNode.parentNode;
        }
    }

    row.innerHTML =
        "<td>" +
            "<input name='uName'>" +
            "<input name='oName' value='"+oName+"' type='hidden'>" +
        "</td>"+
        "<td><input name='uSex'></td>" +
        "<td><input name='uAge'></td>" +
        "<td><input name='uTel'></td>" +
        "<td><button type='button' class='btn btn-link' onclick='doChange(this)'>确定</button></td>" +
        "<td><input type='hidden' value='CompanyUser' name='identify'></td>";
}