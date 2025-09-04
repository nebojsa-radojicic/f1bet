package com.sporty.f1bet.dto;

public record Bet (
    String provider,
    Integer event,
    Integer driver,
    Integer odds,
    Integer account,
    Integer amount
) {}