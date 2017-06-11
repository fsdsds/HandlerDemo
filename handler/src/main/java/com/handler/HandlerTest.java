package com.handler;

import java.util.UUID;

/**
 * Created by Tony on 2017/6/12.
 */

public class HandlerTest {

    public static void main(String[] args) {
        //轮询器初始化
        Looper.prepare();
        //主线程
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(Thread.currentThread().getName() + ",received: "+msg.toString()
                );
            }
        };

        //子线程发送消息
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Message msg = new Message();
                        msg.what = 1;
                        synchronized (UUID.class){
                            //加锁，线程安全
                            msg.obj = Thread.currentThread().getName() +",send message:"+ UUID.randomUUID().toString();
                        }
                        handler.sendMessage(msg);
                        System.out.println(msg);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        //开始轮询
        Looper.loop();
    }

}
