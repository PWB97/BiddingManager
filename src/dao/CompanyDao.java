package dao;

import common.DBConnection;
import model.Company;
import model.PageModel;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompanyDao {

    public PageModel getComByPm(PageModel pm) {

        ArrayList comList = new ArrayList();
        int totalPages = 0, totalRecords = 0;
        int pageSize = pm.getPageSize();
        int currentPage = pm.getCurrentPage();

        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql1 = "select count(*) from COMPANY";
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
            String sql2 = "select * from (select rownum as rowno, CNAME, COMPANY.USERNAME, ADDRESS, TEL, USERAGE, USERSEX" +
                    ", USERTEL from COMPANY, COMPANYUSER where COMPANY.USERNAME = COMPANYUSER.USERNAME) t " +
                    "where t.rowno>" + (currentPage - 1) * pm.getPageSize() + " and" +
                    " t.rowno <=" + currentPage * pm.getPageSize();
            ResultSet rs2 = stmt2.executeQuery(sql2);

            while (rs2.next()) {

                Company company = new Company();
                User user = new User();
                company.setcName(rs2.getString(2));
                company.setcPerson(rs2.getString(3));
                company.setAddress(rs2.getString(4));
                company.setcTel(rs2.getString(5));
                user.setuAge(rs2.getString(6));
                user.setuSex(rs2.getString(7));
                user.setuTel(rs2.getString(8));
                company.setUser(user);
                comList.add(company);
            }
            pm.setComList(comList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pm;
    }

    public int changeCompany(String oName, String cName, String address, String cTel) {

        int result = -1;

        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql = "update COMPANY set CNAME = '"+cName+"', ADDRESS = '"+address+"', TEL = '"+cTel+"' where CNAME = '"+oName+"'";
            result = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
