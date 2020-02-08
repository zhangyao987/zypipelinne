package cn.com.zy.util.enums;

/**
 * @Description:
 */
public enum RedisKeyEnum {
    USER_PERMISSIONS_KEY_PREFIX("USER_ROLE_"),
    PLATFORM_PERMISSIONS_KEY("PLATFORM_PERMISIONS_KEY"),
    FORCE_REFRESH_PLATFORM_PERMISSIONS_KEY("FORCE_REFRESH_PLATFORM_PERMISSIONS_KEY");

    private String id;
    RedisKeyEnum(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return this.id;
    }
}
