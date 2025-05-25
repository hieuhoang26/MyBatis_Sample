package vn.hhh.noti.service;

public interface SqsService {
    void sendMessage(String messageBody);
}
