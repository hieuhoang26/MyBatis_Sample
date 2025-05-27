//package vn.hhh.noti.repository;
//
//import org.apache.ibatis.annotations.*;
//import vn.hhh.noti.model.Notification;
//import vn.hhh.noti.utils.Status;
//
//import java.time.LocalDateTime;
//import java.util.List;
//@Mapper
//public interface Annotation {
//    final String GET_ALL = "SELECT * FROM notifications";
//
//    final String GET_ID = "SELECT * FROM notifications n WHERE n.id = #{id}";
//
//    final String INSERT = "INSERT INTO notifications (title, image_url, content, status) " +
//            "VALUES (#{title}, #{imageUrl}, #{content}, #{status})";
//    final String DELETE_ID = "DELETE FROM notifications n WHERE n.id = #{id}";
//
//    final String CHANGE_STATUS = "UPDATE notifications  SET status = #{status}, pushed_at = #{push_at} WHERE id = #{id}";
//
//    @Select(GET_ALL)
//    List<Notification> findAll();
//
//    @Select(GET_ID)
//    Notification getById(Integer id);
//
//    @Insert(INSERT)
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insert(Notification notification);
//
//    @Delete(DELETE_ID)
//    void deleteById(Integer id);
//
//    @Update(CHANGE_STATUS)
//    void changeStatus(Integer id, Status status, LocalDateTime push_at);
//}
