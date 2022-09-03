package com.example.demo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.service.PlayerDto;
import com.example.demo.domain.service.PlayerService;

@Controller // HTMLを返すリクエストを受け入れるコントローラ宣言
@RequestMapping("/players") // このコントローラへのリクエストURL
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	/**
	 * HttpGetで /players/ でリクエストすると、player.html が帰ってくる
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public String players(Model model) {
		// 画面に表示するためのデータを取得する
		List<PlayerDto> result = playerService.findAll();
		// 画面にデータを渡すため、modelに設定する。modelは画面にデータを渡すためのオブジェクト。
		model.addAttribute("list", result);
		// 画面を返す。文字列を返すと、src/main/resources/template 配下で合致するパスのhtmlが返される
		return "players.html";
	}

}
