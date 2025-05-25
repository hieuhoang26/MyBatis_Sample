package vn.hhh.noti.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import vn.hhh.noti.service.SqsService;

@Service
@RequiredArgsConstructor
public class SqsServiceImp implements SqsService {

    private final SqsClient sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;


    @Override
    public void sendMessage(String messageBody) {
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();
        sqsClient.sendMessage(sendMsgRequest);
    }
}
