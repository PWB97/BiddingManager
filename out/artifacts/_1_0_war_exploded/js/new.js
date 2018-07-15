function check(){
    var tmp=document.form3.Sex.value;
    if(tmp==null||tmp===""){
        alert("性别不能为空");
        document.form2.Sex.focus();
        return;
    }
    tmp=document.form3.Age.value;
    if(tmp==null||tmp===""){
        alert("年龄不能为空");
        document.form2.Age.focus();
        return;
    }
    tmp=document.form3.Tel.value;
    if(tmp==null||tmp===""){
        alert("联系方式不能为空");
        document.form2.Tel.focus();
        return;
    }
    tmp=document.form3.passwd.value;
    if(tmp==null||tmp===""){
        alert("密码不能为空");
        document.form3.passwd.focus();
        return;
    }
    document.form3.action="/ChangeServlet";
    document.form3.submit();
}