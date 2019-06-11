package ee.ttu.priit.task.service;

import ee.ttu.priit.task.entity.Task;
import ee.ttu.priit.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskService {

    @Resource
    private TaskRepository repo;

    public void save(Task task) {
        repo.save(task);
    }

    public List<Task> findAll() {
        return repo.findAll();
    }

    public void edit(Task task) {
        repo.save(task);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void completeTask(Long id) {
        Task task = repo.getOne(id);
        if (task.isCompleted()) {
            task.setCompleted(false);
        } else {
            task.setCompleted(true);
        }
        repo.save(task);
    }
}
