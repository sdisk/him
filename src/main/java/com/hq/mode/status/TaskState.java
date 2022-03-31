package com.hq.mode.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态
 * Created by huang on 30/3/2022.
 */
@AllArgsConstructor
@Getter
public enum TaskState
{
    INIT("初始化"),
    ONGOING( "进行中"),
    PAUSED("暂停中"),
    FINISHED("已完成"),
    EXPIRED("已过期");
    private final String message;

}
