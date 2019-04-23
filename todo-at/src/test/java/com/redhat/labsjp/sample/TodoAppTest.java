package com.redhat.labsjp.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TodoAppTest {

    private TodoApp todoApp;

    private Map<String, String> createTaskItem(String overview, String content) {
        Map<String, String> item = new HashMap<>();
        item.put("Overview", overview);
        item.put("Content", content);
        return item;
    }

    @Before
    public void setUp(){
        Map<String, List> initData = new HashMap<>();
        List<Map> taskListA = new LinkedList<>();
        taskListA.add(createTaskItem("タスク1", "作業内容1"));
        taskListA.add(createTaskItem("タスク2", "作業内容2"));
        initData.put("A", taskListA);

        List<Map> taskListB = new LinkedList<>();
        taskListB.add(createTaskItem("タスク1B", "作業内容1B"));
        taskListB.add(createTaskItem("タスク2B", "作業内容2B"));
        initData.put("B", taskListB);

        todoApp = new TodoApp(initData);
    }

    @Test
    public void 初期データをセットするところのテスト(){
        assertNotEquals(todoApp, null);
    }

    @Test
    public void Aさんの1番目のタスクの問い合わせができる(){
        boolean result = todoApp.queryTask("A", 1);
        assertTrue(result);
    }

    @Test
    public void indexが負の値のとき問い合わせに失敗する(){
        boolean result = todoApp.queryTask("A", -1);
        assertFalse(result);
    }

    @Test
    public void Aさんの1番目のタスクを問い合わせて取得する(){
        boolean result = todoApp.queryTask("A", 1);
        Map<String, String> task = todoApp.getResult();
        assertEquals(task.get("Overview"), "タスク1");
        assertEquals(task.get("Content"), "作業内容1");
    }

    @Test
    public void Aさんの2番目のタスクを問い合わせて取得する(){
        boolean result = todoApp.queryTask("A", 2);
        Map<String, String> task = todoApp.getResult();
        assertEquals(task.get("Overview"), "タスク2");
        assertEquals(task.get("Content"), "作業内容2");
    }

    @Test
    public void Cさんの1番目のタスクを問い合わせてfalseが返ってくる(){
        boolean result = todoApp.queryTask("C", 1);
        assertFalse(result);
    }

    @Test
    public void Aさんの1番目のタスクを問い合わせた後にCさんの1番目のタスクを問い合わせるとfalseが返ってくる(){
        todoApp.queryTask("A", 1);
        boolean result = todoApp.queryTask("C", 1);
        assertFalse(result);
        assertNull(todoApp.getResult());
    }


}
