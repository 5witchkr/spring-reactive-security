package com.example.reactivesecurity;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String secretKey = "abasdfasdfasdgasdfffsd";

    private long validityInMs = 36000000;
}
