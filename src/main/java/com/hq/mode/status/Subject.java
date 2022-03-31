package com.hq.mode.status;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 31/3/2022.
 */
public abstract class Subject
{
    protected List<Observer> observerList = new ArrayList<>();

    public void add(Observer observer){
        observerList.add(observer);
    }

    public void remove(Observer observer){
        observerList.remove(observer);
    }

    public void notifyAll(Long taskId){
        observerList.forEach((x)->{
            x.response(taskId);
        });
    }
}
