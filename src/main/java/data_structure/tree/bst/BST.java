package data_structure.tree.bst;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayDeque;
import java.util.Deque;

@ToString
public class BST<T extends Comparable> {

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        TreeNode root = new TreeNode(8);
        tree.insertIteration(root, 3);
        tree.insertIteration(root, 15);
        tree.insertIteration(root, 19);
        tree.insertIteration(root, 13);

        tree.preorder(root);
        System.out.println();
        tree.inorder(root);
        System.out.println();
        tree.postorder(root);

        root = tree.delete(root, 8);
        System.out.println();
        tree.inorder(root);
        System.out.println();
        tree.preorder(root);
    }

    /*
     * Search
     */
    public TreeNode searchRecursion(TreeNode root, T val) {
        if (root == null || val == null) {
            return null;
        }

        if (root.getVal().equals(val)) {
            return root;
        }

        if (root.getVal().compareTo(val) < 0) {
            return searchRecursion(root.getRight(), val);
        } else {
            return searchRecursion(root.getLeft(), val);
        }
    }

    public TreeNode searchIteration(TreeNode root, T val) {
        if (root == null || val == null) {
            return null;
        }

        while (root != null) {
            if (root.getVal().equals(val)) {
                return root;
            }
            if (root.getVal().compareTo(val) < 0) {
                root = root.getRight();
            } else {
                root = root.getLeft();
            }
        }

        return null;
    }

    /*
     * Insert
     */
    public TreeNode insertRecursion(TreeNode root, T val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.getVal().equals(val)) {
            return root;
        }

        if (root.getVal().compareTo(val) < 0) {
            root.setRight(insertRecursion(root.getRight(), val));
        } else {
            root.setLeft(insertRecursion(root.getLeft(), val));
        }

        return root;
    }

    public TreeNode insertIteration(TreeNode root, T val) {
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode cur = root;
        TreeNode toAdd = new TreeNode(val);
        while (cur != null) {
            if (cur.getVal().equals(val)) {
                return cur;
            }

            if (cur.getVal().compareTo(val) < 0) {
                if (cur.getRight() == null) {
                    cur.setRight(toAdd);
                    return root;
                } else {
                    cur = cur.getRight();
                }
            } else {
                if (cur.getLeft() == null) {
                    cur.setLeft(toAdd);
                    return root;
                } else {
                    cur = cur.getLeft();
                }
            }
        }

        return root;
    }

    /*
     * Delete
     */
    public TreeNode delete(TreeNode root, T val) {
        if (root == null) {
            return null;
        }

        // Step 1: find the node
        if (root.getVal().compareTo(val) < 0) {
            root.setRight(delete(root.getRight(), val));
            return root;
        } else if (root.getVal().compareTo(val) > 0) {
            root.setLeft(delete(root.getLeft(), val));
            return root;
        }

        // Step 2: delete the node
        // case 1: no child
        if (root.getLeft() == null && root.getRight() == null) {
            return null;
        }

        // case 2: only has left or right
        if (root.getLeft() != null && root.getRight() == null) {
            return root.getLeft();
        } else if (root.getRight() != null && root.getLeft() == null) {
            return root.getRight();
        }

        // case 3: has both left & right
        // use smallest in the right tree
        if (root.getRight().getLeft() == null) {
            root.getRight().setLeft(root.getLeft());
            return root.getRight();
        }

        TreeNode prev = root.getRight();
        TreeNode cur = root.getRight().getLeft();
        while (cur.getLeft() != null) {
            prev = cur;
            cur = cur.getLeft();
        }

        prev.setLeft(cur.getRight());
        cur.setLeft(root.getLeft());
        cur.setRight(root.getRight());
        return cur;
    }

    /*
     * Pre-order
     */
    public void preorder(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            System.out.println(cur.getVal());
            if (cur.getRight() != null) {
                stack.offerFirst(cur.getRight());
            }
            if (cur.getLeft() != null) {
                stack.offerFirst(cur.getLeft());
            }
        }
    }

    /*
     * In-order
     */
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null) {
            stack.offerFirst(root);
            root = root.getLeft();
        }

        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            System.out.println(cur.getVal());
            if (cur.getRight() != null) {
                cur = cur.getRight();
                while (cur != null) {
                    stack.offerFirst(cur);
                    cur = cur.getLeft();
                }
            } else {
                cur = stack.pollFirst();
                while (!stack.isEmpty() && stack.peekFirst().getRight() == cur) {
                    cur = stack.pollFirst();
                }
            }
        }
    }

    /*
     * Post-order
     */
    public void postorder(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode prev = null, cur = root;

        while (!stack.isEmpty()) {
            cur = stack.peekFirst();
            // case 1: going down
            if (prev == null || prev.getLeft() == cur || prev.getRight() == cur) {
                if (cur.getLeft() == null && cur.getRight() == null) {
                    System.out.println(cur.getVal());
                    stack.pollFirst();
                } else if (cur.getLeft() != null) {
                    stack.offerFirst(cur.getLeft());
                } else {
                    stack.offerFirst(cur.getRight());
                }
            }
            // case 2: from left
            if (prev == cur.getLeft()) {
                if (cur.getRight() == null) {
                    System.out.println(cur.getVal());
                    stack.pollFirst();
                } else {
                    stack.offerFirst(cur.getRight());
                }
            }
            // case 3: from right
            if (prev == cur.getRight()) {
                System.out.println(cur.getVal());
                stack.pollFirst();
            }

            prev = cur;
        }
    }
}

@ToString
@Getter
@Setter
class TreeNode<T extends Comparable> {
    private T val;
    private TreeNode left, right;

    public TreeNode(T val) {
        this.val = val;
        left = null;
        right = null;
    }
}
