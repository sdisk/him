package com.hq.mode.status;

import org.springframework.stereotype.Component;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class ActivityService
{
    public void notifyFinished(Long taskId){
        //notifyFinished事件
        System.out.println("ActivityService  notifyFinished ");
    }
}
