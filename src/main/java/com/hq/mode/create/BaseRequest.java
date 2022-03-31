package com.hq.mode.create;

import lombok.Data;

/**
 * Created by huang on 31/3/2022.
 */
@Data
public class BaseRequest
{
    private int id;
    private String name;
    private int type;
    private int money;
    private String remark;
}
