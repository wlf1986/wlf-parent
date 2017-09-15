package com.common;

import com.utils.Page;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月21日 11:13
 */
public abstract class PageUtil {
    private static final ThreadLocal threadLocal = new ThreadLocal();

    public static void setPage(Page page){
        threadLocal.set(page);
    }

    public static Page getPage(){
        return (Page) threadLocal.get();
    }

    public static Page getAndRemPage(){
        Page page = null;
        try {
            page = (Page) threadLocal.get();
        }finally {
            threadLocal.remove();
        }
        return page;
    }
}
