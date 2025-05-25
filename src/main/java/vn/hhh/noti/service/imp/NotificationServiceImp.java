package vn.hhh.noti.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import vn.hhh.noti.dto.NotificationRequest;
import vn.hhh.noti.model.Notification;
import vn.hhh.noti.repository.NotificationMapper;
import vn.hhh.noti.service.NotificationService;
import vn.hhh.noti.service.S3Service;
import vn.hhh.noti.service.SqsService;
import vn.hhh.noti.utils.Status;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {

    private final NotificationMapper notificationMapper;
    private final S3Service s3Service;
    private final SqsService sqsService;
    private final ObjectMapper objectMapper;


    @Override
    public List<Notification> getAllNoti() {
        return notificationMapper.findAll();
    }

    @Override
    public Notification getById(Integer id) throws NotFoundException {
        Notification notification = notificationMapper.getById(id);
        if (notification == null) {
            throw new NotFoundException("Notification not found with id: " + id);
        }
        return notification;
    }

    @Override
    public Long insert(NotificationRequest request) {
        String image = s3Service.upload(request.getImageUrl());
        Notification notification = new Notification();
        notification.setTitle(request.getTitle());
        notification.setContent(request.getContent());
        notification.setImageUrl(image);
        notification.setStatus(Status.NOT_PUSHED);
        notification.setPushedAt(null);
        notificationMapper.insert(notification);
        return request.getId();
    }

    @Override
    public void push(Integer id) throws NotFoundException, JsonProcessingException {
        Notification notification = notificationMapper.getById(id);
        if (notification == null) {
            throw new NotFoundException("Notification not found with id: " + id);
        }
        Map<String, Object> message = new HashMap<>();
        message.put("id", notification.getId());
        message.put("title", notification.getTitle());
        message.put("content", notification.getContent());

        String json = objectMapper.writeValueAsString(message);
        sqsService.sendMessage(json);

        notificationMapper.changeStatus(id, Status.PUSHED, LocalDateTime.now());
    }

    @Override
    public void delete(Integer id) {
        notificationMapper.deleteById(id);
    }
}
