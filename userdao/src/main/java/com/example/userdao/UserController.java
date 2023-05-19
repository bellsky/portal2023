package com.example.userdao;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.lang.invoke.MutableCallSite;
import java.sql.SQLException;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserDao userDao;

    @RequestMapping(path="/user")
    public User getUser(@RequestParam Long id) throws SQLException, ClassNotFoundException {
        return userDao.findById(id);
    }

    @RequestMapping("/exeption")
    public void exception() {
        throw new RuntimeException("어이쿠?!???");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e",e);
        return modelAndView;

    }

    @RequestMapping(path = "/upload", method = RequestMethod.GET)
    public void upload(){
    }
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getServletContext().getRealPath("/")+
                "/WEB-INF/static/" + file.getOriginalFilename();

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", "/images/"
                + file.getOriginalFilename());
        return modelAndView;
    }
}
