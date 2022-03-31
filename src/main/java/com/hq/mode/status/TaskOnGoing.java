package com.hq.mode.status;

/**
 * Created by huang on 31/3/2022.
 */
public class TaskOnGoing implements State
{

    /**
     * 活动服务
     */
    private ActivityService activityService;
    /**
     * 任务管理器
     */
    private TaskManager taskManager;


    @Override
    public void update(Task task, ActionType actionType){
        if (actionType == ActionType.ACHIEVE) {
            task.setState(new TaskFinished());
            // 任务完成后进对外部服务进行通知
            activityService.notifyFinished(task.getTaskId());
            taskManager.release(task.getTaskId());
        } else if (actionType == ActionType.STOP) {
            task.setState(new TaskPaused());
        } else if (actionType == ActionType.EXPIRE) {
            task.setState(new TaskExpired());
        }
    }
}
