package vn.hhh.noti.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import vn.hhh.noti.dto.NotificationRequest;
import vn.hhh.noti.model.Notification;
import vn.hhh.noti.utils.Status;

import java.util.List;



public interface NotificationService {
    List<Notification> getAllNoti(int limit, int offset);
    Notification getById(Integer id) throws NotFoundException;
    Long insert(NotificationRequest request);
    void push(Integer id) throws NotFoundException, JsonProcessingException;
    void delete(Integer id);

}
