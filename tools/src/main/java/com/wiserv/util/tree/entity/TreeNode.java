package com.wiserv.util.tree.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode {

    private Integer value;

    private TreeNode left;

    private TreeNode right;

}
