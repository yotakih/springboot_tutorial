package com.example.demo.app.form;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

/**
 * レスポンス用の構造体　controllerから外に向けての返事用
 * 
 * @author kiyotah
 *
 */
@Data	//Dataアノテーションはlombokの機能で自動的にgetter, setter, コンストラクタを作成しくれる
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) //jsonはスネークケース、javaではキャメルケースで対応づけする
public class PlayerOutputRequestForm {

	/**
	 * id
	 */
	private String id;
	/**
	 * 名前
	 */
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
