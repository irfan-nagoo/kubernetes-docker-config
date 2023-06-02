package com.sample.raptor.service.impl;

import com.sample.raptor.async.AsyncSMSSender;
import com.sample.raptor.domain.Message;
import com.sample.raptor.response.BaseResponse;
import com.sample.raptor.resquest.MessageRequest;
import com.sample.raptor.service.RaptorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author irfan.nagoo
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class RaptorServiceImpl implements RaptorService {

    private final AsyncSMSSender asyncSMSSender;

    @Override
    public BaseResponse sendMessage(MessageRequest messageRequest) {
        log.info("Preparing to send Messages");
        CompletableFuture[] futures = new CompletableFuture[messageRequest.getMessages().size()];
        int i = 0;
        for (Message msg : messageRequest.getMessages()) {
            futures[i++] = asyncSMSSender.sendSMS(msg.getPhoneNumber(), msg.getText());
        }
        CompletableFuture.allOf(futures).join();
        log.info("All messages sent successfully");
        return new BaseResponse(HttpStatus.OK.name(), "Message Sent Successfully");
    }

    @Override
    public BaseResponse processRequest() {
        log.info("Processing Raptor request");
        return new BaseResponse(HttpStatus.OK.name(), "Request Processed Successfully");
    }

}
