package vn.hhh.noti.Service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import vn.hhh.noti.Dto.NotificationRequest;
import vn.hhh.noti.Mapper.NotificationMapper;
import vn.hhh.noti.Model.Notification;
import vn.hhh.noti.Utils.Status;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationMapper notificationMapper;
    public List<Notification> getAllNoti(){
        return notificationMapper.findAll();
    }

    public Notification getById(Integer id) throws NotFoundException {
        Notification notification = notificationMapper.getById(id);
        if (notification == null) {
            throw new NotFoundException("not found");
        }
        return notification;
    }
    public Long insert(NotificationRequest request){
        notificationMapper.insert(request);
        return request.getId();
    }
    public void changeStatus(Integer id, Status status){
        notificationMapper.changeStatus(id,status);
    }


    public void delete(Integer id){
        notificationMapper.deleteById(id);
    }

}
