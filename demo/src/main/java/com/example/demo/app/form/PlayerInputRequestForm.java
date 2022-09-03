package com.example.demo.app.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

/**
 * リクエスト構造体controllerとservice間の橋渡し用
 * 
 * @author kiyotah
 *
 */
@Data	//Dataアノテーションはlombokの機能で自動的にgetter, setter, コンストラクタを作成しくれる
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) //jsonはスネークケース、javaではキャメルケースで対応づけする
public class PlayerInputRequestForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// バリデーションは各変数にそれぞれ対応するアノテーションをつける
	// メッセージは ValidationMessge.propertiesに設定されている
	
	/**
	 * id
	 */
	@NotEmpty(message = "{custom.message.HISSU_CHECK}")		        //バリデーション　必須
	@Range(min=1, max=100, message = "{custom.message.RANGE}")		//範囲 id は1から100の間でないとNG
	private String id;
	/**
	 * 名前
	 */
	@NotEmpty									//バリデーション　必須
	@Pattern(regexp = "^[A-Z]+ [A-Z]+$")		//パターンマッチ この場合は名前は全角英数で間に１つ半角空白が必要
	private String name;
	/**
	 * 所属チーム
	 */
	private String team;
	/**
	 * ポジション
	 */
	private String position;

}
