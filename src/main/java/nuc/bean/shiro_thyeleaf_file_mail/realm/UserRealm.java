package nuc.bean.shiro_thyeleaf_file_mail.realm;

import nuc.bean.shiro_thyeleaf_file_mail.entity.User;
import nuc.bean.shiro_thyeleaf_file_mail.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //授权(给用户赋权限)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
        //拿到当前的登陆用户
        Subject subject = SecurityUtils.getSubject();
       //拿到User对象
        User currentUser= (User) subject.getPrincipal();
        //拿到对应权限（perms）
        info.addStringPermission(currentUser.getPerms());
        return info;

    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //在UserController中获取到的登录 令牌（token）
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //用户，密码（数据库 ）,连接真实数据库
        User user  = userService.queryUserByName(userToken.getUsername());

        if (user == null) {
            return null;
        }
//         //获取session便于对前端的展示信息的判断
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }




}