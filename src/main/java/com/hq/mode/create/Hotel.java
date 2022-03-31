package com.hq.mode.create;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by huang on 31/3/2022.
 */
@Component
public class Hotel extends AbstractStrategy implements Strategy
{

    private static final Hotel instance = new Hotel();
    @Resource
    private HotelService hotelService;

    private Hotel() {

    }
    public static Hotel getInstance() {
        return instance;
    }

    @PostConstruct
    public void init(){
        register();
        System.out.println("init method");
        System.out.println(Hotel.getInstance());
        instance.hotelService = this.hotelService;
    }
    @Override
    public void issue(BaseRequest baseRequest)
    {
        HotelRequest request = new HotelRequest();
        BeanUtils.copyProperties(baseRequest, request);
        request.setHotelName(baseRequest.getRemark());
        hotelService.issueHotel(request);
    }
}
