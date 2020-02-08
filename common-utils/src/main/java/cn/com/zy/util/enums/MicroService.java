package cn.com.zy.util.enums;

import cn.com.zy.util.constatns.MicroServiceConstatns;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum MicroService {

    SYSTEM_ADMIN(MicroServiceConstatns.SYSTEM_ADMIN, "系统管理"),


    DATA_PIPELINE_CENTRALDB(MicroServiceConstatns.DATA_PIPELINE_CENTRALDB, "中心资源库服务"),
    DATA_PIPELINE_CORE(MicroServiceConstatns.DATA_PIPELINE_CORE, "资源管理中心核心服务"),
    DATA_PIPELINE_CSB(MicroServiceConstatns.DATA_PIPELINE_CSB, "CSB服务总线服务"),
    DATA_PIPELINE_EXCHANGE(MicroServiceConstatns.DATA_PIPELINE_EXCHANGE, "交换平台服务"),
    DATA_PIPELINE_OPENDATA(MicroServiceConstatns.DATA_PIPELINE_OPENDATA, "开放平台"),
    DATA_PIPELINE_ML(MicroServiceConstatns.DATA_PIPELINE_ML, "目录系统"),
    DATA_PIPELINE_GOV_DATA(MicroServiceConstatns.DATA_PIPELINE_GOV_DATA, "中心资源库服务");

    @Setter
    @Getter
    public String id;

    @Setter
    @Getter
    public String name;

    MicroService(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public static List<Map<String, String>> getAllDictionaries() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (MicroService item : MicroService.values()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("key", item.getId());
            map.put("value", item.getName());
            list.add(map);
        }
        return list;
    }
}
