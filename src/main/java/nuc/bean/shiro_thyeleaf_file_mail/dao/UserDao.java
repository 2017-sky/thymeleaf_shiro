package nuc.bean.shiro_thyeleaf_file_mail.dao;

import nuc.bean.shiro_thyeleaf_file_mail.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    public User queryByName(String name);
}
