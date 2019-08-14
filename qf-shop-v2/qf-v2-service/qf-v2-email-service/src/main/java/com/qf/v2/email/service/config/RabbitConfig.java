package com.qf.v2.email.service.config;

import com.qf.v2.common.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue getQueue(){
        return new Queue(RabbitConstant.EMAIL_USER_REGIST_QUEUE);
    }
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitConstant.EMAIL_REGIST_EXCHANGE);
    }

    @Bean
    public Binding getBinding(Queue getQueue,TopicExchange getTopicExchange){
        return BindingBuilder.bind(getQueue).to(getTopicExchange).with(RabbitConstant.EMAIL_REGIST_USER_ROUTING_KEY);
    }
}
