package com.wiserv.util.tree.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode {

    private Integer value;

    private TreeNode left;

    private TreeNode right;


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
