package com.sporty.f1bet.controller;

import com.sporty.f1bet.dto.Bet;
import com.sporty.f1bet.dto.ProviderEvents;
import com.sporty.f1bet.dto.Outcome;
import com.sporty.f1bet.service.F1betService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class F1betController {

    private final F1betService f1betService;

    @GetMapping("/events")
    public ResponseEntity<Collection<ProviderEvents>> getEvents(
        @RequestParam(name = "type", required = false) String type,
        @RequestParam(name = "year", required = false) Integer year,
        @RequestParam(name = "country", required = false) String country
    ) {
        return ResponseEntity.ok(f1betService.getEvents(type, year, country));
    }

    @PostMapping("/bet")
    public ResponseEntity<Integer> placeBet(@RequestBody Bet bet) {
        return ResponseEntity.ok(f1betService.placeBet(bet));
    }

    @PostMapping("/outcome")
    public ResponseEntity<Integer> eventOutcome(@RequestBody Outcome outcome) {
        return ResponseEntity.ok(f1betService.eventOutcome(outcome));
    }

}