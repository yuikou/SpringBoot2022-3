package com.atguigu.controller;

import com.atguigu.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /*
     ** 訪問登入page
     */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {

        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {

        if (!StringUtils.isEmpty(user.getUserName()) && StringUtils.hasLength(user.getPassWord())){

            session.setAttribute("loginUser", user.getUserName());
            //登入成功, 重導至main.html
            //重導可以防止表單重複提交
            return "redirect:/main.html";
        } else{
            model.addAttribute("msg", "帳號密碼錯誤");
            return "login";
        }
    }

    /**
     * 跳轉頁面至main page
     *
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model) {

        //判斷是否登入1. filter 2.攔截器

//        if(null != session.getAttribute("user")){
//            return "main";
//        } else{
//            model.addAttribute("msg", "請重新登入");
//            return "login";
//        }

//        log.info("當前方法為{}", "mainPage");

        model.addAttribute("mainCount", stringRedisTemplate.opsForValue().get("/main.html"));
        model.addAttribute("dept10Count", stringRedisTemplate.opsForValue().get("/dept/10"));

        return "main";
    }
}
