package com.hq.mode.status;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class TaskOnGoing2 extends Subject implements State
{
    @Resource
    private ActivityObserver activityObserver;
    @Resource
    private TaskManagerObserver taskManagerObserver;

    @PostConstruct
    public void init(){
//        this.add(new ActivityObserver());
//        this.add(new TaskManagerObserver());
        this.add(activityObserver);
        this.add(taskManagerObserver);
    }

    @Override
    public void update(Task task, ActionType actionType){
        if (actionType == ActionType.ACHIEVE) {
            task.setState(new TaskFinished());
            // 任务完成后进对外部服务进行通知
            notifyAll(task.getTaskId());
        } else if (actionType == ActionType.STOP) {
            task.setState(new TaskPaused());
        } else if (actionType == ActionType.EXPIRE) {
            task.setState(new TaskExpired());
        }
    }
}
