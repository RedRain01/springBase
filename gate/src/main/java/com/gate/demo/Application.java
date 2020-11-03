package com.gate.demo;
import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// tag::code[]
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                .route(r -> r.path("/customer/**")
                        .filters(f -> f.filter(new RequestTimeFilter())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("http://httpbin.org:80/get")
                        .order(0)
                        .id("customer_filter_router")
                )
                .build();
        // @formatter:on
    }



//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //basic proxy
//                .route(r -> r.path("/baidu")
//                        .uri("http://baidu.com:80/")
//                ).build();
//    }

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        String httpUri = "http://httpbin.org:80";
//        return builder.routes()
//            .route(p -> p
//                .path("/get")
//                .filters(f -> f.addRequestHeader("Hello", "World"))
//                .uri(httpUri))
//            .route(p -> p
//                .host("*.hystrix.com")
//                .filters(f -> f
//                    .hystrix(config -> config
//                        .setName("mycmd")
//                        .setFallbackUri("forward:/fallback")))
//                .uri(httpUri))
//            .build();
//    }
    // end::route-locator[]

    // tag::fallback[]
//    @RequestMapping("/fallback")
//    public Mono<String> fallback() {
//        return Mono.just("fallback");
//    }

}

