package vn.hhh.noti.repository;

import org.apache.ibatis.annotations.*;
import vn.hhh.noti.dto.NotificationRequest;
import vn.hhh.noti.model.Notification;
import vn.hhh.noti.utils.Status;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface NotificationMapper {

    List<Notification> findAll(@Param("limit") int limit, @Param("offset") int offset);

    Notification getById(@Param("id") Integer id);

    void insert(Notification notification);

    void deleteById(@Param("id") Integer id);

    void changeStatus(@Param("id") Integer id, @Param("status") Status status, @Param("push_at") LocalDateTime push_at);


}