package com.example.mail.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "tacocloud.api")
public class ApiProperties {
    private String orderUrl;
    private String ingredientUrl;
}
