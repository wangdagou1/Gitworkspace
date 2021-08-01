package com.wangchuanan.wjj.query;

/**
 * 配置用户分页查询参数实体类
 */
public class UserQuery {

    //当前页数
    private Integer pageNum = 1;
    //每页显示条数
    private Integer pageSize = 10;
    //用户名（搜索条件）
    private String userName;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
