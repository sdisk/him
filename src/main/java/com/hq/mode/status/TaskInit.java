package com.hq.mode.status;

/**
 * Created by huang on 31/3/2022.
 */
public class TaskInit implements State
{
    @Override
    public void update(Task task, ActionType actionType){
       if (actionType == ActionType.START){
           task.setState(new TaskOnGoing());
       }
    }
}
