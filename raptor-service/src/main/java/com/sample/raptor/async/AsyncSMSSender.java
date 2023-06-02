package com.sample.raptor.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author irfan.nagoo
 */

@Component
@Slf4j
public class AsyncSMSSender {

    @Async
    public CompletableFuture<String> sendSMS(String num, String msg) {
        log.info("Sending message [{}] to the number [{}]", msg, num);
        return CompletableFuture.completedFuture(String.format("Message [%s] successfully sent to %s",
                msg, num));
    }
}
