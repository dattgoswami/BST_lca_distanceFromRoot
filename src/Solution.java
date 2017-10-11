

import java.io.*;
import java.util.*;

public class Solution {
    private static BSTNode root;
    public Solution(){
        Solution.root = null;
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        try{
        Scanner scan = new Scanner(System.in);
        int value_a = 2;
        int value_b = 5;
//        int total_elements = 6;
        insert(6);
        insert(8);
        insert(3);
        insert(4);
        insert(2);
        insert(5);
//        inorder();
        
        if(!find(value_a) || !find(value_b)){
            System.out.println("Not found");
        }else{
        	System.out.println(distance(root, value_a, value_b));
        }
        scan.close();
        }catch(Exception e){
            
        }
    }
    /*Algorithm - getting the distance of both the nodes from root
    * getting the distance of lca from the root
    *adding the first two values and subtracting the root to lca distance twice as it is present in both the values x and y
    *distance(a,b) = distance(root,a) + distance(root,b) - 2 distance(root,lca(a,b))
    */
    public static int distance(BSTNode p, int a, int b){
        int x = distanceFromRoot(p,a) - 1;
        int y = distanceFromRoot(p,b) - 1;
        BSTNode lca_node = lca(p,a,b);
        int lca_distance = distanceFromRoot(p, lca_node.data) - 1; 
        return ( (x + y) - (2*lca_distance));
    }
    public static int distanceFromRoot(BSTNode p, int a){
        if(p == null){
            return 0;
        }else{
            int length = 0;
            if((p.data == a) || ((length = distanceFromRoot(p.left,a)) > 0) || ((length = distanceFromRoot(p.right,a)) > 0)){
                return (length + 1);
            }
            return 0;
        }
    }
    public static BSTNode lca(BSTNode p, int a, int b){
        BSTNode temp_root = p;
        if(p == null){
            return  null;
        }
        if((temp_root.data > a) && (temp_root.data < b)){
            return temp_root;
        }else  if((temp_root.data > a) && (temp_root.data > b)){
        	return lca(p.left,a,b);
        }else  if((temp_root.data < a) && (temp_root.data < b)){
            return lca(p.left,a,b);
        }
        return p;
    }
    public static boolean find(int val){
        return findHelper(root,val);
    }
    public static boolean findHelper(BSTNode p, int val){
        boolean flag = false;
        while( (p != null) && !flag){
            if(p.data == val){
                flag = true;
                break;
            }else if(p.data > val){
                p = p.left;
            }else if(p.data < val){
                p = p.right;
            }
            flag = findHelper(p,val);
        }
        return flag;
    }
    public static void inorder(){
            inorderHelper(root);
    }
    public static void inorderHelper(BSTNode p){
        if(p != null){
            inorderHelper(p.left);
            System.out.print(p.data + " ");
            inorderHelper(p.right);
        }
    }
    public static void insert(int val){
        root = insertHelper(root,val);
    }
    public static BSTNode insertHelper(BSTNode p, int val){
        if(p == null){
            return new BSTNode(val);
        }
        if(p.data > val){
            p.left = insertHelper(p.left, val);
        }else if(p.data < val){
            p.right = insertHelper(p.right, val);
        }
        return p;
    } 
}
class BSTNode{
    public int data;
    public BSTNode right;
    public BSTNode left;
    public BSTNode(int val){
        this.data = val;
        this.right = null;
        this.left = null;
    }
}