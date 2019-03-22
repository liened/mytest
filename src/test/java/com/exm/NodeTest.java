package com.exm;

import org.junit.Before;
import org.junit.Test;

/**
 * 链表操作：http://mp.weixin.qq.com/s?__biz=MzI2NjA3NTc4Ng==&mid=2652080873&idx=1&sn=faace88736f0be905c9ed7dd2ee5c2e9&chksm=f174810cc603081a4446a744ec00fe67c5d794f603b2b41b055a1996324de66a2e25d2479890&scene=0&xtrack=1#rd
 */
public class NodeTest {

    Node root = new Node();

    @Before
    public void init(){
        root.setVal(0);
        root.setName("root_0");
        Node t = root;
        Node e = null;
        for (int i=1;i<9;i++){
            Node a = new Node();
            a.setVal(i);
            a.setName("root_"+i);
            t.setNext(a);
            t = a;
            if (i == 3){
                e = a;
            }
        }
        t.setNext(e);
        System.out.println("============ 初始化完成 ==========");
//        System.out.println(root.getName());
//        Node b = root;
//        while ((b=b.getNext())!=null){
//            System.out.println(b.getName());
//        }
//        System.out.println("=========== 遍历完成 =============");
    }


    @Test
    public void 倒数n为啥(){
        //笨方法，循环了两次
        int len = 1;
        Node t = root;
        while ((t=t.getNext())!=null){
            len++;
        }
        System.out.println("长度为:"+len);
        int k = 5;
        Node a = root;
        for (int i=1;i<len-k+1;i++){
            a = a.getNext();
        }
        System.out.println("倒数第"+k+"个值是:"+a.getName());
    }

    /**
     * 这儿需要注意，例子上写的循环是从0开始的。其实想想最后的时候，slow的位置在哪，就明白相隔多少了
     */
    @Test
    public void 双指针(){
        int k = 5;
        Node fast = root;
        Node slow = root;
        for (int i=1;i<k;i++){
            fast = fast.getNext();
        }
        while (fast.getNext() != null){
            fast = fast.getNext();
            slow = slow.getNext();
        }
        System.out.println("倒数第"+k+"个值是:"+slow.getName());

    }

    @Test
    public void 是否有环_快慢指针(){
        //如果两指针没有在尾部相遇，则有环
        Node fast = root;
        Node slow = root;
        while (fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow){
                System.out.println("有环,相遇点:"+slow.getName());
                break;
            }
        }
        System.out.println("判断完毕");
    }

    @Test
    public void 定位环入口(){
        //找到相遇点
        Node fast = root;
        Node slow = root;
        Node m = null;
        while (fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow){
                System.out.println("找到相遇点了:"+slow.getName());
                m = slow;
                break;
            }
        }

        //p1从root开始走，p2从相遇点开始走，它们再次相遇时就是在入口点（环的交叉点）
        Node p1 = root;
        Node p2 = m;
        while (p1 != p2){
            p1 = p1.getNext();
            p2 = p2.getNext();
        }
        System.out.println("入口点是:"+p1.getName());
    }

    @Test
    public void 环长度(){
        //使用快慢指针法找到相遇点，使指针按原来的规则继续前进，当再次相遇时，slow走过的节点即为环长度
        Node fast = root;
        Node slow = root;
        Node e = null;
        while (fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
            if (fast == slow){
                System.out.println("找到相遇点了:"+slow.getName());
                e = slow;
                break;
            }
        }

        //计算环长度
        int size = 0;
        Node fast2 = e;
        Node slow2 = e;
        while (fast2.getNext() != null){
            size++;
            fast2 = fast2.getNext().getNext();
            slow2 = slow2.getNext();
            if (fast2 == slow2){
                System.out.println("再次相遇了:"+slow2.getNext());
                break;
            }
        }
        System.out.println("环长度为:"+size);
    }


    class Node{
        private int val;
        private String name;
        private Node next;

        public Node() {
        }

        public Node(int val, String name, Node next) {
            this.val = val;
            this.name = name;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
