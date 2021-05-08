package algo.sword2offer.Q07;

import algo.utils.tree.TreeUtils;
import algo.utils.tree.definition.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution07 {
    private int index = 1;
    private int len = 0;
    private Map<Integer, Integer> pos = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int index = 0; index < inorder.length; index ++) {
            pos.put(inorder[index], index);
        }
        len = preorder.length;
        TreeNode root = new TreeNode(preorder[0]);
        create(null, root, preorder);
        return root;
    }

    private void create(TreeNode last, TreeNode cur, int[] preorder) {
        if (index >= len) return;
        TreeNode next = new TreeNode(preorder[index]);
        if (pos.get(next.val) < pos.get(cur.val)) {
            cur.left = next;
            index ++;
            create(cur, next, preorder);
        } else {
            if (last != null && pos.get(next.val) < pos.get(last.val)) {
                cur.left = null;
                cur.right = next;
                index ++;
                create(cur, next, preorder);
            } else {
            cur.left = null;
                cur.right = null;
            }
        }
    }

    public static void main(String[] args) {
        int[] preorder = new int[] {3, 9, 20, 15, 7};
        int[] inorder = new int[] {9, 3, 15, 20, 7};
        Solution07 solution07 = new Solution07();
        TreeNode root = solution07.buildTree(preorder, inorder);
        TreeUtils.printPreorder(root);
    }
}
