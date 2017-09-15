package com.user.controller;

import com.common.MappingTest;
import com.user.entity.User;
import com.user.service.UserService;
import com.utils.JsonUtil;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String getAnn() {
        MappingTest mappingTest = new MappingTest();
        mappingTest.Tset(this);
        return "login";
    }

    /**
     * 获取集合
     *
     * @return
     */
    @GetMapping("/getUser")
    public String getUser(Page page) {
        return JsonUtil.success(userService.getUser());
    }

    /**
     * 添加对象
     *
     * @param user
     *
     * @return
     */
    @PutMapping("/addUser")
    public String addUser(User user) {
        System.out.println(111);
        return JsonUtil.success(userService.addUser(user));
    }

    /**
     * 删除对象
     *
     * @param id
     *
     * @return
     */
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return JsonUtil.success(userService.deleteUser(id));
    }

    /**
     * 更新对象
     *
     * @param user
     *
     * @return
     */
    @PostMapping("/updateUser")
    public String updateUser(User user) {
        return JsonUtil.success(userService.updateUserById(user));
    }

    /**
     * 获取对象
     *
     * @param id
     *
     * @return
     */
    @GetMapping("/getUser/{id}")
    public String getUserById(@PathVariable("id") Long id) {
        return JsonUtil.success(userService.getUserById(id));
    }
}
