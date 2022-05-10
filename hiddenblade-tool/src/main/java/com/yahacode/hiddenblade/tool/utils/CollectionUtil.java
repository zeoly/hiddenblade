package com.yahacode.hiddenblade.tool.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static <E> List<List<E>> splitList(List<E> list, int size) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        List<List<E>> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            int endIdx = i + size > list.size() ? list.size() : i + size;
            results.add(list.subList(i, endIdx));
        }
        return results;
    }
}
