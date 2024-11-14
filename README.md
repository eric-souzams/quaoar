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
- [ ] A
- [ ] B
- [ ] C
- [ ] D
- [ ] E
- [ ] F

## Pending
- [ ] Support AWS SQS messaging active option

## Setup

### Profiles
```text
local | test | dev | qas | prod
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
```

### Running
```bash
$ git clone https://github.com/eric-souzams/quaoar.git
$ cd quaoar

$ mvn clean install -DskipTests -P{profile}
$ mvn spring-boot:run
```