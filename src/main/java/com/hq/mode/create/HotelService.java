package com.hq.mode.create;

import org.springframework.stereotype.Component;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class HotelService
{
    public void issueHotel(HotelRequest request){
        System.out.println("酒店姓名:" + request.getHotelName() + "，订单金额:" + request.getMoney());
    }
}
