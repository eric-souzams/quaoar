<div align="center" style="padding-top: 30px;">
    <img src="public/logo.png" width="55%" />
</div>
<span style="font-size: 12px; font-weight: bold; float: right;">Batch mailing with Java & Amazon AWS.</span>
<br />

## Requisites

- [Java 17](https://www.oracle.com/br/java/technologies/javase-jdk17-downloads.html)
- [Maven 3.6+](https://maven.apache.org/download.cgi)
- [AWS SES (Simple Email Service)](https://aws.amazon.com/pt/ses/)
- [AWS SQS (Simple Queue Service)](https://aws.amazon.com/pt/sqs/)
- [PostgreSQL 16](https://www.postgresql.org/download/)
- [RabbitMQ](https://www.rabbitmq.com/)


## Features
- [ ] On future...

## Pending
- [ ] Support AWS SQS messaging active option
- [ ] Support AWS SES to send e-mails
- [ ] Support integration ID

## Setup

### Profiles
```text
local | test | dev | qas | prod
```

### Queue Payload

```json
change-user-info.q

{
    "name": "Éric Magalhães",
    "email": "eric@eric.dev",
    "integrationId": "H7f%7$#fe1",
    "blocked": "false"
}
```

```json
delete-user.q

{
    "email": "eric@eric.dev",
}
```

```json
subscribeToTopicOutbound-to-topicEntity.q

{
    "email": "eric@eric.dev",
    "integrationId": "H7f%7$#fe1",
    "topics": [
      "PARTY",
      "UBER"
    ]
}
```

```json
unsubscribe-from-topicEntities.q

{
    "email": "eric@eric.dev",
    "integrationId": "H7f%7$#fe1",
    "topics": [
      "PARTY",
      "UBER"
    ]
}
```

### Endpoints

#### Mail
```json
POST -> /v1/mail/send -> Send mail

{
    "replyTo": "test@google.com",
    "subject": "Your password has been reset!",
    "content": "Click here to access now.",
    "recipientsTo": [
      "test@google.com"
    ],
    "recipientsCc": [
      "dev@google.com"
    ],
    "recipientsBcc": [
      "bug@google.com"
    ]
}
```

#### Template
```json
POST -> /v1/templates -> Create template

{
    "name": "RECOVER_PASSWORD",
    "title": "Quaoar - Recovery Password",
    "content": "Click here do change your password."
}
```

```json
PUT -> /v1/templates/{templateID} -> Update template

{
    "name": "RECOVER_PASSWORD",
    "title": "Quaoar - Recovery Password",
    "content": "Click here do change your password.",
    "active": true
}
```

```json
GET -> /v1/templates/{templateID} -> Find template by id

{
  "id": "11b9c66b-3ae3-461d-b2f6-8f42bdbad8b8",
  "name": "RECOVER_PASSWORD",
  "title": "Quaoar - Recovery Password",
  "content": "Click here do change your password.",
  "active": true,
  "createdAt": "2025-04-07T11:10:14.484742",
  "updatedAt": "2025-04-07T11:10:14.484742"
}
```

```json
GET -> /v1/templates -> Find all templates

{
  "content": [
    {
      "id": "11b9c66b-3ae3-461d-b2f6-8f42bdbad8b8",
      "name": "RECOVER_PASSWORD",
      "title": "Quaoar - Recovery Password",
      "content": "Click here do change your password.",
      "active": true,
      "createdAt": "2025-04-07T11:10:14.484742",
      "updatedAt": "2025-04-07T11:10:14.484742"
    }
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalElements": 3,
  "totalPages": 1,
  "isLast": true,
  "last": true
}
```

```json
DELETE -> /v1/templates/{templateID} -> Delete template by id
```

### Environment Variables
```text
| Variable       | Description               |
|----------------|---------------------------|
| DB_HOST        | Database URL host         |
| DB_PORT        | Database port             |
| DB_DATABASE    | Database name             |
| DB_USERNAME    | Database username         |
| DB_PASSWORD    | Database password         |
| AWS_REGION     | AWS region                |
| AWS_ACCESS_KEY | AWS access key to connect |
| AWS_SECRET_KEY | AWS secret key to connect |
| AWS_SES_FROM   | E-mail to use as origin   |
```

### Running
```bash
$ git clone https://github.com/eric-souzams/quaoar.git
$ cd quaoar

$ mvn clean install -DskipTests -P{profile}
$ mvn spring-boot:run
```