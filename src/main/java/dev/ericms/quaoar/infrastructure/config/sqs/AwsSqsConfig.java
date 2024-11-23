package dev.ericms.quaoar.infrastructure.config.sqs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import dev.ericms.quaoar.infrastructure.config.conditional.MessagingAwsCondition;
import dev.ericms.quaoar.infrastructure.exception.JmsErrorHandler;
import jakarta.jms.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@EnableJms
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

    @Value("${broker.consumer.prefetch-count}")
    private Integer prefetchCount;

    @Bean
    public StaticCredentialsProvider credentialsProvider() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));
    }

    @Bean
    public AmazonSQS amazonSQSClient() {
        logger.info("Connecting to AWS SQS...");
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region)
                .build();
    }

    @Bean
    public SQSConnectionFactory sqsConnectionFactory() {
        SqsClient client = SqsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(credentialsProvider())
                .build();

        ProviderConfiguration providerConfiguration = new ProviderConfiguration().withNumberOfMessagesToPrefetch(prefetchCount);

        return new SQSConnectionFactory(providerConfiguration, client);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(SQSConnectionFactory sqsConnectionFactory,
                                                                          MappingJackson2MessageConverter mappingJackson2MessageConverter) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(sqsConnectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setMaxMessagesPerTask(50);
        factory.setMessageConverter(mappingJackson2MessageConverter);
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        factory.setErrorHandler(new JmsErrorHandler());

        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(SQSConnectionFactory connectionFactory, MappingJackson2MessageConverter mappingJackson2MessageConverter) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setMessageConverter(mappingJackson2MessageConverter);

        return jmsTemplate;
    }

}
