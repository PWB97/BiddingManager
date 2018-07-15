package model;


import common.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//该JavaBean应当放在统一的JavaBean文件夹中
public class Project {
    String PID, APPLICANT, PNAME, PCONTENT, PTIME;
    ArrayList companyInProject;
    boolean isProjectBidden;
	StringBuffer queryResult;
    String nameInfo, projectUpdateInfo, projectDeleteInfo;

    public boolean isProjectBidden() {
        return isProjectBidden;
    }

    public void setProjectBidden(boolean projectBidden) {
        isProjectBidden = projectBidden;
    }

    public ArrayList getCompanyInProject() {
        return companyInProject;
    }

    public void setCompanyInProject(ArrayList companyInProject) {
        this.companyInProject = companyInProject;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public void setAPPLICANT(String APPLICANT) {
        this.APPLICANT = APPLICANT;
    }

    public void setPNAME(String PNAME) {
        this.PNAME = PNAME;
    }

    public void setPCONTENT(String PCONTENT) {
        this.PCONTENT = PCONTENT;
    }

    public String getPID() {
        return PID;
    }

    public String getAPPLICANT() {
        return APPLICANT;
    }

    public String getPNAME() {
        return PNAME;
    }

    public String getPTIME() {
        return PTIME;
    }

    public void setPTIME(String PTIME) {
        this.PTIME = PTIME;
    }

    public String getPCONTENT() {
        return PCONTENT;
    }

    public String getProjectDeleteInfo() {
        int count = 0;
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();
            String sql = "delete from PROJECT where PID = " + PID;
            count = stmt.executeUpdate(sql);
            if (count > 0) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count != 0) projectDeleteInfo = "成功";
        else projectDeleteInfo = "失败";
        return projectDeleteInfo;
    }

    public void setProjectDeleteInfo(String projectDeleteInfo) {
        this.projectDeleteInfo = projectDeleteInfo;
    }

    public String getProjectUpdateInfo() {
        int count = 0;
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();
            String sql = "insert into Project(PID, APPLICANT,PNAME ,PCONTENT ,PTIME )" +
                    " values('"+PID+"','"+APPLICANT+"','"+PNAME+"','"+PCONTENT+"',"+"sysdate)";

            count = stmt.executeUpdate(sql);
            if(count>0) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > 0) projectUpdateInfo = "成功";
        else projectUpdateInfo = "失败";
        return projectUpdateInfo;
    }

    public void setProjectUpdateInfo(String projectUpdateInfo) {
        this.projectUpdateInfo = projectUpdateInfo;
    }
}
