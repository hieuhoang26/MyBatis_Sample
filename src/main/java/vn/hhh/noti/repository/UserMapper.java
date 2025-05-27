package vn.hhh.noti.repository;

import org.apache.ibatis.annotations.Mapper;
import vn.hhh.noti.model.User;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    void saveUser(User user);
}
