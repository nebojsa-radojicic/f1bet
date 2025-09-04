package com.sporty.f1bet.scheduler;

import com.sporty.f1bet.service.F1betService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class F1betScheduler {

    private final F1betService f1betService;

    @Scheduled(fixedDelay = 60000)
    public void processEventOutcomes() {
        try {
            log.info("processEventOutcomes start");
            f1betService.processEventOutcomes();
            log.info("processEventOutcomes finish");
        } catch (Exception e) {
            log.error("processEventOutcomes error");
            log.error(e.getMessage(), e);
        }
    }

}