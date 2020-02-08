package cn.com.zy.params;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ResetRTableParam<T> {
    public List<T>entities = new ArrayList<>();

    /**
     * @description: <p>根据传输的映射规则进行删除，比如删除某特定角色关联的menu信息，可以只传递一个字符串：'roleId'</p>
     */
    public Set<String> mappingFields = new HashSet<String>();
}
