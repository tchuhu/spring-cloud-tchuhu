package com.tchuhu.common.core.util.tree;

import lombok.Data;

/**
 * <b> 树结构默认扩展实体 </b>
 * <p>
 * 功能描述:提供默认的树节点扩展实体，自定义的树节点扩展实体继承该实体即可实现树节点属性扩展，如果自定义实体使用@Data注解，需重写toString方法
 * </p>
 *
 * @author tchuhu
 * @date 2018/4/9
 * @time 11:04
 */
@Data
public class DefaultTreeExtend extends TreeExtend {

    @TreeProperty(TreeField.ID)
    protected String id;

    @TreeProperty(TreeField.PARENTID)
    protected String parentId;

    @TreeProperty(TreeField.NAME)
    protected String name;

	@Override
	public void setHasLeaf(Boolean hasLeaf) {
		super.setHasLeaf(hasLeaf);
	}

	@Override
	public void setIsLeaf(Boolean isLeaf) {
		super.setIsLeaf(isLeaf);
	}

	@Override
    public String toString() {
        return super.toString();
    }
}
