package com.redhat.labsjp.sample.at;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

import com.codeborne.selenide.Condition;

import com.kepco.TodoApp;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TodoRefStepDefinition {

    TodoApp todoApp;

    @Given("初期データを利用してTODOアプリを開始する")
    public void 初期データを利用してtodoアプリを開始する() {
        // 初期データを用意
        Map<String, List> initData = new HashMap<>();
        List<Map> taskListA = new LinkedList<>();
        taskListA.add(createTaskItem("タスク1", "作業内容1"));
        taskListA.add(createTaskItem("タスク2", "作業内容2"));
        initData.put("A", taskListA);

        List<Map> taskListB = new LinkedList<>();
        taskListB.add(createTaskItem("タスク1B", "作業内容1B"));
        taskListB.add(createTaskItem("タスク2B", "作業内容2B"));
        initData.put("B", taskListB);

        // アプリに初期データを設定する
        todoApp = new TodoApp(initData);

    }

    @When("ユーザー{string}の{int}番目のタスクを問い合わせる")
    public void ユーザー_の_番目のタスクを問い合わせる(String userName, Integer index) {
        // アプリを開始する（問い合わせることじゃ）
        todoApp.queryTask(userName, index);
    }

    @Then("タスク概要が{string}、作業内容が{string}と表示される")
    public void タスク概要が_作業内容が_と表示される(String string, String string2) {
        // 結果を得る
        Map result = todoApp.getResult();

        // assertするのじゃ
        assertEquals(string, result.get("Overview"));
        assertEquals(string2, result.get("Content"));
    }

    private Map<String, String> createTaskItem(String overview, String content) {
        Map<String, String> item = new HashMap<>();
        item.put("Overview", overview);
        item.put("Content", content);
        return item;
    }
}
