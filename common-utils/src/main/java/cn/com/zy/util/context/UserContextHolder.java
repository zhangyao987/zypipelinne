package cn.com.zy.util.context;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class UserContextHolder {

    public static String USER_ID_KEY = "id";
    public static String JWT_KEY = "jwt";

    private static ThreadLocal<Map<String,String>> context = new ThreadLocal<Map<String,String>>(){
        @Override
        protected Map<String,String> initialValue(){
            return new HashMap<>();
        }

        @Override
        public Map<String,String> get(){
            return super.get();
        }

        @Override
        public void set(Map<String,String> context){
            super.set(context);
        }

        @Override
        public void remove(){
            super.remove();
        }
    };

    public static void setContext(Map<String, String> map) {
        context.set(map);
    }

    public static String getContext() {
        if (context.get() == null) {
            log.info("UserContextHolder中没有上下文信息");
            return null;
        }
        return context.get().get(USER_ID_KEY);
    }

    public static String getJwtToken(){
        if (context.get() == null){
            log.info("UserContextHolder中没有上下文信息");
            return null;
        }
        return context.get().get(JWT_KEY);
    }

}
