package com.sporty.f1bet.dto;

import java.util.Collection;

public record ProviderEvents (
    String provider,
    Collection<Event> events
) {}