package com.hq.mode.create;

import org.springframework.stereotype.Component;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class WaimaiService
{
    public void issueWaimai(WaimaiRequest request){
        System.out.println("骑手姓名:" + request.getSendName() + "，订单金额:" + request.getMoney());
    }
}
