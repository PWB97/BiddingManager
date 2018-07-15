package model;

import common.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    String uName, uSex, uAge, uTel,password="123456",identify = "1";
    String nameInfo, updateInfo, deleteInfo, logInfo1, logInfo2, logInfo3;

    public String getDeleteInfo() {
        int count = 0;
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();
            if (identify.equals("CompanyUser")){
                String cName = null;
                
                String sql = "select CNAME from COMPANY where USERNAME = '" + uName + "'";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) { cName = rs.getString(1); }
                
                sql = "delete from BIDDINGAPPLY where CNAME = '" + cName + "'";
                stmt.executeUpdate(sql);
                
                sql = "delete from COMPANY where USERNAME = '" + uName + "'";
                stmt.executeUpdate(sql);
                
                sql = "delete from COMPANYUSER where USERNAME = '" + uName + "'";
                count = stmt.executeUpdate(sql);
                if (count > 0) con.close();
            }
            else {
                if (identify.equals("GovernmentUser")){
                    String sql = "delete from GOVERNMENTUSER where USERNAME = '" + uName + "'";
                    count = stmt.executeUpdate(sql);
                    if (count > 0) con.close();
                }
                else {
                    String sql = "delete from MANAGERUSER where USERNAME = '" + uName + "'";
                    count = stmt.executeUpdate(sql);
                    if (count > 0) con.close();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > 0) deleteInfo = "成功";
        else deleteInfo = "失败";
        return deleteInfo;
    }

    void write(ResultSet rs) throws SQLException {
        uName = rs.getString(1);
        uSex = rs.getString(2);
        uAge = rs.getString(3);
        uTel = rs.getString(4);
    }

    public String getLogInfo1() {
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from COMPANYUSER where USERNAME='" + uName + "'");
            if (rs.next()) {
                logInfo2 = "用户名或密码不正确";
                logInfo3 = "用户名或密码不正确";
                if (rs.getString(5).equals(password)) {
                    logInfo1 = "登录成功";
                    write(rs);
                }
                else
                {
                    logInfo1 = "用户名或密码不正确";
                }
            }
            else {
                logInfo1 = "用户名或密码不正确";
                ResultSet rs2 = stmt.executeQuery("select * from GOVERNMENTUSER where USERNAME='" + uName + "'");
                if (rs2.next()) {
                    logInfo3 = "用户名或密码不正确";
                    if (rs2.getString(5).equals(password)) {
                        logInfo2 = "登录成功";
                        write(rs2);
                    }
                    else
                    {
                        logInfo2 = "用户名或密码不正确";
                    }
                }
                else {
                    logInfo2 = "用户名或密码不正确";
                    ResultSet rs3 = stmt.executeQuery("select * from MANAGERUSER where USERNAME='" + uName + "'");
                    if (rs3.next()) {
                        if (rs3.getString(5).equals(password)) {
                            logInfo3 = "登录成功";
                            write(rs3);
                        }
                        else
                        {
                            logInfo3 = "用户名或密码不正确";
                        }
                    }
                    else{
                        logInfo3 = "用户名或密码不正确";
                    }
                }

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return logInfo1;
    }

    public String getUpdateInfo() {
        int count = 0;
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();
            if (identify.equals("CompanyUser")) {
                String sql = "insert into COMPANYUSER(USERNAME, USERSEX, USERAGE, USERTEL,PASSWORD) values('" + uName + "','" + uSex + "','" + uAge + "','" + uTel + "','" + password + "')";
                count = stmt.executeUpdate(sql);
                if (count > 0) con.close();
            }
            else{
                if (identify.equals("GovernmentUser")){
                    String sql = "insert into GOVERNMENTUSER(USERNAME, USERSEX, USERAGE, USERTEL,PASSWORD) values('" + uName + "','" + uSex + "','" + uAge + "','" + uTel + "','" + password + "')";
                    count = stmt.executeUpdate(sql);
                    if (count > 0) con.close();
                }
                else{
                    String sql = "insert into MANAGERUSER(USERNAME, USERSEX, USERAGE, USERTEL,PASSWORD) values('" + uName + "','" + uSex + "','" + uAge + "','" + uTel + "','" + password + "')";
                    count = stmt.executeUpdate(sql);
                    if (count > 0) con.close();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > 0) updateInfo = "成功";
        else updateInfo = "失败";
        return updateInfo;
    }

    public String getNameInfo() {
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from (select USERNAME from COMPANYUSER union select USERNAME from GOVERNMENTUSER union select USERNAME from MANAGERUSER ) where USERNAME = '" + uName + "'");
            if (resultSet.next()) {
                nameInfo = "用户名已占用";
            } else {
                nameInfo = "用户名可用";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameInfo;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public String getuAge() {
        return uAge;
    }

    public void setuAge(String uAge) {
        this.uAge = uAge;
    }

    public String getuTel() {
        return uTel;
    }

    public void setuTel(String uTel) {
        this.uTel = uTel;
    }

    public void setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public void setDeleteInfo(String deleteInfo) {
        this.deleteInfo = deleteInfo;
    }

    public void setLogInfo1(String logInfo1) {
        this.logInfo1 = logInfo1;
    }

    public String getLogInfo2() {
        return logInfo2;
    }

    public void setLogInfo2(String logInfo2) {
        this.logInfo2 = logInfo2;
    }

    public String getLogInfo3() {
        return logInfo3;
    }

    public void setLogInfo3(String logInfo3) {
        this.logInfo3 = logInfo3;
    }
}
