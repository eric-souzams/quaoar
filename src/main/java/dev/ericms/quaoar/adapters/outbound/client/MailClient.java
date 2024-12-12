package dev.ericms.quaoar.adapters.outbound.client;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailClient {

    @Autowired
    private AmazonSimpleEmailService simpleEmailService;

    public void send() {
        
    }

}
