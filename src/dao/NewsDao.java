package dao;

import common.DBConnection;
import model.News;
import model.PageModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsDao {

    public PageModel getNewsByPm(PageModel pm){

        ArrayList newsList=new ArrayList();
        int totalPages,totalRecords=0;
        int pageSize=pm.getPageSize();
        int currentPage=pm.getCurrentPage();

        try {
            DBConnection dbc=new DBConnection();
            Connection con=dbc.getCon();
            Statement stmt=con.createStatement();

            String sql1="select count(*) from MESSAGE";
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
            String sql2 = "select * from (select rownum as rowno, ZXID, AUTH,ZXTITLE , ZXMESSAGE, " +
                    "ZXTIME from MESSAGE) t " +
                    "where t.rowno>"+(currentPage-1)*pm.getPageSize()+" and" +
                    " t.rowno <="+currentPage*pm.getPageSize();
            ResultSet rs2 = stmt2.executeQuery(sql2);

            String Title,Message;
            while(rs2.next()){

                News news = new News();
                news.setZXID(rs2.getString(2));
                news.setAUTH(rs2.getString(3));
                Title = rs2.getString(4);
                if(Title.length()>16) Title = Title.substring(0,16)+ "...";
                news.setZXTITLE(replaceBlank(Title));
                Message = rs2.getString(5);
                if (Message.length()>32) Message = Message.substring(0,32)+"...";
                news.setZXMESSAGE(replaceBlank(Message));
                news.setZXTIME(rs2.getString(6));
                newsList.add(news);
            }
            pm.setNewsList(newsList);
        } catch (Exception e) {e.printStackTrace();}
        return pm;
    }

    public PageModel getNewsListByPm(PageModel pm){

        ArrayList newsList=new ArrayList();
        int totalPages=0,totalRecords=0;
        int pageSize=pm.getPageSize();
        int currentPage=pm.getCurrentPage();

        try {
            DBConnection dbc=new DBConnection();
            Connection con=dbc.getCon();
            Statement stmt=con.createStatement();

            String sql1="select count(*) from MESSAGE";
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
            String sql2 = "select * from (select rownum as rowno, ZXID, AUTH,ZXTITLE , ZXMESSAGE, " +
                    "ZXTIME from MESSAGE) t " +
                    "where t.rowno>"+(currentPage-1)*pm.getPageSize()+" and" +
                    " t.rowno <="+currentPage*pm.getPageSize();
            ResultSet rs2 = stmt2.executeQuery(sql2);

            String Title,Message;
            while(rs2.next()){
                News news = new News();
                news.setZXID(rs2.getString(2));
                news.setAUTH(rs2.getString(3));
                Title = rs2.getString(4);
                if(Title.length()>16) Title = Title.substring(0,16)+ "...";
                news.setZXTITLE(replaceBlank(Title));
                Message = rs2.getString(5);
                if (Message.length()>80) Message = Message.substring(0,80)+"...";
                news.setZXMESSAGE(replaceBlank(Message));
                news.setZXTIME(rs2.getString(6));
                newsList.add(news);
            }
            pm.setNewsList(newsList);
        } catch (Exception e) {e.printStackTrace();}
        return pm;
    }

    public News getNewsDetail(String ZXID){
        News news = new News();
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();

            String sql="select AUTH,ZXTITLE,ZXMESSAGE,ZXTIME from MESSAGE"+
            " where ZXID = " + ZXID;
            ResultSet rs=stmt.executeQuery(sql);

            while (rs.next()) {
                news.setZXID(ZXID);
                news.setAUTH(rs.getString(1));
                news.setZXTITLE(rs.getString(2));
                news.setZXMESSAGE(rs.getString(3));
                news.setZXTIME(rs.getString(4));
            }
        }
        catch (Exception e) {e.printStackTrace();}

        return news;
    }

    public ArrayList getNewest(){

        ArrayList newest=new ArrayList();

        try {
            DBConnection dbc=new DBConnection();
            Connection con=dbc.getCon();
            Statement stmt=con.createStatement();

            Statement stmt2=con.createStatement();
            String sql2 = "select * from (select rownum as rowno, ZXID, AUTH,ZXTITLE , ZXMESSAGE, " +
                    "ZXTIME from MESSAGE order by ZXTIME desc) t where t.rowno <= 5";
            ResultSet rs2 = stmt2.executeQuery(sql2);

            String Title,Message;
            while(rs2.next()){
                News news = new News();
                news.setZXID(rs2.getString(2));
                Title = rs2.getString(4);
                if(Title.length()>16) Title = Title.substring(0,16)+ "...";
                news.setZXTITLE(replaceBlank(Title));
                newest.add(news);
            }
        } catch (Exception e) {e.printStackTrace();}
        return newest;
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
