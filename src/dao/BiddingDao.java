package dao;

import common.DBConnection;
import model.Bidding;
import model.PageModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BiddingDao {

    public int requestForBidding(String uName, int PID) {

        int result = 0;

        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql = "select CNAME from COMPANY where USERNAME = '" + uName + "'";
            ResultSet rs = stmt.executeQuery(sql);

            String cName = null;
            while (rs.next()) {
                cName = rs.getString(1);
            }

            sql = "select * from BIDDINGAPPLY where CNAME = '"+ cName +"' and PID = '" + PID + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) return 2;

            sql = "insert into BIDDINGAPPLY values ('" + cName + "', '" + PID + "')";
            result = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public PageModel getBiddingByPm(PageModel pm) {

        ArrayList bidList = new ArrayList();
        int totalPages, totalRecords = 0;
        int pageSize = pm.getPageSize();
        int currentPage = pm.getCurrentPage();

        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql1 = "select count(*) from BIDDINGAPPLY";
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
            String sql2 = "select * from (select rownum as rowno, CNAME, PNAME from BIDDINGAPPLY b, PROJECT p where b.PID = p.PID) t " +
                    "where t.rowno>" + (currentPage - 1) * pm.getPageSize() + " and" +
                    " t.rowno <=" + currentPage * pm.getPageSize();
            ResultSet rs2 = stmt2.executeQuery(sql2);

            while (rs2.next()) {
                Bidding bidding = new Bidding();
                bidding.setcName(rs2.getString(2));
                bidding.setPName(rs2.getString(3));
                bidList.add(bidding);
            }
            pm.setBidList(bidList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pm;
    }

    public int confirmProject(String cName, String PName) {
        int result = 0;

        try {
            int PID = 0;
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql = "select PID from PROJECT where PNAME = '"+PName+"'";
            ResultSet  rs = stmt.executeQuery(sql);
            while (rs.next()) {PID = rs.getInt(1);}

            sql = "delete from BIDDINGAPPLY where PID = "+PID;
            result = stmt.executeUpdate(sql);


            sql = "update COMPANY set BIDDEDPROJECT = '" + PID + "' where CNAME = '" + cName + "'";
            result = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int denyApply(String cName) {
        int result = 0;

        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql = "delete from BIDDINGAPPLY where CNAME = '" + cName + "'";
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
