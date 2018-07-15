package model;


import common.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class News {
    String ZXID, AUTH, ZXTITLE, ZXMESSAGE, ZXTIME;
    StringBuffer queryResult, newsList, newestNews;
    String nameInfo, newsUpdateInfo, newsDeleteInfo;

    public void setZXID(String ZXID) {
        this.ZXID = ZXID;
    }

    public void setAUTH(String auth) {
        this.AUTH = auth;
    }

    public void setZXTITLE(String ZXTitle) {
        this.ZXTITLE = ZXTitle;
    }

    public void setZXMESSAGE(String ZXMESSAGE) {
        this.ZXMESSAGE = ZXMESSAGE;
    }

    public void setZXTIME(String ZXTIME) {
        this.ZXTIME = ZXTIME;
    }

    public String getZXTIME() {
        return ZXTIME;
    }

    public String getZXID() {

        return ZXID;
    }

    public String getAUTH() {
        return AUTH;
    }

    public String getZXTITLE() {
        return ZXTITLE;
    }

    public String getZXMESSAGE() {
        return ZXMESSAGE;
    }

    public StringBuffer getqueryResult() {
        Statement sql;
        ResultSet rs;
        try {
            queryResult = new StringBuffer();
            DBConnection dbc = new DBConnection();
            Connection conn = dbc.getCon();
            sql = conn.createStatement();
            rs = sql.executeQuery("SELECT * FROM MESSAGE");
            while (rs.next()) {
                String title = rs.getString(3);
                if (title.length() > 12) {
                    title = title.substring(0, 12) + "...";
                }
                String content = rs.getString(4);
                if (content.length() > 30) {
                    content = content.substring(0, 30) + "...";
                }
                queryResult.append("<tr>");
                queryResult.append("<td>" + rs.getString(2) + "</td>");
                queryResult.append("<td>" + title + "</td>");
                queryResult.append("<td>" + content + "</td>");
                queryResult.append("<td>" + rs.getString(5) + "</td>");
                queryResult.append("</tr>");
            }

            conn.close();
        } catch (SQLException e) {
            queryResult.append(e);
        }
        return queryResult;
    }

    public StringBuffer getnewestNews() {
        Statement sql;
        ResultSet rs;
        try {
            newestNews = new StringBuffer();
            DBConnection dbc = new DBConnection();
            Connection conn = dbc.getCon();
            sql = conn.createStatement();
            rs = sql.executeQuery("SELECT * FROM MESSAGE");
            int i = 1;
            while (rs.next()) {
                String title = rs.getString(3);
                if (title.length() > 60) {
                    title = title.substring(0, 60) + "...";
                }

                newestNews.append("<li><a href=\"#\" title=");
                newestNews.append(title);
                newestNews.append("\"><span>" + i + "</span><h3>");
                newestNews.append(title);
                newestNews.append("</h3></a></li>");
                i++;
            }

            conn.close();
        } catch (SQLException e) {
            newestNews.append(e);
        }
        return newestNews;
    }

    public StringBuffer getnewsList() {
        Statement sql;
        ResultSet rs;
        try {
            newsList = new StringBuffer();
            DBConnection dbc = new DBConnection();
            Connection conn = dbc.getCon();
            sql = conn.createStatement();
            rs = sql.executeQuery("SELECT * FROM MESSAGE");
            while (rs.next()) {
                String title = rs.getString(3);
                if (title.length() > 60) {
                    title = title.substring(0, 60) + "...";
                }
                String content = rs.getString(4);
                if (content.length() > 80) {
                    content = content.substring(0, 80) + "...";
                }
                newsList.append("<div class=\"news_info\">");
                newsList.append("<h4>" + title + "</h4>");
                newsList.append("<p>" + rs.getString(5) + "</p>");
                newsList.append("<p>" + content + "</p>");
                newsList.append("<hr>");
                newsList.append("</div>");
            }

            conn.close();
        } catch (SQLException e) {
            newsList.append(e);
        }
        return newsList;
    }

    public String getnewsDeleteInfo() {
        int count = 0;
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();
            String sql = "delete from MESSAGE where ZXID = " + ZXID ;
            count = stmt.executeUpdate(sql);
            if (count > 0) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count != 0) newsDeleteInfo = "成功";
        else newsDeleteInfo = "失败";
        return newsDeleteInfo;
    }

    public void setNewsDeleteInfo(String newsDeleteInfo) {
        newsDeleteInfo = newsDeleteInfo;
    }

    public String getNewsUpdateInfo() {
        int count = 0;
        try {
            DBConnection dbc = new DBConnection();
            Connection con = dbc.getCon();
            Statement stmt = con.createStatement();
            String sql = "insert into Message(ZXID, AUTH, ZXTITLE, ZXMESSAGE, ZXTIME)" +
                    " values('"+ZXID+"','"+AUTH+"','"+ZXTITLE+"','"+ZXMESSAGE+"',"+"sysdate)";

            count = stmt.executeUpdate(sql);
            if(count>0) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > 0) newsUpdateInfo = "成功";
        else newsUpdateInfo = "失败";
        return newsUpdateInfo;
    }

    public void setNewsUpdateInfo(String newsUpdateInfo) {
        this.newsUpdateInfo = newsUpdateInfo;
    }
}
