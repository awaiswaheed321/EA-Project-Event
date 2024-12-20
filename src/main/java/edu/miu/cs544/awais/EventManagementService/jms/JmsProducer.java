package edu.miu.cs544.awais.EventManagementService.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    @Value("${queue.name}")
    private String queueName;
    private final JmsTemplate jmsTemplate;

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(Message message) {
        jmsTemplate.convertAndSend(queueName, message.toMap());
        System.out.println("Sent message: " + message);
    }
}
