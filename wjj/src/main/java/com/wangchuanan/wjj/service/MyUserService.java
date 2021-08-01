package com.wangchuanan.wjj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangchuanan.wjj.dao.MyUserMapper;
import com.wangchuanan.wjj.pojo.MyUser;
import com.wangchuanan.wjj.query.UserQuery;
import com.wangchuanan.wjj.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyUserService {

    @Autowired
    public RedisTemplate redisTemplate;
    @Resource
    private MyUserMapper myUserMapper;

    /**
     * 根据姓名查询用户
     *
     * @param userName
     * @return
     */
    public Object queryUserByUserName(String userName){

        String key = "user:"+userName;
        Object user = redisTemplate.opsForValue().get(key);
        if(user==null){
            System.out.println("查询数据库");
            user = myUserMapper.queryUserByUserName(userName);
            AssertUtil.isTrue( null==user, "查询用户不存在");
            redisTemplate.opsForValue().set(key,user);
        }else{
            System.out.println("查询缓存");
        }

        return user;
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    public MyUser queryUserByUserId(Integer id){

        MyUser user =  myUserMapper.queryUserByUserId(id);
        AssertUtil.isTrue( null==user, "查询用户不存在");
        return user;

    }

    /**
     * 添加操作(带事务)
     *  1.用户名和密码非空
     *  2.调用DAO的添加方法
     *  3.判断受影响的行数
     * @param myUser
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(MyUser myUser) {

        //判断用户名是否存在
        MyUser  existUser =  myUserMapper.queryUserByUserName(myUser.getUserName());
        AssertUtil.isTrue( existUser!=null, "用户名已存在，请重试");
        //执行添加操作 判断受影响行数
        AssertUtil.isTrue(myUserMapper.addUser(myUser)<1,  "添加用户失败");
        //塞值进redis缓存
        String key = "user:"+myUser.getUserName();
        redisTemplate.opsForValue().set(key,myUser);


    }

    /**
     * 1.修改用户(带事务)
     * 2.判断用户是否存在
     * 3.执行更新
     * @param myUser
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(MyUser myUser){

        //判断用户名是否存在
        MyUser  existUser =  myUserMapper.queryUserByUserName(myUser.getUserName());
        AssertUtil.isTrue( existUser!=null, "用户名已存在，请重试");
        //执行添加操作 判断受影响行数
        AssertUtil.isTrue(myUserMapper.updateUser(myUser)<1,  "修改用户失败");
        //塞值进redis缓存
        String key = "user:"+myUser.getUserName();
        redisTemplate.opsForValue().set(key,myUser);
    }

    /**
     * 1.删除用户(带事务)
     * 2.参数校验
     * 3.执行更新
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserByid(Integer id){

        AssertUtil.isTrue(null == id, "用户id不能为空");
        //查询出用户
        MyUser user =  myUserMapper.queryUserByUserId(id);
        //执行删除操作 判断受影响行数
        AssertUtil.isTrue(myUserMapper.deleteUserById(id)<1,  "删除用户失败");
        //删除redis缓存
        String key = "user:"+user.getUserName();
        redisTemplate.delete(key);

    }

    /**
     * 根据条件搜索用户
     * @param userQuery
     * @return
     */ 
    public PageInfo<MyUser> queryUserByparams(UserQuery userQuery){
        List<MyUser> userList = myUserMapper.queryUserByParams(userQuery);
        //开启分页
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        PageInfo<MyUser> pageInfo = new PageInfo<>(userList);
        return pageInfo;

    }

}
