package com.tchuhu.common.core.util.tree;

import lombok.Data;

import java.util.List;

/**
 * <b> TreeNodeClassInfo </b>
 * <p>
 * 功能描述:树节点字节码信息
 * </p>
 *
 * @author tchuhu
 * @date 2018/7/26
 * @time 12:35
 */
@Data
class TreeNodeClassInfo {
    /**
     * 类名
     */
    private Class type;

    /**
     * 类属性
     */
    private List<TreeFieldInfo> fieldInfoList;


}
