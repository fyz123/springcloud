package com.spring.impl;

import com.spring.ImessageSender;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)
public class MessageSender implements ImessageSender
{
    @Resource(name = "output")
    private MessageChannel messageChannel;

    @Override
    public String send() {
        String msg = UUID.randomUUID().toString();
        messageChannel.send(MessageBuilder.withPayload(msg).build());
        return null;
    }
}
