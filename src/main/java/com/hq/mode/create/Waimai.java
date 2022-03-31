package com.hq.mode.create;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class Waimai extends AbstractStrategy implements Strategy
{
    @Resource
    private WaimaiService waimaiService;

    private static final Waimai instance = new Waimai();

    private Waimai(){

    }

    public static Waimai getInstance(){
        return instance;
    }

    @PostConstruct
    public void init(){
        register();
        System.out.println("init method");
        System.out.println(Waimai.getInstance());
        instance.waimaiService = this.waimaiService;
    }

    @Override
    public void issue(BaseRequest baseRequest)
    {
        WaimaiRequest request = new WaimaiRequest();
        BeanUtils.copyProperties(baseRequest, request);
        request.setSendName(baseRequest.getRemark());

        waimaiService.issueWaimai(request);
    }
}
