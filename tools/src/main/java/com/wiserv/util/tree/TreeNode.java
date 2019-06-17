package com.wiserv.util.tree;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode {

    private String key;

    private Integer value;

    private TreeNode left;

    private TreeNode right;


}
