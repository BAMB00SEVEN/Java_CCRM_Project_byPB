package edu.ccrm.util;


@FunctionalInterface
public interface Searchable<T> {

   
    boolean matches(T criterion);

}
