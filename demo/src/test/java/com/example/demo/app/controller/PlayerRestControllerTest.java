package com.example.demo.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.app.controller.PlayerRestController;
import com.example.demo.app.form.PlayerOutputRequestForm;
import com.example.demo.domain.service.PlayerDto;
import com.example.demo.domain.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PlayerRestControllerTest {

	@InjectMocks
	private PlayerRestController playerRestController;

	@Mock
	private PlayerService playerService;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(playerRestController).build();
	}

	@Test
	void test_正常_get_player() {
		// リクエストURL
		String url = "/api/players/1";
		// スタブ設定
		when(playerService.findOne(Mockito.any())).thenReturn(new PlayerDto(1L, "HOGE FUGA", "green", "center"));
		// テスト
		try {
			// リクエストURLにGET要求をして、HTTPステータスがOK（200）になること。ついでにandDo(print())でレスポンスの内容をコンソールに出力
			MvcResult result = mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk()).andReturn();
			// レスポンスの値を検証
			String json = result.getResponse().getContentAsString();
			ObjectMapper objectMapper = new ObjectMapper();
			PlayerOutputRequestForm res = objectMapper.readValue(json, PlayerOutputRequestForm.class);
			// 以下の値が帰ってきているはず
			assertEquals(res.getId(), "1");
			assertEquals(res.getName(), "HOGE FUGA");
			assertEquals(res.getTeam(), "green");
			assertEquals(res.getPosition(), "center");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}