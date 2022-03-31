package com.hq.mode.status;

import org.springframework.stereotype.Component;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class TaskPaused implements State
{
    @Override
    public void update(Task task, ActionType actionType){
        if (actionType == ActionType.START) {
            task.setState(new TaskOnGoing());
        } else if (actionType == ActionType.EXPIRE) {
            task.setState(new TaskExpired());
        }
    }
}
