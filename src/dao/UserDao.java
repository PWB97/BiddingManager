package dao;

import common.DBConnection;
import model.PageModel;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDao {

    public PageModel getUsrByPm(PageModel pm){

        ArrayList userList=new ArrayList();
        int totalPages,totalRecords=0;
        int pageSize=pm.getPageSize();
        int currentPage=pm.getCurrentPage();

        try {
            DBConnection dbc=new DBConnection();
            Connection con=dbc.getCon();
            Statement stmt=con.createStatement();

            String sql1="select count(*) from COMPANYUSER";
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
            String sql2 = "select * from (select rownum as rowno, USERNAME, USERSEX, USERAGE, USERTEL from COMPANYUSER) t " +
                    "where t.rowno>"+(currentPage-1)*pm.getPageSize()+" and" +
                    " t.rowno <="+currentPage*pm.getPageSize();
            ResultSet rs2 = stmt2.executeQuery(sql2);

            while(rs2.next()){

                User user = add(rs2);
                userList.add(user);
            }
            pm.setUserList(userList);
        } catch (Exception e) {e.printStackTrace();}

        return pm;
    }

    public static User add(ResultSet rs) throws SQLException {
        User user = new User();
        user.setuName(rs.getString(2));
        user.setuSex(rs.getString(3));
        user.setuAge(rs.getString(4));
        user.setuTel(rs.getString(5));
        return user;
    }

    public int changeUser(String oName, String uName, String uSex, String uAge, String uTel) {

        int result = -1;

        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql = "update COMPANYUSER set USERNAME = '"+uName+"', USERAGE = '"+uAge+"', USERSEX = '"+uSex+"', USERTEL = '"+uTel+"' where USERNAME = '"+oName+"'";
            result = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
