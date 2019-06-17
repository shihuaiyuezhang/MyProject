package com.wiserv.util.util;

import java.io.Closeable;

public class IOUtil {

    public static void quietClose(Closeable closeObj) {
        if (closeObj != null) {
            try {
                closeObj.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
