package com.example.demo.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.domain.repository.PlayerRepository;

public class PlayerServiceTest {

	/**
	 * テスト対象のクラス
	 */
	@InjectMocks 
	PlayerService playerService;
	
	/**
	 * スタブ
	 */
	@Mock //PlayerServiceクラスがもっている変数でスタブにしたいものを宣言
	PlayerRepository playerRepository;
	
	// 以下はおまじない
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * findOne機能（idからPlayer1件分を取得する）のテスト
	 */
	@Test
	void test_findOne() {
		
		// スタブ（playerRepository.findOneの戻り値をつくっておく）
		PlayerDto playerDto = new PlayerDto(1L, "HOGE FUGA", "green", "center");
		/**
		 * テスト対象クラス（PlayerService）のfindOneメソッド内の
		 * 「PlayerRepository.findOne(id)」の動作を入れ替えて、上で生成したplayerDtoが
		 * 返されるようにする
		 */
		when(playerRepository.findOne(Mockito.any())).thenReturn(playerDto);
		
		// 実際にテスト
		PlayerDto result = playerService.findOne(1L);
		
		// テスト結果を検証
		// うまくいけばスタブのあたいと同じものが帰ってくるはず
		assertEquals(result.getId(), playerDto.getId());  
		assertEquals(result.getName(), playerDto.getName());
		assertEquals(result.getTeam(), playerDto.getTeam());
		assertEquals(result.getPosition(), playerDto.getPosition());
	}
}
