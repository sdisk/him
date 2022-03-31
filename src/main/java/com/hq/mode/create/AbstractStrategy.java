package com.hq.mode.create;

/**
 * Created by huang on 31/3/2022.
 */
public abstract class AbstractStrategy implements Strategy
{
    public void register(){
        StrategyContext.registerStrategy(getClass().getSimpleName(), this);
    }
}
