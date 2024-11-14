//package dev.ericms.quaoar.infrastructure.config.sqs;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
//import dev.ericms.quaoar.infrastructure.config.conditional.MessagingAwsCondition;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AwsSqsConfig {
//
//    @Value("${aws.access.key}")
//    private String accessKey;
//
//    @Value("${aws.secret.key}")
//    private String secretKey;
//
//    @Value("${aws.region}")
//    private String region;
//
//    @Bean
//    @Conditional(MessagingAwsCondition.class)
//    public AmazonSQS amazonSQSClient() {
//        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
//
//        return AmazonSQSClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .withRegion(region)
//                .build();
//    }
//
//}
