package com.kepco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoApp {
    private Map<String, String> resultMap;
    private Map<String, List> tasks;

    public TodoApp(Map<String, List> initData){
        tasks = initData;
    }

    public boolean queryTask(String userName, Integer index) {
        if(index < 0) return false;
        List taskList = tasks.get(userName);
        try {
            resultMap = (Map) taskList.get(index - 1);
        } catch(Exception e){
            resultMap = null;
            return false;
        }
        return true;
    }

    public Map getResult() {
        return resultMap;
    }
}
