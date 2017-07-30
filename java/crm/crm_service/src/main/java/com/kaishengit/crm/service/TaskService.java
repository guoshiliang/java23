package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Task;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25 0025.
 */
public interface TaskService {


    void saveNewTask(Task task);

    List<Task> findUnDoneTaskByCustId(Integer id);

    List<Task> findTaskByAccountId(Integer accountId, boolean showAll);

    Task findById(Integer id);

    void updateTask(Task task);

    void delTask(Task task);

}
