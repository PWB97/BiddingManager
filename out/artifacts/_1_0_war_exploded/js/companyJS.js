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
        url: "/CompanyManager/deleteCompany.jsp",
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

function doChange() {
    document.form2.action="/changeCompanyServlet";
    document.form2.submit();
}

function changeCompany(btn, oName, oPerson) {
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
            "<input name='cName' id='cName' >" +
            "<input type='hidden' name='oName' id='oName' value='"+oName+"' >" +
            "<small id='nameinfo' class='form-text text-muted'></small>" +
        "</td>" +
        "<td>" +
            "<input disabled='disabled' value='"+oPerson+"'>" +
        "</td>" +
        "<td><input name='address'></td>" +
        "<td><input name='cTel'></td>" +
        "<td><button type='button' class='btn btn-link' onclick='doChange()'>确定</button></td>";
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
        url: "/CompanyManager/checkName.jsp",
        data:{ //发送给数据库的数据
            cName:$("#cName").val()
        },
        success: function(data, textStatus){
            $("#nameinfo").html(data);
        }
    });
}