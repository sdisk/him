package com.hq;

import com.hq.mode.build.RiskControlDecorator;
import com.hq.mode.build.TaskActivity;
import com.hq.mode.create.BaseRequest;
import com.hq.mode.create.StrategyContext;
import com.hq.mode.status.ActionType;
import com.hq.mode.status.Task;
import com.hq.mode.status.TaskInit2;
import com.hq.mode.status.TaskOnGoing2;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by huang on 31/3/2022.
 */
public class ModeTest extends HimApplicationTests
{

    @Resource
    private TaskInit2 taskInit2;

    @Resource
    private TaskOnGoing2 taskOnGoing2;

    @Test
    public void test01(){
        BaseRequest request = new BaseRequest();
        request.setId(11);
        request.setMoney(400);
        request.setName("sdisk");
        request.setRemark("zhangsan");

        StrategyContext.getStrategy("Waimai").issue(request);
        StrategyContext.getStrategy("Hotel").issue(request);

    }

    @Test
    public void test02(){
        Task task = new Task();
        task.setTaskId(100L);

        taskInit2.update(task, ActionType.START);

        taskOnGoing2.update(task, ActionType.ACHIEVE);
    }

    @Test
    public void test03(){
        Task task = new Task();
        task.setTaskId(100L);

        taskInit2.update(task, ActionType.START);

        taskOnGoing2.update(task, ActionType.ACHIEVE);

        TaskActivity taskActivity = new TaskActivity.Builder()
                .setTask(task)
                .setId(1L)
                .setName("chengdu")
                .setType("sale")
                .setMaterial("材料学")
                .build();
        taskActivity.participate(999L);
        System.out.println("---lDecorator ----");
       new RiskControlDecorator(taskActivity).participate(999L);
    }
}
