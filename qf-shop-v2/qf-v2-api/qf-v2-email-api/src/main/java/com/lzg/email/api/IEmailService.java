package com.lzg.email.api;

import com.qf.v2.entity.User;

public interface IEmailService {
    void sendMail(String to, String subject, User user);

}
