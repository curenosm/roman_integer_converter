package com.github.curenosm;

/**
 * Interface for converting from one type to another.
 *
 * @param <F> Type of origin.
 * @param <T> Type of destination.
 */
interface Converter<F, T> {
  T convert(F value);
}