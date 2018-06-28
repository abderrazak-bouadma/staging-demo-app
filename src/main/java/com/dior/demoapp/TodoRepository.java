package com.dior.demoapp;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Abderrazak BOUADMA
 * on 25/06/2018.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
