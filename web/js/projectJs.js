function deleteProject(btn){
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
        url: "/ProjectManager/deleteProject.jsp",
        data:{ //发送给数据库的数据
            PID:value
        },
        success: function(data, textStatus){
            var ele = parser.parseFromString(data, "application/xml");
            var msg = ele.getElementsByTagName("body")[0].textContent.replace(/[\r\n ]/g, "");
            alert(msg);
            location.reload();
        }
    });
}
