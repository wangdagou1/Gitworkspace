package com.wangchuanan.wjj.controller;

import com.wangchuanan.wjj.pojo.MyUser;
import com.wangchuanan.wjj.result.Result;
import com.wangchuanan.wjj.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    MyUserService myUserService;

    /**
     * 登录
     * @param requestUser
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody MyUser requestUser, HttpSession session) {

        //TODO 伪登录，输入正确账号即可
        //对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUserName();
        username = HtmlUtils.htmlEscape(username);

        Object user = myUserService.queryUserByUserName(username);
        if (null == user) {
            return new Result(400,"账号或密码错误！");
        } else {
            session.setAttribute("user", user);
            return new Result(200);
        }
    }
    }


