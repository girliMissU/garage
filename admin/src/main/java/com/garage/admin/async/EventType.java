package com.garage.admin.async;

/**
 * Created by nowcoder on 2016/7/30.
 */
public enum EventType {
    LIKE(0),
    REPLY(1),
    FOLLOW(2),
    UNFOLLOW(3),
    COMMENT(4);

    private int value;

    EventType(int value) { this.value = value; }

    public int getValue() { return value; }
}
