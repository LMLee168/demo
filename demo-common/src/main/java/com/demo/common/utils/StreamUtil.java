package com.demo.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.MapUtils.isEmpty;

public class StreamUtil {

    /**
     * 从Collection<OrderItem>到 Map<Key, OrderItem>，提取 Key, Map 的 Value 就是类型 OrderItem
     * 从Collection<OrderItem>到 Map<Key，Value> ，提取 Key, Map 的 Value 根据 OrderItem 类型进行转化。
     */
    public static <T, K> Map<K, T> toMap(Collection<T> collection, Function<? super T, ? extends K> keyMapper) {
        return toMap(collection, keyMapper, Function.identity());
    }

    public static <T, K, V> Map<K, V> toMap(Collection<T> collection,
                                            Function<? super T, ? extends K> keyFunction,
                                            Function<? super T, ? extends V> valueFunction) {
        return toMap(collection, keyFunction, valueFunction, pickSecond());
    }

    public static <T, K, V> Map<K, V> toMap(Collection<T> collection,
                                            Function<? super T, ? extends K> keyFunction,
                                            Function<? super T, ? extends V> valueFunction,
                                            BinaryOperator<V> mergeFunction) {
        if (CollectionUtils.isEmpty(collection)) {
            return new HashMap<>(0);
        }

        return collection.stream().collect(Collectors.toMap(keyFunction, valueFunction, mergeFunction));
    }

    /**
     * 将 Map<Long, OrderItem> 中的value 转化为 Map<Long, Double>
     * value 转化时，lamada表达式可以使用（v)->{}， 也可以使用 （k，v）->{ }。
     */
    public static <K, V, C> Map<K, C> convertMapValue(Map<K, V> map,
                                                      BiFunction<K, V, C> valueFunction,
                                                      BinaryOperator<C> mergeFunction) {
        if (isEmpty(map)) {
            return new HashMap<>();
        }
        return map.entrySet().stream().collect(Collectors.toMap(
                e -> e.getKey(),
                e -> valueFunction.apply(e.getKey(), e.getValue()),
                mergeFunction
        ));
    }

    public static <K, V, C> Map<K, C> convertMapValue(Map<K, V> originMap, BiFunction<K, V, C> valueConverter) {
        return convertMapValue(originMap, valueConverter, pickSecond());
    }

    /**
     * 将 Collection<OrderItem> 转化为 List<OrderItem>
     * 将 Collection<OrderItem> 转化为 Set<OrderItem>
     */

    public static <T> List<T> toList(Collection<T> collection) {
        if (collection == null) {
            return new ArrayList<>();
        }
        if (collection instanceof List) {
            return (List<T>) collection;
        }
        return collection.stream().collect(Collectors.toList());
    }

    public static <T> Set<T> toSet(Collection<T> collection) {
        if (collection == null) {
            return new HashSet<>();
        }
        if (collection instanceof Set) {
            return (Set<T>) collection;
        }
        return collection.stream().collect(Collectors.toSet());
    }

    /**
     * List、Set 类型之间的转换
     */
    public static <T, R> List<R> map(List<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> map(Set<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <T, R> List<R> mapToList(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> mapToSet(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <T> BinaryOperator<T> pickFirst() {
        return (k1, k2) -> k1;
    }
    public static <T> BinaryOperator<T> pickSecond() {
        return (k1, k2) -> k2;
    }


}
