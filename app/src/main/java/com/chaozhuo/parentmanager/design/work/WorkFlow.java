package com.chaozhuo.parentmanager.design.work;

import android.util.SparseArray;

public class WorkFlow {

    static final SparseArray<WorkNode> works = new SparseArray<>();

    public static void start(final int index) {
        if (index > works.size() - 1) return;
        WorkNode workNode = works.get(works.keyAt(index));
        workNode.doWork(new Runnable() {
            @Override
            public void run() {
                start(1 + index);
            }
        });
    }

    public static void addWord(WorkNode node) {
        works.put(node.getId(), node);
    }
}
