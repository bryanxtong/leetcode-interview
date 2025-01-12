package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

//leetcode 3408
class TaskManager {
    //taskId, task
    private Map<Integer, Task> mapTasks = new HashMap<>();

    private TreeSet<Task> treeSet = new TreeSet<>(((o1, o2) -> {
        if (o1.newpriority == o2.newpriority) {
            return o2.taskId - o1.taskId;
        } else {
            return o2.newpriority - o1.newpriority;
        }
    }));

    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> l : tasks) {
            Task task = new Task(l.get(0), l.get(1), l.get(2));
            mapTasks.put(l.get(1), task);
            treeSet.add(task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        mapTasks.put(taskId, task);
        treeSet.add(task);
    }

    public void edit(int taskId, int newPriority) {
        Task task = mapTasks.get(taskId);
        if (task != null) {
            Task newTask = new Task(task.userId, taskId, newPriority);
            mapTasks.put(taskId, newTask);
            treeSet.remove(task);
            treeSet.add(newTask);
        }
    }

    public void rmv(int taskId) {
        Task task = mapTasks.get(taskId);
        if (task != null) {
            mapTasks.remove(taskId);
            treeSet.remove(task);
        }
    }

    public int execTop() {
        if (treeSet.size() > 0) {
            Task poll = treeSet.pollFirst();
            return poll.userId;
        }

        return -1;
    }

    class Task {
        private int userId;
        private int taskId;
        private int newpriority;

        public Task(int userId, int taskId) {
            this.userId = userId;
            this.taskId = taskId;
        }

        public Task(int userId, int taskId, int newpriority) {
            this.userId = userId;
            this.taskId = taskId;
            this.newpriority = newpriority;
        }
    }
}