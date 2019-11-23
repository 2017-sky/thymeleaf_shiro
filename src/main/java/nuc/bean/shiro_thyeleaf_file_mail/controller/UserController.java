package nuc.bean.shiro_thyeleaf_file_mail.controller;

import com.sun.org.apache.regexp.internal.RE;
import nuc.bean.shiro_thyeleaf_file_mail.entity.User;
import nuc.bean.shiro_thyeleaf_file_mail.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.rmi.activation.UnknownObjectException;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/index")
public String tologin(Model model){
    model.addAttribute("msg","hello");
        return "index";
}


    //没有权限
    @RequestMapping("/noauth")
    @ResponseBody
    public String noauthc(){
        return "你干啥呢";
    }

//添加功能
    @RequestMapping("/user/add")
    public String insert(){
        return "user/add";
    }

    //更新功能
    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/tologin")
    public String login(){
        return "login";
    }
//对于登录的判断
    @RequestMapping("/login")
public String login(String name, String password,Model model){
    //获取当前的用户
    Subject subject=SecurityUtils.getSubject();
    //封装用户的登录信息
    UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch(IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

}
