package com.example.demo.app.controller;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.app.form.PlayerInputRequestForm;
import com.example.demo.app.form.PlayerOutputRequestForm;
import com.example.demo.domain.service.PlayerDto;
import com.example.demo.domain.service.PlayerService;

@RestController // RestAPIのリクエストを受け入れるコントローラ宣言
@RequestMapping("/api/players") // このコントローラへのリクエストURL
public class PlayerRestController {

	@Autowired
	private PlayerService playerService;

	/**
	 * HttpGetで /api/players/1 でリクエストすると、id = 1の Player が帰ってくる
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<PlayerOutputRequestForm> findOne(@PathVariable Long id) {
		PlayerDto result = playerService.findOne(id);
		// 同じインスタンス変数をもつオブジェクトどうしの移し替えができる
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		PlayerOutputRequestForm res = mapper.map(result, PlayerOutputRequestForm.class);
		// mappter 使わない場合は以下になる
//			res.setId(result.getId().toString());
//			res.setName(result.getName());
//			res.setTeam(result.getTeam());
//			res.setPosition(result.getPosition());
		return new ResponseEntity<PlayerOutputRequestForm>(res, HttpStatus.OK);
	}

	/**
	 * Http Postで /api/players/new で Player 型の Json を送信すると登録される
	 * @param player
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("new")
	public ResponseEntity<String> create(@RequestBody @Validated PlayerInputRequestForm player,
			BindingResult bindingResult) {
		// bindingResult にpostされたjsonをバリデーションした結果がすでに入っている
		if (bindingResult.hasErrors()) {
			// 短項目チェックでエラーになった
			return new ResponseEntity<String>("短項目エラー", HttpStatus.BAD_REQUEST);
		}
		// サービスに渡すため、PlayerDtoに入れ替え
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		PlayerDto playerDto = mapper.map(player, PlayerDto.class);
		playerService.save(playerDto);
		// response を返す
		return new ResponseEntity<String>("created",HttpStatus.CREATED);
	}

}
