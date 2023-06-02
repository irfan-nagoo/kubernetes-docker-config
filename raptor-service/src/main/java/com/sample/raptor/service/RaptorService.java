package com.sample.raptor.service;

import com.sample.raptor.response.BaseResponse;
import com.sample.raptor.resquest.MessageRequest;

/**
 * @author irfan.nagoo
 */
public interface RaptorService {

    BaseResponse sendMessage(MessageRequest messageRequest);

    BaseResponse processRequest();

}
