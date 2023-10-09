package com.demo.common.utils;

import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class SmartStringStyle extends ToStringStyle {
    private String arrayStart = "{";
    private String arraySeparator = ",";
    private boolean contentDetail = true;
    private String arrayEnd = "}";
    private String mapStart = "{";
    private String mapSeparator = ",";
    private String mapEnd = "}";
    private String collectionStart = "[";
    private String collectionSeparator = ",";
    private String collectionEnd = "]";
    private static final long serialVersionUID = 1L;
    private static final long MAX_ARRAY_LENGTH = 256L;
    private static final long MAX_COLLECTION_SIZE = 256L;
    private static final long MAX_MAP_SIZE = 256L;
    private static final SmartStringStyle instance = new SmartStringStyle();
    private static final ThreadLocal<WeakHashMap<Object, Object>> REGISTRY = new ThreadLocal();

    public SmartStringStyle() {
    }

    public String getNullText() {
        return super.getNullText();
    }

    static Map<Object, Object> getRegistry() {
        return (Map)REGISTRY.get();
    }

    static boolean isRegistered(Object value) {
        Map<Object, Object> m = getRegistry();
        return m != null && m.containsKey(value);
    }

    static void register(Object value) {
        if (value != null) {
            Map<Object, Object> m = getRegistry();
            if (m == null) {
                REGISTRY.set(new WeakHashMap());
            }

            getRegistry().put(value, (Object)null);
        }

    }

    static void unregister(Object value) {
        if (value != null) {
            Map<Object, Object> m = getRegistry();
            if (m != null) {
                m.remove(value);
                if (m.isEmpty()) {
                    REGISTRY.remove();
                }
            }
        }

    }

    public boolean isUseIdentityHashCode() {
        return super.isUseIdentityHashCode();
    }

    public void setUseIdentityHashCode(boolean useIdentityHashCode) {
        super.setUseIdentityHashCode(useIdentityHashCode);
    }

    protected void appendCollection(StringBuffer buffer, String fieldName, Collection<?> value) {
        buffer.append(this.getShortClassName(value.getClass()));
        if ((long)value.size() <= 256L) {
            this.appendCollectionDetail(buffer, fieldName, value, value.size());
        } else {
            this.appendCollectionSummary(buffer, fieldName, value, value.size());
        }

    }

    private void appendCollectionDetail(StringBuffer buffer, String fieldName, Collection<?> value, int size) {
        buffer.append(this.collectionStart);
        Iterator<?> iterator = value.iterator();

        for(int index = 0; iterator.hasNext(); ++index) {
            Object item = iterator.next();
            if (index > 0) {
                buffer.append(this.collectionSeparator);
            }

            if (item == null) {
                this.appendNullText(buffer, fieldName);
            } else {
                this.appendInternal(buffer, fieldName, item, this.contentDetail);
            }
        }

        buffer.append(this.collectionEnd);
    }

    protected void appendCollectionSummary(StringBuffer buffer, String fieldName, Collection<?> value, int length) {
        this.appendSummarySize(buffer, fieldName, length);
    }

    protected void appendMap(StringBuffer buffer, String fieldName, Map<?, ?> value) {
        buffer.append(this.getShortClassName(value.getClass()));
        if ((long)value.size() <= 256L) {
            this.appendMapDetail(buffer, fieldName, value, value.size());
        } else {
            this.appendMapSummary(buffer, fieldName, value, value.size());
        }

    }

    private void appendMapDetail(StringBuffer buffer, String fieldName, Map<?, ?> value, int size) {
        buffer.append(this.mapStart);
        Iterator<? extends Map.Entry<?, ?>> iterator = value.entrySet().iterator();

        for(int index = 0; iterator.hasNext(); ++index) {
            Map.Entry<?, ?> entry = (Map.Entry)iterator.next();
            if (index > 0) {
                buffer.append(this.mapSeparator);
            }

            String key = entry.getKey().toString();
            this.appendFieldStart(buffer, key);
            this.appendInternal(buffer, key, entry.getValue(), this.contentDetail);
        }

        buffer.append(this.mapEnd);
    }

    protected void appendMapSummary(StringBuffer buffer, String fieldName, Map<?, ?> value, int length) {
        this.appendSummarySize(buffer, fieldName, length);
    }

    protected void appendArray(StringBuffer buffer, String fieldName, Object value) {
        buffer.append(this.getShortClassName(value.getClass()));
        int length = Array.getLength(value);
        if ((long)length <= 256L) {
            this.appendArrayDetail(buffer, fieldName, value, length);
        } else {
            this.appendArraySummary(buffer, fieldName, value, length);
        }

    }

    protected void appendArraySummary(StringBuffer buffer, String fieldName, Object value, int length) {
        this.appendSummarySize(buffer, fieldName, length);
    }

    protected void appendArrayDetail(StringBuffer buffer, String fieldName, Object value, int length) {
        buffer.append(this.arrayStart);

        for(int i = 0; i < length; ++i) {
            Object item = Array.get(value, i);
            if (i > 0) {
                buffer.append(this.arraySeparator);
            }

            if (item == null) {
                this.appendNullText(buffer, fieldName);
            } else {
                this.appendInternal(buffer, fieldName, item, this.contentDetail);
            }
        }

        buffer.append(this.arrayEnd);
    }

    protected void appendInternal(StringBuffer buffer, String fieldName, Object value, boolean detail) {
        if (isRegistered(value) && !(value instanceof Number) && !(value instanceof Boolean) && !(value instanceof Character)) {
            this.appendCyclicObject(buffer, fieldName, value);
        } else {
            register(value);

            try {
                if (value instanceof Collection) {
                    this.appendCollection(buffer, fieldName, (Collection)value);
                } else if (value instanceof Map) {
                    this.appendMap(buffer, fieldName, (Map)value);
                } else if (value.getClass().isArray()) {
                    this.appendArray(buffer, fieldName, value);
                } else if (detail) {
                    this.appendDetail(buffer, fieldName, value);
                } else {
                    this.appendSummary(buffer, fieldName, value);
                }
            } finally {
                unregister(value);
            }

        }
    }

    public static SmartStringStyle getInstance() {
        return instance;
    }
}
