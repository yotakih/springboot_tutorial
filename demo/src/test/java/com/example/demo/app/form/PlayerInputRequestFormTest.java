package com.example.demo.app.form;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

/**
 * PlayerInputRequestForm テストするクラス バリデーションチェックが主な仕事
 * 
 * @author kiyotah
 *
 */
@SpringBootTest(classes = { ValidationAutoConfiguration.class, PlayerInputRequestForm.class })
public class PlayerInputRequestFormTest {

	/**
	 * Validation を行う Varidator
	 */
	@Autowired
	private Validator validator;

	/**
	 * リクエストフォーム名
	 */
	private static final String FORM_NAME = "PlayerInputRequestForm";

	/**
	 * リクエストフォーム
	 */
	private PlayerInputRequestForm playerInputRequestForm = new PlayerInputRequestForm();

	/**
	 * テスト実施前に実行される
	 */
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		playerInputRequestForm.setId("1");
		playerInputRequestForm.setName("HOGE FUGA");
		playerInputRequestForm.setTeam("green");
		playerInputRequestForm.setPosition("center");
	}

	/**
	 * 正常 バリデーションエラーなしのテスト
	 */
	@Test
	void test_正常_エラーなし() {
		BindingResult bindingResult = new BindException(playerInputRequestForm, FORM_NAME);

		// テスト
		validator.validate(playerInputRequestForm, bindingResult);

		// 結果確認
		assertEquals(bindingResult.getFieldError(), null);
	}

	/**
	 * 異常 id 必須チェック 空文字
	 */
	@Test
	void test_異常_id_必須チェク_空文字() {
		BindingResult bindingResult = new BindException(playerInputRequestForm, FORM_NAME);

		// idのあたいを変更
		playerInputRequestForm.setId("");

		// テスト
		validator.validate(playerInputRequestForm, bindingResult);

		// 結果確認
		assertEquals(bindingResult.getFieldError().getField(), "id");
		// id には NotEmpty と Range が設定されているが、今回のテスト（値が空）ではどちらでもエラーになりえる。
		// だが、バリデーションの実施順番はまちまちなためどちらのエラーになるか（どちらが先に実行されるか）わからない
//		assertEquals(bindingResult.getFieldError().getDefaultMessage(), "{0}には必ず値を設定してください");
//		assertEquals(bindingResult.getFieldError().getDefaultMessage(), "{0}には1から100の値を設定してください");
		assertThat(bindingResult.getFieldError().getDefaultMessage().equals("{0}には必ず値を設定してください")
				|| bindingResult.getFieldError().getDefaultMessage().equals("{0}には1から100の値を設定してください"));
	}

}
