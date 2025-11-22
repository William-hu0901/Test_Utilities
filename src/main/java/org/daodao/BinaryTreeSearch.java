package org.daodao;

/**
 * 二叉搜索树节点类
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

/**
 * 二叉搜索树类，包含查找、插入等基本操作
 */
class BinarySearchTree {
    private TreeNode root;
    
    public BinarySearchTree() {
        this.root = null;
    }
    
    /**
     * 插入节点到二叉搜索树
     * @param val 要插入的值
     */
    public void insert(int val) {
        root = insertRec(root, val);
    }
    
    /**
     * 递归插入节点
     * @param root 当前根节点
     * @param val 要插入的值
     * @return 插入后的根节点
     */
    private TreeNode insertRec(TreeNode root, int val) {
        // 如果树为空，创建一个新节点
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        
        // 否则递归地向下插入
        if (val < root.val) {
            root.left = insertRec(root.left, val);
        } else if (val > root.val) {
            root.right = insertRec(root.right, val);
        }
        
        // 值已存在，不做改变（也可以根据需求决定是否允许重复值）
        return root;
    }
    
    /**
     * 在二叉搜索树中查找指定值
     * @param val 要查找的值
     * @return 如果找到返回true，否则返回false
     */
    public boolean search(int val) {
        return searchRec(root, val);
    }
    
    /**
     * 递归查找节点
     * @param root 当前根节点
     * @param val 要查找的值
     * @return 如果找到返回true，否则返回false
     */
    private boolean searchRec(TreeNode root, int val) {
        // 基本情况：树为空或找到了目标值
        if (root == null) {
            return false;
        }
        
        if (root.val == val) {
            return true;
        }
        
        // 根据二叉搜索树的性质选择左子树或右子树继续查找
        if (val < root.val) {
            return searchRec(root.left, val);
        }
        
        return searchRec(root.right, val);
    }
    
    /**
     * 在二叉搜索树中查找指定值，并返回该节点
     * @param val 要查找的值
     * @return 如果找到返回对应的节点，否则返回null
     */
    public TreeNode findNode(int val) {
        return findNodeRec(root, val);
    }
    
    /**
     * 递归查找节点并返回
     * @param root 当前根节点
     * @param val 要查找的值
     * @return 如果找到返回对应的节点，否则返回null
     */
    private TreeNode findNodeRec(TreeNode root, int val) {
        // 基本情况：树为空或找到了目标值
        if (root == null || root.val == val) {
            return root;
        }
        
        // 根据二叉搜索树的性质选择左子树或右子树继续查找
        if (val < root.val) {
            return findNodeRec(root.left, val);
        }
        
        return findNodeRec(root.right, val);
    }
    
    /**
     * 中序遍历二叉搜索树（将按升序打印所有节点值）
     */
    public void inorderTraversal() {
        inorderRec(root);
        System.out.println();
    }
    
    /**
     * 递归进行中序遍历
     * @param root 当前根节点
     */
    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.val + " ");
            inorderRec(root.right);
        }
    }
}

/**
 * 二叉树查找算法主类
 */
public class BinaryTreeSearch {
    
    public static void main(String[] args) {
        // 创建一个二叉搜索树实例
        BinarySearchTree bst = new BinarySearchTree();
        
        // 向树中插入一些节点
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        // 中序遍历（验证树的结构）
        System.out.println("中序遍历结果（应该是升序的）：");
        bst.inorderTraversal();
        
        // 测试查找功能
        int[] testValues = {20, 40, 55, 70, 90};
        for (int val : testValues) {
            if (bst.search(val)) {
                System.out.println("值 " + val + " 在树中找到");
            } else {
                System.out.println("值 " + val + " 不在树中");
            }
        }
    }
}