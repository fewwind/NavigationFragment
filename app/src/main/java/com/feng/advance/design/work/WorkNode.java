package com.feng.advance.design.work;

public class WorkNode implements Node {

    int id;
    Worker worker;
    Runnable runnable;

    public void doWork(Runnable run) {
        this.runnable = run;
        worker.doWork(this);
    }

    @Override
    public void onComplete() {
        if (runnable != null) runnable.run();
    }

    @Override
    public int getId() {
        return id;
    }

    public WorkNode(int id, Worker worker) {
        this.id = id;
        this.worker = worker;
    }

}
