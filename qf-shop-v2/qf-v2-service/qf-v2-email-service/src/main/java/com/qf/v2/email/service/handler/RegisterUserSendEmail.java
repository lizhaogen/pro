package com.qf.v2.email.service.handler;

import com.lzg.email.api.IEmailService;
import com.qf.v2.common.constant.RabbitConstant;
import com.qf.v2.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserSendEmail {

    @Autowired
    private IEmailService service;

    @RabbitListener(queues = RabbitConstant.EMAIL_USER_REGIST_QUEUE)
    public void process(User user){}


}
