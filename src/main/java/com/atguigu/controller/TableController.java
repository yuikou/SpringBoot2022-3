package com.atguigu.controller;

import com.atguigu.Exception.UserTooManyException;
import com.atguigu.bean.Emp2;
import com.atguigu.bean.User;
import com.atguigu.service.EmpService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {

    @Autowired
    EmpService empService;

    @GetMapping("/basic_table")
    public String basicTable() {

        int i = 10/0;
        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamicTable(@RequestParam(value = "pn", defaultValue = "1")Integer pn, Model model) {

//        //遍歷表單內容
//        List<User> users = Arrays.asList(new User("Mysta","123"),
//                new User("Shu", "456"),
//                new User("Ike", "789"),
//                new User("Luca", "abc"));
//        model.addAttribute("users", users);
//
//        if(users.size() > 3){
//            throw new UserTooManyException();
//        }

//        從DB中取得用戶資料, 進行展示

        List<Emp2> list = empService.list();
//        model.addAttribute("emps", list);
        //參數: 分頁查詢數據; 回傳分頁查詢結果
        Page<Emp2> page = empService.page(new Page<Emp2>(pn, 3), null);
        model.addAttribute("page", page);

        return "table/dynamic_table";
    }

    @GetMapping("/emp2/{empno}")
    //@DeleteMapping("/emp2/{empno}")，表单提交post请求，加上hidden “_method=DELETE”参数
    public String deleteEmp(@PathVariable(value = "empno")Integer empno, @RequestParam(value = "pn", defaultValue = "1")Integer currentPn
    , RedirectAttributes ra){

        empService.removeById(empno);

        ra.addAttribute("pn", currentPn);
        return "redirect:/dynamic_table";
    }

    @GetMapping("/editable_table")
    public String editableTable() {

        return "table/editable_table";
    }

    @GetMapping("/pricing_table")
    public String pricing_Table() {

        return "table/pricing_table";
    }

    @GetMapping("/responsive_table")
    public String responsiveTable() {

        return "table/responsive_table";
    }

    @Value("${person.name:Mysta}")
    private String name;

    @GetMapping("/profiletest")
    @ResponseBody
    public String profileTest(){

        return "Hello " + name;
    }
}
