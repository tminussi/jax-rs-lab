package com.bytecraft.config;

import com.bytecraft.web.controller.ProductController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ProductController.class);
    }
}
