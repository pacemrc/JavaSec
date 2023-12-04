package com.pacemrc.javasec.utils;


import me.gv7.tools.josearcher.entity.Blacklist;
import me.gv7.tools.josearcher.entity.Keyword;
import me.gv7.tools.josearcher.searcher.SearchRequstByBFS;

import java.util.ArrayList;
import java.util.List;

public class ObjectSearcher {

    public static void searchTomcat() {

        //设置搜索类型包含Request关键字的对象
        List<Keyword> keys = new ArrayList<>();
        keys.add(new Keyword.Builder().setField_type("Request").build());
        //定义黑名单
        List<Blacklist> blacklists = new ArrayList<>();
        blacklists.add(new Blacklist.Builder().setField_type("java.io.File").build());
        //新建一个广度优先搜索Thread.currentThread()的搜索器
        SearchRequstByBFS searcher = new SearchRequstByBFS(Thread.currentThread(),keys);
        // 设置黑名单
        searcher.setBlacklists(blacklists);
        //打开调试模式,会生成log日志
        searcher.setIs_debug(true);
        //挖掘深度为20
        searcher.setMax_search_depth(20);
        //设置报告保存位置
        searcher.setReport_save_path("D:\\tmp");
        searcher.searchObject();

    }
}
