package com.sample.raptor.resquest;

import com.sample.raptor.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author irfan.nagoo
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageRequest {

    @Valid
    @NotNull
    private List<Message> messages;

}
