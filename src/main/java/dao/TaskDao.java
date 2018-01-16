package dao;

import models.Task;

import java.util.List;

/**
 * Created by Guest on 1/16/18.
 */
public interface TaskDao {

    void add (Task task);

    Task findById(int id);

    List<Task> getAll();

    void update(int id, String content, int categoryId);

    void deleteById(int id);

    void clearAllTasks();

}
