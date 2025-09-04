package com.sporty.f1bet.dto;

public record Outcome (
    String provider,
    Integer event,
    Integer driver
) {}