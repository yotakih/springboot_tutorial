package com.example.demo.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.example.demo.domain.common.CSVDataSetLoader;
import com.example.demo.domain.service.PlayerDto;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

// 以下のアノテーションはデータベーステスト時のおまじない
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = CSVDataSetLoader.class)
public class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository playerRepository;

	@Test
	@DatabaseSetup(value = "/com/example/demo/domain/repository/datas") //以下のテストメソッドが実行される前に指定したCSVをロードさせる
	void test_検索() {
		PlayerDto player = playerRepository.findOne(1L);
		assertEquals(player.getId(), 1L);
	}
	
	@Test
	@DatabaseSetup(value = "/com/example/demo/domain/repository/datas") //以下のテストメソッドが実行される前に指定したCSVをロードさせる
	@ExpectedDatabase(value = "/com/example/demo/domain/repository/expectdata", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void test_更新() {
		PlayerDto player = playerRepository.findOne(1L);
		player.setName("FUGA PIYO");
		playerRepository.update(player);
	}

}
