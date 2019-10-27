package com.fsd.mod.technology.service;

@FunctionalInterface
public interface Converter<S, T> {
    T convert(S source);
}
