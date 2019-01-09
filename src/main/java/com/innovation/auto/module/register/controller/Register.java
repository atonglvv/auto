package com.innovation.auto.module.register.controller;

import com.innovation.auto.entity.User;
import com.innovation.auto.model.APIResult;
import com.innovation.auto.module.register.service.UserService;
import com.innovation.auto.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

/**
 * @Auther: carver
 * @Date: 2018/12/26 15:54
 * @email lt1550919167@163.com
 * @QQ 1550919167
 * @Description:
 */
@RestController
public class Register {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param userName 用户名
     * @param userPassword 用户密码
     * @param email 用户邮箱
     * @param birthday 用户生日
     * @param gender 性别
     * @return
     * @throws ParseException
     */
    @PostMapping("/user/register")
    public APIResult register(@RequestParam(value = "userName",required = true) String userName,
                              @RequestParam(value = "userPassword",required = true) String userPassword,
                              @RequestParam(value = "email",required = true) String email,
                              @RequestParam(value = "birthday",required = true) String birthday,
                              @RequestParam(value = "gender",required = true) String gender) throws ParseException {
        APIResult apiResult = new APIResult();
        apiResult.setMsg("registered successfully...");
        apiResult.setStatus(Constants.SUCCESS);

        User userEmail = userService.selectUserByEmail(email);
        if (null != userEmail) {
            apiResult.setMsg("邮箱已经注册");
            apiResult.setStatus(Constants.ERROR2);
            return apiResult;
        }

        User user = new User();
        user.setName(userName);
        user.setPassword(userPassword);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setGender(gender.equals("男") ? "1" : "0");
        user.setExperience("0");
        user.setCreatedTime(new Date());

        String registerResult = userService.register(user);

        if (registerResult.equals("success")) {
            apiResult.setRes("registered successfully...");
            return apiResult;
        }else {
            apiResult.setMsg("registered failed...");
            apiResult.setStatus(Constants.ERROR);
            return apiResult;
        }

    }




}