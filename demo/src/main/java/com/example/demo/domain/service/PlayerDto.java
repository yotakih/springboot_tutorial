package com.example.demo.domain.service;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Player 操作のための構造体serviceとrepository間
 * 
 * @author kiyotah
 *
 */
@Data	//Dataアノテーションはlombokの機能で自動的にgetter, setter, コンストラクタを作成しくれる
@AllArgsConstructor //lombokの機能で自動で、インスタンス変数（idrとか）を引数にとるコンストラクタを生成してくれる
public class PlayerDto {
	/**
	 * id
	 */
	private Long id;
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
