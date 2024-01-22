package com.lenhatthanh.blog.shared;

import java.util.UUID;

public class UniqueIdGenerator {
    public static String create () {
        // TODO: generate ID for database distributed
        // Example: Using Snowflake
        return UUID.randomUUID().toString();
    }
}
