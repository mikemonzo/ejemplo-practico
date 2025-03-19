package com.example.java.out;

import java.util.List;

import com.example.java.model.test.Test;

public interface OutputTest {

    void ououtTest(Test test) throws Exception;

    void outputCorreccion(Test test) throws Exception;

    void ououtTests(List<Test> testList) throws Exception;

    void outputCorreccion(List<Test> testList) throws Exception;

    List<String> formatTest(Test test);

    List<String> formatCorreccion(Test test);

}
