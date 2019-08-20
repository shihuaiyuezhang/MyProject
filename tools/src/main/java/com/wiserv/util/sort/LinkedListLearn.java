package com.wiserv.util.sort;

public class LinkedListLearn {


    /**
     * 反转单向链表
     * @param head
     */
    public  static Node reverse1(Node<Integer> head) {
        if(head == null || head.next == null){
            return head;
        }
        Node node = reverse1(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public  static Node reverse2(Node<Integer> head) {
        Node current = head,node = null,next;
        while(current != null) {
            next = current.next;
            current.next = node;
            node = current;
            current = next;
        }
        return node;
    }

    /**
     * 内部类
     * @param <T>
     */
    static class Node<T>{
        public T value;
        public Node next;

        public Node(T data){
            this.value = data;
        }
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        Node current = head;
        for(int i = 1;i < 4; i++) {
            current.next = new Node(i+1);
            current = current.next;
        }
        Node<Integer> node = LinkedListLearn.reverse1(head);
        System.out.println(node);
    }

}


