package com.sporty.f1bet.dto;

import java.util.Collection;

public record Event (
    Integer id,
    String location,
    String start,
    String finish,
    String type,
    String country,
    String circuit,
    Collection<Driver> drivers
) {}