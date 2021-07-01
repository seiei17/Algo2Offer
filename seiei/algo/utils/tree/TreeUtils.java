package algo.utils.tree;

import algo.utils.tree.definition.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class TreeUtils {


    //**********algo.utils.tree.definition.TreeNode*********//
    //----------traverse---------//
    public static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    //--------reduction--------//
    /*
    Reduction a binary tree using preorder and inorder.
    */
    public static TreeNode reductionByPreAndInorder(int[] preorder, int[] inorder) {
        return reduction(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode reduction(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int leftLen = inStart;
        while (leftLen <= inEnd && inorder[leftLen] != root.val) leftLen++;
        leftLen -= inStart; 
        root.left = reduction(preorder, preStart + 1, preStart + leftLen, inorder, inStart, inStart + leftLen - 1);
        root.right = reduction(preorder, preStart + leftLen + 1, preEnd, inorder, inStart + leftLen + 1, inEnd);
        return root;
    }

    //----------create---------//
    public static TreeNode createByLevel(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        return createByLevel(list);
    }

    public static TreeNode createByLevel(List<Integer> nums) {

        if (nums.size() == 0) return null;
        if (nums.get(0) == '#') return null;

        int index = 0;
        TreeNode root = new TreeNode(nums.get(index ++));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                if (!queue.isEmpty()) {
                    continue;
                }else {
                    break;
                }
            }
            if (nums.size() - index >= 2) {
                if (nums.get(index ++) == '#') {
                    cur.left = null;
                }else {
                    cur.left = new TreeNode(nums.get(index - 1));
                }
                if (nums.get(index ++) == '#') {
                    cur.right =null;
                }else {
                    cur.right = new TreeNode(nums.get(index - 1));
                }
                queue.offer(cur.left);
                queue.offer(cur.right);
            }else if (nums.size() - index == 1) {
                if (nums.get(index ++) == '#') {
                    cur.left = null;
                }else {
                    cur.left = new TreeNode(nums.get(index - 1));
                }
                queue.offer(cur.left);
                cur.right = null;
            }else {
                cur.left = null;
                cur.right = null;
            }
        }
        return root;
    }
}
