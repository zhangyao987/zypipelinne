package cn.com.zy.util.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 */
public enum BooleanEnum {

    YES("是"),NO("否");

    @Setter
    @Getter
    private String value;

    BooleanEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
