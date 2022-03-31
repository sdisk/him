package com.hq.mode.status;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class ActivityObserver implements Observer
{
    /**
     * 活动服务
     */
    @Resource
    private ActivityService activityService;

    @Override
    public void response(Long taskId)
    {
        activityService.notifyFinished(taskId);
    }
}
