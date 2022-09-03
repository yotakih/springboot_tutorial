package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.repository.PlayerRepository;

/**
 * Player情報の取得、登録、更新、削除を行うサービス
 * @author kiyotah
 *
 */
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository PlayerRepository;

    @Transactional
    public List<PlayerDto> findAll() {
        return PlayerRepository.findAll();
    }

    @Transactional
    public PlayerDto findOne(Long id) {
        return PlayerRepository.findOne(id);
    }

    @Transactional
    public void save(PlayerDto PlayerDto) {
        PlayerRepository.save(PlayerDto);
    }

    @Transactional
    public void update(PlayerDto PlayerDto) {
        PlayerRepository.update(PlayerDto);
    }

    @Transactional
    public void delete(Long id) {
        PlayerRepository.delete(id);
    }
}
