package dao;

import common.DBConnection;
import model.PageModel;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GovernmentDao {

    public PageModel getGoverByPm(PageModel pm){

        ArrayList comList=new ArrayList();
        int totalPages,totalRecords=0;
        int pageSize=pm.getPageSize();
        int currentPage=pm.getCurrentPage();

        try {
            DBConnection dbc=new DBConnection();
            Connection con=dbc.getCon();
            Statement stmt=con.createStatement();

            String sql1="select count(*) from GOVERNMENTUSER";
            ResultSet rs1=stmt.executeQuery(sql1);
            while(rs1.next()) totalRecords=rs1.getInt(1);

            if(totalRecords % pageSize == 0)
                totalPages = totalRecords / pageSize;
            else totalPages = totalRecords / pageSize + 1;

            if(currentPage < 0) currentPage = totalPages;

            pm.setCurrentPage(currentPage);
            pm.setTotalPages(totalPages);
            pm.setTotalRecords(totalRecords);

            Statement stmt2=con.createStatement();
            String sql2 = "select * from (select rownum as rowno, USERNAME, USERSEX, USERAGE, USERTEL from GOVERNMENTUSER) t " +
                    "where t.rowno>"+(currentPage-1)*pm.getPageSize()+" and" +
                    " t.rowno <="+currentPage*pm.getPageSize();
            ResultSet rs2 = stmt2.executeQuery(sql2);

            while(rs2.next()){
                User user = UserDao.add(rs2);
                comList.add(user);
            }
            pm.setComList(comList);
        } catch (Exception e) {e.printStackTrace();}

        return pm;
    }
}
