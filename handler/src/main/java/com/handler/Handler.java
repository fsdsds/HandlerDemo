package com.handler;

/**
 * Created by Tony on 2017/6/11.
 */

public class Handler {

    private MessageQueue mQueue;

    //Handler的初始化在主线程中完成
    public Handler() {
        //获取主线程的Looper对象
        this.mQueue = Looper.myLooper().mQueue;
    }

    /**
     * 发送消息，压入队列
     * @param msg
     */
    public void sendMessage(Message msg) {
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }

    public void handleMessage(Message msg) {
    }
}
