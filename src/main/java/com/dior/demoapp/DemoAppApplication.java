package com.dior.demoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import javax.transaction.Transactional;

@SpringBootApplication
public class DemoAppApplication implements CommandLineRunner {

    private final TodoRepository r;

    @Autowired
    public DemoAppApplication(TodoRepository r) {
        this.r = r;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {

        Todo t = new Todo();
        t.setTitle("install logstash");
        t.setTags("infra, cloud, azure");
        t.setDone(false);
        r.save(t);

        t = new Todo();
        t.setTitle("install fluentd");
        t.setTags("infra, cloud, azure");
        t.setDone(false);
        r.save(t);

        t = new Todo();
        t.setTitle("install mysql");
        t.setTags("infra, cloud, azure");
        t.setDone(true);
        r.save(t);
    }
}
