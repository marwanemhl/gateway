package org.sid.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication

public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


    RouteLocator routeLocator(RouteLocatorBuilder builder){
            return builder.routes()
                    .route("r1",(r)->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                    .route("r2",(r)->r.path("/inventories/**").uri("lb://INVENTORY-SERVICE"))
                    .route("r3",(r)->r.path("/bills/**").uri("lb://BILLING-SERVICE"))
                    .route("r4",(r)->r.path("/fullBill/**").uri("lb://BILLING-SERVICE"))

                    .build();
    }

    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(
            ReactiveDiscoveryClient rdc,
            DiscoveryLocatorProperties properties){
        return new DiscoveryClientRouteDefinitionLocator(rdc,properties);
    }


}

