//package vn.hhh.noti.service;
//
//import org.apache.ibatis.javassist.NotFoundException;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import vn.hhh.noti.dto.NotificationRequest;
//import vn.hhh.noti.repository.NotificationMapper;
//import vn.hhh.noti.model.Notification;
//import vn.hhh.noti.service.imp.NotificationServiceImp;
//import vn.hhh.noti.utils.Status;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class NotificationServiceTest {
//
//    private NotificationService notificationService; // Stubbing
//
//    private @Mock NotificationMapper notificationMapper;
//
//    private static Notification success;
//    private static Notification error;
//
//    @BeforeAll
////    Dữ liệu giả lập
//    static void BeforeAll() {
//        success = new Notification();
//        success = new Notification();
//        success.setId(1L);
//        success.setTitle("Success Notification");
//        success.setImageUrl("https://example.com/success.png");
//        success.setContent("Operation completed successfully.");
//        success.setStatus(Status.PUSHED);
//        success.setPushedAt(LocalDateTime.now());
//        success.setCreatedAt(LocalDateTime.now());
//
//        error = new Notification();
//        error.setId(2L);
//        error.setTitle("Error Notification");
//        error.setImageUrl("https://example.com/error.png");
//        error.setContent("An error occurred.");
//        error.setStatus(Status.NOT_PUSHED);
//        error.setPushedAt(LocalDateTime.now());
//        error.setCreatedAt(LocalDateTime.now());
//
//    }
//
//    @BeforeEach
//    void setUp() {
//        // Khởi tạo lớp triển khai của UserService
//        notificationService = new NotificationServiceImp(notificationMapper,null,null);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void tesGetAllNoti_Success() {
//        // Giả lập phương thức
//        List<Notification> list = new ArrayList<>(List.of(success, error));
//        when(notificationMapper.findAll()).thenReturn(list);
//
//        // Method need test
//        List<Notification> rs = notificationService.getAllNoti();
//
//        Assertions.assertNotNull(rs);
//        assertEquals(2, rs.size());  // Spring boot load
//    }
//
//    @Test
//    void tesGetAllNoti_Empty() {
//        // Giả lập phương thức
//        List<Notification> list = new ArrayList<>(List.of());
//        when(notificationMapper.findAll()).thenReturn(list);
//
//        // Method need test
//        List<Notification> rs = notificationService.getAllNoti();
//
//        Assertions.assertNotNull(rs);
//        assertEquals(0, rs.size());  // Spring boot load
//    }
//
//    @Test
//    void testGetById_Success() throws NotFoundException {
//        when(notificationMapper.getById(1)).thenReturn(success);
//
//        Notification rs = notificationService.getById(1);
//
//        Assertions.assertNotNull(rs);
//        assertEquals(1, rs.getId());
//
//    }
//
//    @Test
//    void testGetById_Fail() {
//        NotFoundException thrown = assertThrows(NotFoundException.class, () -> notificationService.getById(3));
//        assertEquals("not found", thrown.getMessage());
//
//
//    }
//
//    @Test
//    void insert() {
////        giả lập phương thức
//
//        // init instance
//        NotificationRequest notificationRequest = new NotificationRequest();
//        notificationRequest.setId(1L);
//        notificationRequest.setTitle("Test Notification");
////        notificationRequest.setImageUrl("https://example.com/image.jpg");
//        notificationRequest.setContent("This is a test notification.");
//        notificationRequest.setStatus(Status.PUSHED);
//
//        Long rs = notificationService.insert(notificationRequest);
//        assertEquals(1,rs);
//    }
//
//    @Test
//    void changeStatus() {
//    }
//
//    @Test
//    void delete() {
//    }
//}