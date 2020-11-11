package com.player.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.player.exception.BusinessException;
import com.player.model.Player;

public interface PlayerSearchDAO {
	public Player getPlayerById(int id)throws BusinessException;
	public ResultSet getAllPlayers()throws BusinessException, ClassNotFoundException, SQLException;
	public List<Player> getPlayersByName(String name)throws BusinessException;
	public List<Player> getPlayersByAge(int age)throws BusinessException;
	public Player getPlayerByContactNumber(long contact)throws BusinessException;
	public List<Player> getPlayersByTeamName(String teamName)throws BusinessException;
	public List<Player> getPlayersByGender(String gender)throws BusinessException;
}
