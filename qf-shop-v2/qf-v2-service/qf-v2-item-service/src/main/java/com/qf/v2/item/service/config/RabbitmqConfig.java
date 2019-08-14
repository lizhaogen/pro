package com.qf.v2.item.service.config;

import com.qf.v2.common.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue getQueue(){
        return new Queue(RabbitConstant.PRODUCT_ITEM_SAVE_UPDATE_QUEUE);
    }
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitConstant.PRODUCT_WEB_EXCHANGE);
    }

    @Bean
    public Binding getBinding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(RabbitConstant.ITEM_SERVICE_ADD_ROUTING_KEY);
    }
}
