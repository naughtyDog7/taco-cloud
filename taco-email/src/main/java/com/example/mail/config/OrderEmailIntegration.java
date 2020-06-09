package com.example.mail.config;

import com.example.mail.config.flow_objects.EmailOrderTransformer;
import com.example.mail.model.Order;
import com.example.mail.props.ApiProperties;
import com.example.mail.props.EmailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.mail.dsl.ImapMailInboundChannelAdapterSpec;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.support.PropertiesBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class OrderEmailIntegration {
    @Bean
    public IntegrationFlow tacoOrderEmailFlow(
            EmailOrderTransformer emailOrderTransformer,
            EmailProperties emailProps,
            RestTemplate rest,
            ApiProperties apiProps) {
        return IntegrationFlows.from(emailInboundAdapter(emailProps),
                cf -> cf.poller(Pollers.fixedDelay(emailProps.getPollRate())))
                .transform(emailOrderTransformer)
                .handle(postOrderHandler(rest, apiProps))
                .get();
    }

    public ImapMailInboundChannelAdapterSpec emailInboundAdapter(EmailProperties emailProps) {
        return Mail.imapInboundAdapter(emailProps.getImapUrl())
                .javaMailProperties(javaMailProps())
                .shouldMarkMessagesAsRead(true)
                .shouldDeleteMessages(false)
                .simpleContent(true);
    }

    private Consumer<PropertiesBuilder> javaMailProps() {
        return i -> i.put("mail.debug", "false")
                .put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                .put("mail.imap.socketFactory.fallback", "false")
                .put("mail.store.protocol", "imap");
    }

    public GenericHandler<Order> postOrderHandler(RestTemplate rest, ApiProperties apiProps) {
        return (order, messageHeaders) -> {
            String response = rest.postForObject(apiProps.getOrderUrl(), order, String.class);
            log.info("response " + response);
            return null;
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
