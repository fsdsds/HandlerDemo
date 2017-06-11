package com.handler;


/**
 * Created by Tony on 2017/6/11.
 */

public class Message {

    Handler target;
    public int what;
    public Object obj;
    @Override
    public String toString() {
        return obj.toString();
    }
}
