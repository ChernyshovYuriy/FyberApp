package com.yuriy.fyberapp.vo;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/28/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * {@link com.yuriy.fyberapp.vo.BaseVO} is a base class to provide ability to perform
 * Value Objects implementation. Values are storing in the map.
 */
public abstract class BaseVO implements Serializable {

    /**
     * Map which is keep pairs of the key - value, where 'key' is a name of the parameter
     * and 'value' is its value.
     */
    private final Map<String, Object> parametersMap = new TreeMap<String, Object>();

    /**
     * Return parameter from the map by the provided key.
     * @param key Key of the parameter.
     * @return Value, associated with the key, or empty string if key is not found.
     */
    @SuppressWarnings("unchecked")
    protected <T> T getParameterByKey(final String key) {
        return (T) parametersMap.get(key);
    }

    /**
     * Set provided value at the specified key in the map.
     *
     * @param key   Key of the parameter
     * @param value Value of the parameter.
     */
    protected <T> void setParameterForKey(final String key, final T value) {
        parametersMap.put(key, value);
    }

    /**
     * @return The set of the keys stored in the map.
     */
    protected Set<String> getKeySet() {
        return parametersMap.keySet();
    }
}
