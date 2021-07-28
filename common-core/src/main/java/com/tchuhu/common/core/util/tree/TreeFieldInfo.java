package com.tchuhu.common.core.util.tree;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * <b> TreeFieldInfo </b>
 * <p>
 * 功能描述:TreeFieldInfo
 * </p>
 *
 * @author tchuhu
 * @date 2018/7/26
 * @time 12:43
 */
@Data
class TreeFieldInfo {
    /**
     * 字段对象
     */
    private Field field;
    /**
     * 排序信息
     */
    private Sort sort;
    /**
     * 映射树属性
     */
    private TreeProperty treeProperty;

	/**
	 * 树属性前缀
	 */
	private TreeFieldPrefix treeFieldPrefix;
}
