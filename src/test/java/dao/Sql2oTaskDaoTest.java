package dao;

import models.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Guest on 1/16/18.
 */
public class Sql2oTaskDaoTest {

    private Sql2oTaskDao taskDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        taskDao = new Sql2oTaskDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDOwn() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Task task = new Task("mow the lawn", 1);
        int originalTaskId = task.getId();
        taskDao.add(task);
        assertNotEquals(originalTaskId, task.getId());
    }

    @Test
    public void existingTasksCanBeFoundById() throws Exception {
        Task task = new Task("mow the lawn", 1);
        taskDao.add(task);
        Task foundTask = taskDao.findById(task.getId());
        assertEquals(task, foundTask);
    }

    @Test
    public void addedTasksAreReturnedFromGetAll() throws Exception {
        Task task = new Task("mow the lawn", 1);
        taskDao.add(task);
        assertEquals(1, taskDao.getAll().size());
    }

    @Test
    public void noTaskReturnsEmptyList() throws Exception {
        assertEquals(0, taskDao.getAll().size());
    }

    @Test
    public void updateASingleTasksContent() throws Exception {
        String initialDescription = "mow the lawn";
        Task task = new Task(initialDescription, 1);
        taskDao.add(task);

        taskDao.update(task.getId(), "brush the cat", 1);
        Task updateTask = taskDao.findById(task.getId());
        assertNotEquals(initialDescription, updateTask.getDescription());
    }

    @Test public void deleteByIdDeletesCorrectTask() throws Exception {
        Task task = new Task("mow the lawn", 1);
        taskDao.add(task);
        taskDao.deleteById(task.getId());
        assertEquals(0, taskDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Task task = new Task("mow the lawn", 1);
        Task otherTask = new Task("brush the cat", 1);
        taskDao.add(task);
        taskDao.add(otherTask);
        int daoSize = taskDao.getAll().size();
        taskDao.clearAllTasks();
        assertTrue(daoSize > 0 && daoSize > taskDao.getAll().size());
    }



}
