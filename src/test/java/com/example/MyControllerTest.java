package com.example;

import java.util.Iterator;
import java.util.Map;

import com.example.model.LoginUserModel;
import com.example.model.MessagesModel;
import com.example.MyController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.mvc.Models;

class MyModels implements jakarta.mvc.Models {
	@Override
	public Iterator<String> iterator() {
		return null;
	}
	@Override
	public Models put(String name, Object model) {
		return null;
	}
	@Override
	public Object get(String name) {
		return null;
	}
	@Override
	public <T> T get(String name, Class<T> clazz) {
		return null;
	}
	@Override
	public Map<String, Object> asMap() {
		return null;
	}
}

public class MyControllerTest {
	@Test
	public void testGetMessage() {
		var loginUserModel = new LoginUserModel();
		var myController = new MyController(new MyModels(), new MessagesModel(), loginUserModel);
		myController.getMessage();
		// Assertions.assertEquals(loginUserModel.getName(), "鴨川三条");
		Assertions.assertEquals(loginUserModel.getName(), "宇治川三条");
	}
}
