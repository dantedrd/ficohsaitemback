package com.ficohsa.item.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.ficohsa.item",
        includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Service")},
        useDefaultFilters = false)
public class ServicesConfig {
}
