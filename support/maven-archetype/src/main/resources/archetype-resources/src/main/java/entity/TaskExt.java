package org.springcat.sample.entity;

import org.springside.modules.mapper.BeanMapper;

/**
 * Created by springcat on 16/2/15.
 */
public class TaskExt extends Task{

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Task toTask(TaskExt taskExt) {
        Task task = new Task();
        BeanMapper.copy(taskExt,task);
        return task;
    }
}
