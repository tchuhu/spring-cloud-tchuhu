package com.tchuhu.common.core.util.tree;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * <b> TreeComplexField </b>
 * <p>
 * 功能描述:树节点复杂属性
 * </p>
 *
 * @author tchuhu
 * @date 2019/9/7
 * @time 13:24
 * @path com.shiyou.common.core.util.tree.TreeComplexField
 */
public interface TreeComplexField extends Serializable {
	JSONObject toJSON();
}
