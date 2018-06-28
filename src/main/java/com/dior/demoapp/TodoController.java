package com.dior.demoapp;

import org.fluentd.logger.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Abderrazak BOUADMA
 * on 25/06/2018.
 */
@RestController
@RequestMapping("/todos")
public class TodoController {

    private static final FluentLogger loggerES = FluentLogger.getLogger("es.logs");
    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> all() {
        loggerES.log("controller", "get", "called get endpoint");
        return ResponseEntity.ok(todoRepository.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Todo> add(@RequestParam String title, @RequestParam String tags) throws URISyntaxException {
        Todo t = new Todo();
        t.setTitle(title);
        t.setTags(tags);
        t.setDone(false);
        Todo saved = todoRepository.save(t);
        URI uri = new URI("/" + saved.getId());
        loggerES.log("controller", "post", saved);
        return ResponseEntity.created(uri).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (todoRepository.getOne(id) != null) {
            todoRepository.delete(id);
            loggerES.log("controller", "delete", "deleted todo with id=" + id);
            return ResponseEntity.ok().build();
        } else {
            loggerES.log("controller", "delete", "todo with id=" + id + " not found");
            return ResponseEntity.notFound().build();
        }
    }

}
