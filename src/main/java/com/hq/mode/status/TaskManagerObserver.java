package com.hq.mode.status;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class TaskManagerObserver implements Observer
{
    @Resource
    private TaskManager taskManager;

    @Override
    public void response(Long taskId) {
        taskManager.release(taskId);
    }
}
