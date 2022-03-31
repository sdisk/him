package com.hq.mode.create;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huang on 31/3/2022.
 */
public class StrategyContext
{
    private static final Map<String, Strategy> registerMap = new HashMap<>();

    /**
     * 注册策略
     */
    public static void registerStrategy(String rewardType, Strategy strategy){
        registerMap.putIfAbsent(rewardType, strategy);
    }
    /**
     * 获取策略
     */
    public static Strategy getStrategy(String rewardType){
        return registerMap.get(rewardType);
    }
}
