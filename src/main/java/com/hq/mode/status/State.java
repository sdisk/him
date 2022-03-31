package com.hq.mode.status;

/**
 * Created by huang on 31/3/2022.
 */
public interface State
{
    default void update(Task task, ActionType actionType){
        // do nothing
    }
}
