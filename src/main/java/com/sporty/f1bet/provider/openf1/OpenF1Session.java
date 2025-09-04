package com.sporty.f1bet.provider.openf1;

public record OpenF1Session (
    Integer meeting_key,
    Integer session_key,
    String location,
    String date_start,
    String date_end,
    String session_type,
    String session_name,
    Integer country_key,
    String country_code,
    String country_name,
    Integer circuit_key,
    String circuit_short_name,
    String gmt_offset,
    Integer year
) {}