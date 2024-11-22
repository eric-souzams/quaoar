package dev.ericms.quaoar.infrastructure.config.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingAwsCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(MessagingAwsCondition.class)
public class AwsSqsConfig {

    private static final Logger logger = LoggerFactory.getLogger(AwsSqsConfig.class);

    @Value("${messaging.aws.access}")
    private String accessKey;

    @Value("${messaging.aws.secret}")
    private String secretKey;

    @Value("${messaging.aws.region}")
    private String region;

    @Bean
    @Conditional(MessagingAwsCondition.class)
    public AmazonSQS amazonSQSClient() {
        logger.info("Connecting to AWS SQS...");
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region)
                .build();
    }

}
