package com.game.service;

import com.game.model.Player;

import java.util.List;

public interface PlayerService {

    public Player getById(Long id);
    public List<Player> findAll();
    public void createPlayer(Player player);
    public boolean update(Player player, Long id);
    public void deleteById(Long id);

}
