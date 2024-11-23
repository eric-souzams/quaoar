package dev.ericms.quaoar.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class JmsErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(JmsErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        logger.error("Error to process message", t);
    }
}
