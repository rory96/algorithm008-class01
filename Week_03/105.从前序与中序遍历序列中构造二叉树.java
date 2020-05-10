/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int preIndex = 0;
    int[] preorder;
    int[] inorder;
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        int idx= 0;
        for (int num : inorder) {
            map.put(num, idx++);
        }
        return help(0, inorder.length);
    }
    private TreeNode help(int left, int right) {
        if (left == right) return null;
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);
        // 获取到中序遍历的根节点下标
        int index = map.get(rootVal);
        root.left = help(left, index);
        root.right = help(index + 1, right);
        return root;
    }
}