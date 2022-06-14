package com.fzolv.lld.vendingmachine.repository;

public interface MapRepository<K, V> {

    V get(K key);

    K add(V value);

    void update(K key, V value);

    void delete(K key);
}
