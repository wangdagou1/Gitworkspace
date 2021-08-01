package com.wangchuanan.wjj.controller;

import com.wangchuanan.wjj.pojo.MyUser;
import com.wangchuanan.wjj.query.UserQuery;
import com.wangchuanan.wjj.result.Result;
import com.wangchuanan.wjj.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    MyUserService myUserService;

    /**
     * 根据用户名取值
     *
     * @param userName
     * @return
     */
    @GetMapping(value = "/user/name/{userName}")
    public Result queryUserByUserName(@PathVariable String userName) {

        return new Result(200,"success",myUserService.queryUserByUserName(userName));

    }

    /**
     * 根据ID取值
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/user/id/{id}")
    public Result queryUserByUserName(@PathVariable Integer id) {

        return new Result(200,"success",myUserService.queryUserByUserId(id));

    }

    /**
     * 添加用户
     *
     * @param myUser
     * @return
     */
    @PostMapping("/user")
    public Result addUser(@RequestBody @Valid MyUser myUser) {

        myUserService.addUser(myUser);
        return new Result(200,"success");

    }

    /**
     * 添加用户（带参数校验）
     *
     * @param myUser
     * @return
     */
    @PostMapping("/user02")
    public Result addUser02(@RequestBody @Valid MyUser myUser) {

        myUserService.addUser(myUser);
        return new Result(200,"success");

    }

    /**
     * 修改用户
     *
     * @param myUser
     * @return
     */
    @PutMapping("/user")
    public Result updateUser(@RequestBody @Valid MyUser myUser) {

        myUserService.updateUser(myUser);
        return new Result(200,"success");

    }

    /**
     * 修改用户
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/user/{id}")
    public Result updateUser(@PathVariable Integer id) {

        myUserService.deleteUserByid(id);
        return new Result(200,"success");

    }

    /**
     * 获取多条用户数据
     *
     * @param userquery
     * @return
     */
    @RequestMapping("/list")
    public Result queryUserByPage(UserQuery userquery) {

        return new Result(200,"success",myUserService.queryUserByparams(userquery));

    }
}


