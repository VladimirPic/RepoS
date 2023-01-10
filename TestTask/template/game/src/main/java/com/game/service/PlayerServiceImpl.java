package com.game.service;

import com.game.model.Player;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {


    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player getById(Long id) {
        return playerRepository.getOne(id);
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public void createPlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public boolean update(Player player, Long id) {
        if(playerRepository.findById(id).isPresent()) {
            player.setId(id);
            playerRepository.save(player);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
}
