package com.player.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.player.exception.BusinessException;
import com.player.model.Player;
import com.player.service.PlayerSearchService;
import com.player.service.impl.PlayerSearchServiceImpl;



//returns and displays ResultSet instead of Player object, also iterates twice for the heck of it.  I'm just learnin stuff
public class PlayerMain {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Vinay's Player Search APP V1.0");
		System.out.println("--------------------------------------------------");
		PlayerSearchService playerSearchService=new PlayerSearchServiceImpl();
		int ch = 0;
		do {
			System.out.println("Player Search MENU");
			System.out.println("======================");
			System.out.println("1)By ID");
			System.out.println("2)By Name");
			System.out.println("3)By Age");
			System.out.println("4)By Gender");
			System.out.println("5)By Contact");
			System.out.println("6)By TeamName");
			System.out.println("7)All Players");
			System.out.println("8)EXIT");
			System.out.println("Please enter appropriate choice(1-8) :) ");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}
			switch (ch) {
			case 1:
			//	System.out.println("case - 1");//Take it off when presenting
				System.out.println("Please Enter Player ID to get Player Details");
				try {
					int id = Integer.parseInt(scanner.nextLine());
					//Code Here for SERVICE LAYER
					Player player=playerSearchService.getPlayerById(id);
					if(player!=null) {
						System.out.println("We found a player by ID = "+id+" Details are : ");
						System.out.println(player);
					}
				} catch (NumberFormatException e) {
					System.out.println("ID should be number only.. Kindly Re-enter");
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:	System.out.println("Thank you for your interest. This option is still under CONSTRUCTION");

			

				break;
			case 3:
				System.out.println("Thank you for your interest. This option is still under CONSTRUCTION");

				break;
			case 4:
				System.out.println("Please enter gender(m/f/o) to get the Players with that gender ");
				String gender=scanner.nextLine();
				try {
					List<Player> playersListGender=playerSearchService.getPlayersByGender(gender);
					if(playersListGender!=null && playersListGender.size()>0) {
						System.out.println("We Found "+playersListGender.size()+" no of player/s in the DB..with gender "+gender+" Details are");
						for(Player p:playersListGender) {
							System.out.println(p);
						}
					}
				} catch (BusinessException e1) {
					System.out.println(e1.getMessage());
				}

				break;
			case 5:
				System.out.println("Thank you for your interest. This option is still under CONSTRUCTION");

				break;
			case 6:
				System.out.println("Thank you for your interest. This option is still under CONSTRUCTION");

				break;
			case 7:
				System.out.println("Fetching All The Players From the DB");
				try {
					System.out.println("asdfsadf");
					ResultSet resultSet=playerSearchService.getAllPlayers();
					//if(resultSet!=null) {
						System.out.println("aaaaaaa");
						//System.out.println("We Found "+playersList.size()+" no of player/s in the DB.. Details are");
						System.out.println(resultSet.next());
						resultSet.first();
					try {	
						
						
						while(resultSet.next()) {
							System.out.println(resultSet.getInt("id")+ resultSet.getString("name")+ resultSet.getString("teamName")+
									resultSet.getInt("age")+ resultSet.getLong("contact")+ resultSet.getString("gender"));
						}
					}catch(NoSuchElementException e2) {
						System.out.println("ah ha");
					}
					//}
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 8:
				System.out.println(
						"Thank YOU for using Vinay's Player Search App V1.0... We would expect you back very soon :)");

				break;
			default:
				System.out.println("Invalid Choice!!!! Please enter choice between 1-8 only");
				break;
			}
		} while (ch != 8);

	}

}
