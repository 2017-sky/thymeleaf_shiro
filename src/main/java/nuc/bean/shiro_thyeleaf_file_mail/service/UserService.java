package nuc.bean.shiro_thyeleaf_file_mail.service;

import nuc.bean.shiro_thyeleaf_file_mail.dao.UserDao;
import nuc.bean.shiro_thyeleaf_file_mail.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
     UserDao userDao;
    public User queryUserByName(String name){ return userDao.queryByName(name); }

}
