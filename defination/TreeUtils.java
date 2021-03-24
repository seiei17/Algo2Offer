import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class TreeUtils {


    //**********TreeNode*********//
    //----------traverse---------//
    public static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
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
