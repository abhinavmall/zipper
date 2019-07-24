package com.practice.zipper.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class Utilities {
    private static final Logger logger =
            LoggerFactory
                    .getLogger(
                            Utilities.class);

    /**
     * This method returns true if the collection is null or is empty.
     * @param collection
     * @return true | false
     */
    public boolean isEmpty( Collection<?> collection ){
        if( collection == null || collection.isEmpty() ){
            return true;
        }
        return false;
    }

    /**
     * This method returns true of the map is null or is empty.
     * @param map
     * @return true | false
     */
    public boolean isEmpty( Map<?, ?> map ){
        if( map == null || map.isEmpty() ){
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the object is null.
     * @param object
     * @return true | false
     */
    public boolean isEmpty( Object object ){
        if( object == null ){
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the input array is null or its length is zero.
     * @param array
     * @return true | false
     */
    public boolean isEmpty( Object[] array ){
        if( array == null || array.length == 0 ){
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the input string is null or its length is zero.
     * @param string
     * @return true | false
     */
    public boolean isEmpty( String string ){
        if( string == null || string.trim().length() == 0 ){
            return true;
        }
        return false;
    }
}
