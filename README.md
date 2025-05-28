# Notification CRUD

## 🧱 Tech Stack

- **Backend**: Spring Boot, MyBatis, Security (Basic Auth / JWT)
- **DB Migration**: Flyway
- **Database**: MySQL (Docker)
- **Swagger**

---

## Security

![flow](/images/security_flow.png)

`@Secured` : chỉ định role (vai trò) có thể truy cập phương thức (prefix `ROLE_`)
`@RolesAllowed` : ~ `@Secured`, (no prefix)
`@PreAuthorize`: Đánh giá trước khi phương thức được gọi

```java
@PreAuthorize("hasRole('ADMIN') or hasAuthority('POST_CREATE')")
```

`@PostAuthorize`: Đánh giá sau khi phương thức thực thi xong (thường dùng với return object)
`@PreFilter`: lọc input collection (List, Set,...) trước khi vào method
`@PostFilter`:lọc output collection (List, Set,...) sau khi method thực thi

## Job

##### 1 Job dùng để gửi notification, sử dụng lambda. Khi người dùng call API push, sẽ trigger notification. (SQS + lambda)

- IAM User : OperatorUser - S3Full + SQS Full
- IAM ROLE : Lambda_NotificationProcessor_Role - S3Full + SQS Full + AWSLambdaBasicExecutionRole + CloudWatchLogsFullAccess
- S3 : noti-demo1
- SQS : noti-demo

- Lambda:
  - NotificationPushHandler

```python
import json

def lambda_handler(event, context):
    for record in event['Records']:
        message_body = json.loads(record['body'])

        notification_id = message_body.get('id')
        title = message_body.get('title')
        content = message_body.get('content')

        print(f"[Lambda] Pushing notification: ID={notification_id}, Title='{title}', Content='{content}'")
        return {
            'statusCode': 200,
            'body': json.dumps('Processed SQS messages successfully')
        }
}
```

##### 1 job chạy định kì để xóa các notification có đã push và thời gian push quá 3 ngày (loading)

- Deploy project ECS (image ECR + MySQL Container/ RDS MySQL )
- ..........
- Lambda call API or access DB

### Deloy

- Jib build image

```xml
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>3.4.0</version>
    <configuration>
        <to>
            <image><ACC_ID>.dkr.ecr.us-east-1.amazonaws.com/<repository-name></image>
            <!--                        reponame-->
        </to>
    </configuration>
</plugin>

```

```bash
aws configure
aws ecr create-repository --repository-name <name>
aws ecr get-login-password | docker login --username AWS --password-stdin <ACC_ID>.dkr.ecr.us-east-1.amazonaws.com
mvn compile jib:build "-Dimage=<ACC_ID>.dkr.ecr.us-east-1.amazonaws.com/<repository-name>:<tag>"
```
