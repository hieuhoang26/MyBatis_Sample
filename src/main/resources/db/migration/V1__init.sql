CREATE TABLE notifications (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               title VARCHAR(255) NOT NULL,
                               image_url TEXT,
                               content TEXT NOT NULL,
                               status ENUM('PUSHED', 'NOT_PUSHED') DEFAULT 'NOT_PUSHED',
                               pushed_at DATETIME,
                               created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO notifications (title, image_url, content, status, pushed_at)
VALUES
    ('Thông báo bảo trì hệ thống',
     'https://example.com/images/maintenance.png',
     'Hệ thống sẽ bảo trì từ 22:00 ngày 25/05 đến 02:00 ngày 26/05.',
     'PUSHED',
     '2025-05-21 22:00:00'),

    ('Khuyến mãi đặc biệt tháng 5',
     'https://example.com/images/promo-may.png',
     'Nhận ưu đãi 20% khi đăng bài VIP trong tháng 5!',
     'NOT_PUSHED',
     NULL),

    ('Ra mắt tính năng mới',
     'https://example.com/images/new-feature.png',
     'Chúng tôi vừa cập nhật tính năng tìm kiếm nâng cao theo bản đồ.',
     'PUSHED',
     '2025-05-15 10:30:00');
