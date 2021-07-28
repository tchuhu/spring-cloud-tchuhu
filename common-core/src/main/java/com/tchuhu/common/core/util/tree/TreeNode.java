package com.tchuhu.common.core.util.tree;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * <b> TreeNode </b>
 * <p>
 * 功能描述:树形结构 节点
 * </p>
 *
 * @author tchuhu
 * @date 2018/3/7
 * @time 18:12
 * @path cn.phxg.entity.dto.TreeNode
 */
@Data
public class TreeNode {
	/**
	 * 节点主键
	 */
	private String id;

	/**
	 * 节点内容 v2
	 */
	private String name;

	/**
	 * 父节点主键
	 */
	private String parentId;

	/**
	 * 子节点
	 */
	private TreeChildren children = new TreeChildren();

	/**
	 * 扩展信息
	 */
	private TreeExtend extend;

	public void setHasLeaf(boolean hasLeaf) {
		extend.hasLeaf = hasLeaf;
	}

	public boolean isLeaf() {
		return extend.getIsLeaf() && children.getSize() <= 0;
	}

	public void setLeaf(boolean isLeaf) {
		extend.isLeaf = isLeaf;
	}

	public boolean hasLeaf() {
		return !isLeaf();
	}

	public boolean effectNode() {
		return extend.hasLeaf || extend.isLeaf;
	}

	/**
	 * 兄弟节点横向排序
	 */
	public void sortChildren() {
		if (children != null && children.getSize() != 0) {
			children.sortChildren();
		}
	}

	public void sortChildren(HashMap<Class, TreeNodeClassInfo> treeClassCache) {
		if (children != null && children.getSize() != 0) {
			children.sortChildren(treeClassCache);
		}
	}

	/**
	 * 添加子节点
	 *
	 * @param node
	 */
	public void addChild(TreeNode node) {
		this.children.addChild(node);
		//根据子节点设置当前节点是否有叶子
		this.setHasLeaf(effectNode());
		//存在子节点说明是非叶子节点
		this.setLeaf(false);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

	public JSONObject toJSON() {
		return toJSON(null);
	}


	JSONObject toJSON(HashMap<Class, TreeNodeClassInfo> classInfo) {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		json.put("parentId", parentId);
		json.putAll(classInfo == null ? extend.toJSON() : extend.toJSON(classInfo));
		List<JSONObject> childrenList = classInfo == null ? children.toJSON() : children.toJSON(classInfo);
		if (!CollectionUtil.isEmpty(childrenList)) {
			json.put("children", childrenList);
		}
		json.put("isLeaf", isLeaf());
		json.put("hasLeaf", hasLeaf());
		return json;
	}
}

