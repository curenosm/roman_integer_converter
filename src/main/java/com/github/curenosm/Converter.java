package com.github.curenosm;

interface Converter<F, T> {
  T convert(F value);
}