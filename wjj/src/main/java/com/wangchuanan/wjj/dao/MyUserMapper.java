package com.wangchuanan.wjj.dao;

import com.wangchuanan.wjj.pojo.MyUser;
import com.wangchuanan.wjj.query.UserQuery;

import java.util.List;

public interface MyUserMapper {

    /**
     * 根据姓名查询用户
     * @param userName
     * @return
     */
    MyUser queryUserByUserName(String userName);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    MyUser queryUserByUserId(Integer id);

    /**
     * 添加用户
     * @param myUser
     * @return
     */
    int addUser(MyUser myUser);

    /**
     * 修改用户
     * @param myUser
     * @return
     */
    int updateUser(MyUser myUser);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUserById(Integer id);

    /**
     * 以分页的形式查询用户
     * @param userQuery
     * @return
     */
    List<MyUser> queryUserByParams(UserQuery userQuery);

}
