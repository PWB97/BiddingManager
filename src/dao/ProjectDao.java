package dao;

import common.DBConnection;
import model.PageModel;
import model.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectDao {

    public PageModel getProjectByPm(PageModel pm) {

        ArrayList projectList = new ArrayList();
        int totalPages, totalRecords = 0;
        int pageSize = pm.getPageSize();
        int currentPage = pm.getCurrentPage();

        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql1 = "select count(*) from PROJECT";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) totalRecords = rs1.getInt(1);

            if (totalRecords % pageSize == 0)
                totalPages = totalRecords / pageSize;
            else totalPages = totalRecords / pageSize + 1;

            if (currentPage < 0) currentPage = totalPages;

            pm.setCurrentPage(currentPage);
            pm.setTotalPages(totalPages);
            pm.setTotalRecords(totalRecords);

            Statement stmt2 = con.createStatement();
            String sql2 = "select * from (select rownum as rowno,PID ,APPLICANT ,PNAME , " +
                    "PCONTENT, PTIME from PROJECT) t " +
                    "where t.rowno>" + (currentPage - 1) * pm.getPageSize() + " and" +
                    " t.rowno <=" + currentPage * pm.getPageSize();
            ResultSet rs2 = stmt2.executeQuery(sql2);

            String name, content;
            while (rs2.next()) {

                Project project = new Project();
                project.setPID(rs2.getString(2));
                project.setAPPLICANT(rs2.getString(3));
                name = rs2.getString(4);
                if (name.length() > 16) name = name.substring(0, 16) + "...";
                int a = name.indexOf('\n');

                project.setPNAME(replaceBlank(name));
                content = rs2.getString(5);
                if (content.length() > 32) content = content.substring(0, 32) + "...";
                project.setPCONTENT(replaceBlank(content));
                project.setPTIME(rs2.getString(6));
                projectList.add(project);
            }
            pm.setProjectList(projectList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    public Project getProjectDetail(String PID){
        Project project = new Project();
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql="select APPLICANT,PNAME,PCONTENT,PTIME from PROJECT where PID =" + PID;
            ResultSet rs=stmt.executeQuery(sql);

            while (rs.next()) {
                project.setPID(PID);
                project.setAPPLICANT(rs.getString(1));
                project.setPNAME(rs.getString(2));
                project.setPCONTENT(rs.getString(3));
                project.setPTIME(rs.getString(4));
            }

            sql = "select CNAME from COMPANY where BIDDEDPROJECT = " + PID;
            rs = stmt.executeQuery(sql);
            ArrayList companyInProject = new ArrayList();

            if (rs.next()) {

                project.setProjectBidden(true);
                companyInProject.add(rs.getString(1));
                project.setCompanyInProject(companyInProject);

            } else {

                project.setProjectBidden(false);
                String sql1 = "select CNAME from BIDDINGAPPLY where PID = " + PID;
                rs = stmt.executeQuery(sql1);
                while (rs.next()) {
                    companyInProject.add(rs.getString(1));
                }

                project.setCompanyInProject(companyInProject);
            }
        }

        catch (Exception e) {e.printStackTrace();}

        return project;
    }

    public ArrayList getNewProject(){

        ArrayList newProject =new ArrayList();

        try {
            DBConnection dbc=new DBConnection();
            Connection con=dbc.getCon();
            Statement stmt=con.createStatement();

            Statement stmt2=con.createStatement();
            String sql2 = "select * from (select rownum as rowno, PNAME from PROJECT order by PTIME desc) t where t.rowno <= 5";
            ResultSet rs2 = stmt2.executeQuery(sql2);

            String Title,Message;
            while(rs2.next()){
                Project project = new Project();
                project.setPNAME(rs2.getString(2));
                newProject.add(project);
            }
        } catch (Exception e) {e.printStackTrace();}
        return newProject;
    }

    private static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("<p>|</p>");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}