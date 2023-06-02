package com.sample.raptor.controller;

import com.sample.raptor.response.BaseResponse;
import com.sample.raptor.resquest.MessageRequest;
import com.sample.raptor.service.RaptorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author irfan.nagoo
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/raptor")
public class RaptorController {

    private final RaptorService raptorService;

    @PostMapping("/send")
    public BaseResponse sendMessage(@Valid @RequestBody MessageRequest request) {
        return raptorService.sendMessage(request);
    }

    @GetMapping("/process")
    public BaseResponse processRequest() {
        return raptorService.processRequest();
    }


}
