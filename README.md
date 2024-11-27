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
- [ ] Future...

## Pending
- [x] Support AWS SQS messaging active option
- [x] Support AWS SES to send e-mails

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
    "integrationId": "H7f%7$#fe1"
}
```

```json
subscribe-to-topicEntity.q

{
    "email": "eric@eric.dev",
    "integrationId": "H7f%7$#fe1",
    "topicEntities": {
      "PARTY",
      "UBER"
    }
}
```

```json
unsubscribe-from-topicEntities.q

{
    "email": "eric@eric.dev",
    "integrationId": "H7f%7$#fe1",
    "topicEntities": {
      "PARTY",
      "UBER"
    }
}
```

### Environment Variables
```text
| Variable       | Description               |
|----------------|---------------------------|
| DB_HOST        | Database URL host         |
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