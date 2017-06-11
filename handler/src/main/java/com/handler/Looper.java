package com.handler;

/**
 * Created by Tony on 2017/6/11.
 */

public final class Looper {

    //每一个主线程都有一个Looper对象
    //Looper对象保存在ThreadLocal，保证了线程数据的隔离
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();

    //一个Looper对象对应一个消息队列
    MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        if (sThreadLocal.get()!=null){
            throw new RuntimeException("only one looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * 获取当前线程的Looper对象
     * @return
     */
    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    /**
     * 轮询消息队列
     */
    public static void loop() {
        Looper me = myLooper();
        if (me == null) {
            throw  new RuntimeException("");
        }

        MessageQueue queue = me.mQueue;
        for (;;) {
            Message msg = queue.next();
            if (msg == null) {
                continue;
            }
            //转发给Handler
            msg.target.dispatchMessage(msg);

        }
    }

}
