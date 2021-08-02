package com.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Constant {
    public static Map<String, AtomicInteger> RUN_COUNT_MAP= Collections.synchronizedMap(new HashMap());
}
