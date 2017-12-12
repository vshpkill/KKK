package com.youlehuo.app.collection;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by xiaohe on 17-11-30.
 */

public class CollectionDemo {
    public static void method() {
        List<String> strings = new ArrayList<>(Collections.nCopies(5, "str"));
        Collections.fill(strings, "ctr");

        Map<String, String> stringMap = new HashMap<>();

        Pair<String, String> pair = new Pair<>("123123", "456456");
        stringMap.put(pair.first, pair.second);

        Set<Map.Entry<String, String>> ss = stringMap.entrySet();

        //Arrays.asList制造的集合不可改变长度
        List<String> list = Arrays.asList("1","2","3");
        list.add("4");
        SortedSet<String> sortedSet = new TreeSet<>();
    }
}
