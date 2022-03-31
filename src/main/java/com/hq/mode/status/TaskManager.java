package com.hq.mode.status;

import org.springframework.stereotype.Component;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class TaskManager
{
    public void release(Long taskId){
        //release task
        System.out.println("TaskManager release task");
    }
}
