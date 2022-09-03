package com.example.demo.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.service.PlayerDto;

/**
 * SQLが記載してあるXMLファイルとJavaクラスを紐づけるクラス
 * @author kiyotah
 *
 */
@Mapper
public interface PlayerRepository {
    List<PlayerDto> findAll();

    PlayerDto findOne(Long id);

    void save(PlayerDto player);

    void update(PlayerDto player);

    void delete(Long id);	
}
