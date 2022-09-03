package com.example.demo.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.domain.service.PlayerDto;
import com.example.demo.domain.service.PlayerService;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

	@InjectMocks
	private PlayerController playerController;

	@MockBean
	private PlayerService playerService;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * players.htmlの取得テスト
	 */
	@Test
	void test_正常_get_players_html() {
		// リクエストURL
		String url = "/players/all";
		// スタブ設定
		when(playerService.findAll()).thenReturn(new ArrayList<PlayerDto>() {
			private static final long serialVersionUID = 1L;
			{
				add(new PlayerDto(1L, "HOGE FUGA", "green", "center"));
				add(new PlayerDto(2L, "PIYO BAR", "red", "right"));
			}
		});
		// テスト
		try {
			// リクエストURLにGET要求をして、HTTPステータスがOK（200）になること。ついでにandDo(print())でレスポンスの内容をコンソールに出力
			mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}