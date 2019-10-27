package com.fsd.mod.training.service;

@FunctionalInterface
public interface Converter<S, T> {
    T convert(S source);
}
