package model;

import java.util.ArrayList;

public class PageModel {
    int pageSize = 5;
    int currentPage;
    int totalPages;
    int totalRecords;
    ArrayList comList;
    ArrayList newsList;
    ArrayList projectList;
    ArrayList userList;
    ArrayList bidList;


    public ArrayList getBidList() {
        return bidList;
    }

    public void setBidList(ArrayList bidList) {
        this.bidList = bidList;
    }

    public ArrayList getUserList() {
        return userList;
    }

    public void setUserList(ArrayList userList) {
        this.userList = userList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public ArrayList getComList() {
        return comList;
    }

    public void setComList(ArrayList comList) {
        this.comList = comList;
    }

    public void setNewsList(ArrayList newsList){this.newsList = newsList;}

    public ArrayList getNewsList(){return newsList;}

    public ArrayList getProjectList() { return projectList; }

    public void setProjectList(ArrayList projectList) { this.projectList = projectList; }
}
