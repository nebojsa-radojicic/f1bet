package com.sporty.f1bet.provider.openf1;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Collection;

public interface OpenF1Client {

    @GetExchange("/sessions")
    Collection<OpenF1Session> getSessions(
        @RequestParam(name = "session_type", required = false) String type,
        @RequestParam(name = "year", required = false) Integer year,
        @RequestParam(name = "country_name", required = false) String country
    );

}