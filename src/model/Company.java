package model;

import common.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Company {
    String cName, cPerson, address, cTel;
    String nameInfo, updateInfo, deleteInfo, personInfo;
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeleteInfo() {
        int count = 0;
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String uName = null;
            String sql = "delete from COMPANY where cName = '"+cName+"'";
            count = stmt.executeUpdate(sql);

            if(count>0) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > 0) deleteInfo = "成功";
        else deleteInfo = "失败";
        return deleteInfo;
    }

    public String getPersonInfo() {
        try {
            DBConnection dbc=new DBConnection();
            Connection con=dbc.getCon();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from COMPANYUSER where USERNAME ='"+cPerson+"'");

            if(rs.next())
                personInfo="";
            else
                personInfo="无此法人";
        }catch(Exception e){e.printStackTrace();}
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public String getUpdateInfo() {
        int count = 0;
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();
            String sql = "insert into COMPANY(CNAME, USERNAME, ADDRESS, TEL) values('"+cName+"','"+cPerson+"','"+address+"','"+cTel+"')";
            count = stmt.executeUpdate(sql);
            if(count>0) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > 0) updateInfo = "成功";
        else updateInfo = "失败";

        return updateInfo;
    }

    public String getNameInfo() {
        try {
            DBConnection dbc=new DBConnection();
            Connection con=dbc.getCon();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from COMPANY where cName='"+cName+"'");
            if(rs.next())
                nameInfo="已有企业";
            else
                nameInfo="";
        }catch(Exception e){e.printStackTrace();}
        return nameInfo;
    }

    public void setDeleteInfo(String deleteInfo) {
        this.deleteInfo = deleteInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public void setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName)  {
        this.cName = cName;
    }

    public String getcPerson() {
        return cPerson;
    }

    public void setcPerson(String cPerson)  {
        this.cPerson = cPerson;
    }

    public String getcTel() {
        return cTel;
    }

    public void setcTel(String cTel) {
        this.cTel = cTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address)  {
        if (address == null) {
            address = "";
        }
        this.address = address;
    }
}
