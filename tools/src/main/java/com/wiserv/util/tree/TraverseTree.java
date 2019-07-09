package com.wiserv.util.tree;

import com.wiserv.util.tree.entity.TreeNode;

import java.util.LinkedList;

public class TraverseTree {


    /**
     * 前序遍历：根结点 ---> 左子树 ---> 右子树
     * （递归版本）
     * @param root
     */
    public void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            System.out.print(root.getValue());
            preOrderTraverse1(root.getLeft());
            preOrderTraverse1(root.getRight());
        }

    }

    /**
     * 前序遍历：根结点 ---> 左子树 ---> 右子树
     *
     * @param root
     */
    public void preOrderTraverse2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                System.out.print(node.getValue()+"  ");
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                node = node.getRight();
            }
        }
    }

    /**
     * 中序遍历：左子树 ---> 根节点 ---> 右子树
     * （递归版本）
     * @param root
     */
    public void inOrderTraverse1(TreeNode root) {
        if (root != null) {
            inOrderTraverse1(root.getLeft());
            System.out.print(root.getValue()+"  ");
            inOrderTraverse1(root.getRight());
        }
    }

    /**
     * 中序遍历：左子树 ---> 根节点 ---> 右子树
     * 通过stack实现
     * @param root
     */
    public void inOrderTraverse2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                System.out.print(node.getValue()+"  ");
                node = node.getRight();
            }
        }
    }
    /**
     * 后序遍历：左子树 ---> 右子树 ---> 根结点
     * （递归版本）
     * @param root
     */
    public void postOrderTraverse1(TreeNode root) {
        if (root != null) {
            preOrderTraverse1(root.getLeft());
            preOrderTraverse1(root.getRight());
            System.out.print(root.getValue());
        }

    }
    /**
     * 后序遍历：左子树 ---> 右子树 ---> 根结点
     * 
     * @param root
     */
    public void postOrderTraverse2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        TreeNode pre = null;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            }else {
                node = stack.peek();
                //右节点为空，或者已经访问过了，弹栈、访问、标记
                if(node.getRight() == null || node.getRight() == pre){
                    node = stack.pop();
                    System.out.print(root.getValue() +"  ");
                    pre = node;
                    node = null;//避免再次压栈
                }else {
                    node = node.getRight();
                }
            }
        }
    }


    public void postOrderTraversal(TreeNode root){
        TreeNode node = root;
        LinkedList<TreeNode> stack1 = new LinkedList<>();
        LinkedList<TreeNode> stack2 = new LinkedList<>();

        while(node != null || !stack1.isEmpty()){
            //只把右节点压栈
            while(node != null){
                stack1.push(node);
                stack2.push(node);
                node = node.getRight();
            }
            if(!stack1.isEmpty()){
                node = stack1.pop();//先弹栈，再访问左节点，此时栈顶存的是下一次循环要访问其左节点的节点
                node = node.getLeft();
            }
        }
        while(!stack2.isEmpty()){
            node = stack1.pop();
            System.out.print(node.getValue() +"  ");
        }
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public void levelTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            System.out.print(node.getValue()+"  ");
            if (node.getLeft() != null) {
                queue.push(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.push(node.getRight());
            }
        }
    }





}
