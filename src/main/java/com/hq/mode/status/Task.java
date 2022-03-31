package com.hq.mode.status;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by huang on 30/3/2022.
 */
@Getter
@Setter
public class Task
{

    private Long taskId;
    // 初始化为初始态
    private State state = new TaskInit();
    // 更新状态
    public void updateState(ActionType actionType) {
        state.update(this, actionType);
    }

//    private Long taskId;
//
//    private TaskState state = TaskState.INIT;
//    /**
//     * 活动服务
//     */
//    private ActivityService activityService;
//    /**
//     * 任务管理器
//     */
//    private TaskManager taskManager;
//
//    /**
//     * 接收不同的行为，然后更新当前任务的状态；
//     * 当任务过期时，通知任务所属的活动和任务管理器。
//     *
//     * 违背了开闭原则(对扩展开放，对修改关闭)
//     *
//     * 状态模式：对有状态的对象，把复杂的“判断逻辑”提取到不同的状态对象中，
//     * 允许状态对象在其内部状态发生改变时改变其行为
//     *
//     * 环境类（Context）角色：也称为上下文，它定义了客户端需要的接口，内部维护一个当前状态，并负责具体状态的切换。
//     * 抽象状态（State）角色：定义一个接口，用以封装环境对象中的特定状态所对应的行为，可以有一个或多个行为
//     * 具体状态（Concrete State）角色：实现抽象状态所对应的行为，并且在需要的情况下进行状态切换
//     *
//     * @param actionType
//     */
//    public void updateState(ActionType actionType){
//        if (state == TaskState.INIT) {
//            if (actionType == ActionType.START) {
//                state = TaskState.ONGOING;
//            }
//        } else if (state == TaskState.ONGOING) {
//            if (actionType == ActionType.ACHIEVE) {
//                state = TaskState.FINISHED;
//                // 任务完成后进对外部服务进行通知
//                activityService.notifyFinished(taskId);
//                taskManager.release(taskId);
//            } else if (actionType == ActionType.STOP) {
//                state = TaskState.PAUSED;
//            } else if (actionType == ActionType.EXPIRE) {
//                state = TaskState.EXPIRED;
//            }
//        } else if (state == TaskState.PAUSED) {
//            if (actionType == ActionType.START) {
//                state = TaskState.ONGOING;
//            } else if (actionType == ActionType.EXPIRE) {
//                state = TaskState.EXPIRED;
//            }
//        }
//    }
}
