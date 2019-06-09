package ee.ttu.priit.task.controller;

import ee.ttu.priit.task.entity.Task;
import ee.ttu.priit.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TaskController {

    @Resource
    private TaskService service;

    @PostMapping("/task")
    public void save(@RequestBody Task task) {
        service.save(task);
    }

    @GetMapping("/task")
    public List<Task> getAllTasks() {
        return service.findAll();
    }

    @PostMapping("/edit")
    public void edit(@RequestBody Task task) {
        service.edit(task);
    }

    @DeleteMapping("/task/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("task/complete/{id}")
    public void completeTask(@PathVariable Long id) {
        service.completeTask(id);
    }
}
