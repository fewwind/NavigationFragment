package com.chaozhuo.parentmanager.test.algorithm

import com.chaozhuo.parentmanager.bean.LinkNode
import java.util.*

class LeetCode {
    fun treeMaxPath(tree: FTree.TreeNode): Int {
        var stack = LinkedList<FTree.TreeNode>()
        stack.push(tree)
        var max = 0
        while (stack.isNotEmpty()) {
            max++
            var size = stack.size
            while (size-- > 0) {
                var pop = stack.pop()
                println(pop.`val`)
                //用push是深度，addLast是广度，pop和poll逻辑都是取first。但是一个是栈一个队列
                if (pop.right != null) stack.push(pop.right)
                if (pop.left != null) stack.push(pop.left)
//                var pop = stack.poll()
//                println(pop.`val`)
//                if (pop.right != null) stack.addLast(pop.right)
//                if (pop.left != null) stack.addLast(pop.left)
            }
        }
        return max
    }

}

fun main(args: Array<String>) {
    var leetCode = LeetCode()
    var simple = FSimple()
    var tree = FTree()
    var head = LinkNode(1, LinkNode(1, LinkNode(2, LinkNode(2, LinkNode(3, null)))))
    var cycle = LinkNode(1, LinkNode(2, LinkNode(3, LinkNode(4, LinkNode(2, null)))))
//    1
//  21    22
// 31 32 33 34
//    leetCode.treeMaxPath(FTree.root)
//    simple.indexAdd(3, intArrayOf(0,2,1,5))
//    simple.reverseNum(123)
//    var res = simple.isOrderArray(intArrayOf(1,1,2,0))
//    println(res)
//    simple.s2Int("123")
//    FSimple.better(intArrayOf(1,2,3,5),5)
//    Flink.deleteRepeat(head)
    Flink.isNodeCycle2(cycle)
}