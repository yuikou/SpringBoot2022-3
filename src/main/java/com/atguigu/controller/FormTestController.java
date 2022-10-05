package com.atguigu.controller;

import com.atguigu.bean.City;
import com.atguigu.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * 文件上傳測試
 */
@Slf4j
@Controller
public class FormTestController {

    @Autowired
    CityService cityService;

    @GetMapping("/form_layouts")
    public String formLayouts(){
        return "form/form_layouts";
    }

    /**
     * MultipartFile 自動封裝上傳過來的文件
     * @param email
     * @param username
     * @param headerImg
     * @param photos
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg")MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        log.info("上傳的參數, email: {}, username: {}, headerImg: {}, photos:{}", email, username, headerImg.getSize(), photos.length);

        if(!headerImg.isEmpty()){
            //保存至server或雲端
            String filename = headerImg.getOriginalFilename() + "uuid";
            headerImg.transferTo(new File("F:\\picold\\test\\"+filename));
        }

        if(photos.length > 0){
            Arrays.stream(photos)
                    .filter(e -> !e.isEmpty())
                    .forEach(e -> {
                        String fname = e.getOriginalFilename() + "multiUUID";
                        try {
                            e.transferTo(new File("F:\\picold\\test\\multiple\\"+fname));
                        } catch (IOException ioException) {
                            log.error(ioException.getMessage());
                        }
                    });
        }

        return "main";
    }

    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id")Integer id){
        return cityService.getCityById(id);
    }
}

