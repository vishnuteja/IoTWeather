package hello;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {

	static GameInfo game;
	static String baseUrl = "http://ir-interviews.herokuapp.com/maze";
	static RestTemplate restTemplate;
	static ObjectMapper mapper;
	static long nTurns = 0;
	static long nAdvances = 0;
	static boolean endOfGame;

	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {

		restTemplate = new RestTemplate();
		mapper = new ObjectMapper();

		printToConsole("Welcome to Maze Game. ");
		printToConsole("______________________");

		Scanner sc = new Scanner(System.in);

		endOfGame = false;

		while (endOfGame == false) {

			if (game == null) {
				printToConsole("Press 0 to start a New Maze");
			}

			else {
				printToConsole("\n Menu.");
				System.out.println("1) Advance");
				System.out.println("2) Left Turn");
				System.out.println("3) Right Turn");
				System.out.println("4) Quick Feet");
				System.out.println("5) Auto Solve");
				System.out.println("6) Check if allowed");

				System.out.println("0) New Maze");
				System.out.println("$) End");
			}

			System.out.print("\n Enter choice : ");

			String choice = sc.nextLine();

			if ("0".equals(choice)) // New Maze
				game = createNewGame();
			else if ("1".equals(choice) && game != null) // Advance
				advance();
			else if ("2".equals(choice) && game != null) // Left Turn
				leftTurn();
			else if ("3".equals(choice) && game != null) // Right Turn
				rightTurn();
			else if ("4".equals(choice) && game != null) // Quick Feet
				quickFeet();
			else if ("5".equals(choice) && game != null) // Auto Solve
				autoSolve();
			else if ("6".equals(choice) && game != null) // Check Allowed
				isAllowed();
			else if ("$".equals(choice) && game != null) // Game End
				endGame(false);
			else {
				printToConsole("Invalid input. Please try again. You might start creating a new game with option 0.");
				continue;
			}

			printTotals();

		}

		sc.close();
	}

	private static GameInfo createNewGame() {

		GameInfo game = null;

		try {

			ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/start", String.class);
			game = mapper.readValue(response.getBody(), GameInfo.class);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				printToConsole("Your game has just started and your Maze Id and Persona are as follows :");
				printToConsole("Maze Id : " + game.getId());
				printToConsole("Persona : " + game.getPersona());
				printToConsole("Your abilities : " + game.getAbilities());

			} else {
				printToConsole("Sorry there was an error starting a new game. Please try again");
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return game;
	}

	private static boolean advance() throws JsonParseException, JsonMappingException, IOException {

		nAdvances++;

		long nextXpos = getNextXpostion(game.getxPos(), game.getStartingDirection(), "normal");
		long nextYpos = getNextYpostion(game.getyPos(), game.getStartingDirection(), "normal");

		ResponseEntity<String> response = restTemplate.getForEntity(
				baseUrl + "/allowed?id=" + game.getId() + "&x=" + nextXpos + "&y=" + nextYpos, String.class);
		Allowed isAllowed = mapper.readValue(response.getBody(), Allowed.class);

		if (isAllowed.getAllowed() && isNextInBounds()) {
			ResponseEntity<String> advanceCall = restTemplate
					.getForEntity(baseUrl + "/advance?id=" + game.getId() + "&type=normal", String.class);

			Advance advance = mapper.readValue(advanceCall.getBody(), Advance.class);
			
			game.setxPos(advance.getxPos());
			game.setyPos(advance.getyPos());
			
			if (advance.isAtExit()) 
				endGame(true);

			printToConsole("Advanced. ");
			return true;
		} else {
			printToConsole("Advance is not allowed");
			return false;
		}
	}

	private static void leftTurn() {

		String newDirection = null;

		nTurns++;

		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/turn/left?id=" + game.getId(),
				String.class);

		if (response.getStatusCode().equals(HttpStatus.OK)) {
			if ("east".equals(game.getStartingDirection()))
				newDirection = "north";
			else if ("north".equals(game.getStartingDirection()))
				newDirection = "west";
			else if ("west".equals(game.getStartingDirection()))
				newDirection = "south";
			else if ("south".equals(game.getStartingDirection()))
				newDirection = "east";

			game.setStartingDirection(newDirection);

			printToConsole("Yay. Currently you are heading towards - " + newDirection);
		}

		else {
			printToConsole("Error. Please try again");
		}

	}

	private static void rightTurn() {

		String newDirection = null;

		nTurns++;

		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/turn/right?id=" + game.getId(),
				String.class);
		
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			if ("east".equals(game.getStartingDirection()))
				newDirection = "south";
			else if ("south".equals(game.getStartingDirection()))
				newDirection = "west";
			else if ("west".equals(game.getStartingDirection()))
				newDirection = "north";
			else if ("north".equals(game.getStartingDirection()))
				newDirection = "east";

			game.setStartingDirection(newDirection);

			printToConsole("Yay. Currently you are heading towards - " + newDirection);
		} else {
			printToConsole("Error. Please try again");
		}
	}

	private static void quickFeet() throws JsonParseException, JsonMappingException, IOException {

		nAdvances++;

		if (game.getQuickFeet()) {
			
			long nextXpos = getNextXpostion(game.getxPos(), game.getStartingDirection(), "quickFeet");
			long nextYpos = getNextYpostion(game.getyPos(), game.getStartingDirection(), "quickFeet");

			ResponseEntity<String> response = restTemplate.getForEntity(
					baseUrl + "/allowed?id=" + game.getId() + "&x=" + nextXpos + "&y=" + nextYpos, String.class);
			Allowed isAllowed = mapper.readValue(response.getBody(), Allowed.class);

			if (isAllowed.getAllowed()) {
				ResponseEntity<String> advanceCall = restTemplate
						.getForEntity(baseUrl + "/advance?id=" + game.getId() + "&type=quickFeet", String.class);

				if (advanceCall.getStatusCode().equals(HttpStatus.OK)) {
					Advance advance = mapper.readValue(advanceCall.getBody(), Advance.class);

					game.setxPos(advance.getxPos());
					game.setyPos(advance.getyPos());

					printToConsole("Success. Advanced to next place. ");
					
					if (advance.isAtExit()) 
						endGame(true);
					
				} else {
					printToConsole("There was some unknown error. Please try again. ");
				}
			} else {
				printToConsole("Advance is not allowed");
			}
			
		}

		else {
			printToConsole("Quick Feet is not allowed");
		}

	}

	private static boolean isAllowed() throws JsonParseException, JsonMappingException, IOException {

		boolean isAllowed = false;

		long nextXpos = getNextXpostion(game.getxPos(), game.getStartingDirection(), "normal");
		long nextYpos = getNextYpostion(game.getyPos(), game.getStartingDirection(), "normal");

		System.out.println(baseUrl + "/allowed?id=" + game.getId() + "&x=" + nextXpos + "&y=" + nextYpos);
		
		ResponseEntity<String> response = restTemplate.getForEntity(
				baseUrl + "/allowed?id=" + game.getId() + "&x=" + nextXpos + "&y=" + nextYpos, String.class);
		
		Allowed allowedObj = mapper.readValue(response.getBody(), Allowed.class);

		if (allowedObj.getAllowed()) {
			printToConsole("Yes. Advance is allowed");
			isAllowed = true;
		} else {
			printToConsole("No. Advance is not allowed");
		}

		return isAllowed;
	}

	private static void endGame(boolean win) {

		if (!win) {
			printToConsole("Thank you for playing the game. Will hope to see you again.");
			printToConsole("Developed by ~ Vishnu");
		} else {
			String winMessage = 
					"__  __               _       ___          __\n"+ 
					"\\ \\/ /___  __  __   | |     / (_)___     / /\n" + 
					" \\  / __ \\/ / / /   | | /| / / / __ \\   / / \n"+ 
					" / / /_/ / /_/ /    | |/ |/ / / / / /  /_/  \n" + 
					"/_/\\____/\\__,_/     |__/|__/_/_/ /_/  (_)   \n"+
					"                                            ";
			printToConsole(winMessage);
		}
		game = null;
		endOfGame = true;
	}

	private static void autoSolve() throws JsonParseException, JsonMappingException, IOException {
		
		while(endOfGame==false)
		{
			if(isAllowed() && isNextInBounds())
				advance();
			else
			{
				leftTurn();
				
				if(isAllowed() && isNextInBounds())
					advance();
				
				else
				{
					rightTurn();
					
					if(isAllowed() && isNextInBounds())
						advance();
				}
			}
		}
	}

	private static boolean isNextInBounds() {
		long nextXpos = getNextXpostion(game.getxPos(), game.getStartingDirection(), "normal");
		long nextYpos = getNextYpostion(game.getyPos(), game.getStartingDirection(), "normal");
		
		if(nextXpos < 0 || nextXpos > 12 || nextYpos < 0 || nextYpos > 7)
			return false;
		else
			return true;
	}
	
	private static void printTotals() {
		printToConsole("\nAdvances : " + nAdvances + "  |  Turns : " + nTurns + "  |  Total : " + (nTurns + nAdvances) + "\n");
	}

	private static long getNextXpostion(long xPos, String direction, String type) {

		long newX = xPos;

		if ("normal".equals(type)) {
			if (direction.equals("east"))
				newX = xPos + 1;
			if (direction.equals("west"))
				newX = xPos - 1;
			if (direction.equals("south"))
				newX = xPos;
			if (direction.equals("north"))
				newX = xPos;
		}

		else if ("quickFeet".equals(type)) {
			if (direction.equals("east"))
				newX = xPos + 2;
			if (direction.equals("west"))
				newX = xPos - 2;
			if (direction.equals("south"))
				newX = xPos;
			if (direction.equals("north"))
				newX = xPos;
		}

		return newX;
	}

	private static long getNextYpostion(long yPos, String direction, String type) {

		long newY = yPos;

		if ("normal".equals(type)) {
			if (direction.equals("east"))
				newY = yPos;
			if (direction.equals("west"))
				newY = yPos;
			if (direction.equals("south"))
				newY = yPos + 1;
			if (direction.equals("north"))
				newY = yPos - 1;
		}

		else if ("quickFeet".equals(type)) {
			if (direction.equals("east"))
				newY = yPos;
			if (direction.equals("west"))
				newY = yPos;
			if (direction.equals("south"))
				newY = yPos + 2;
			if (direction.equals("north"))
				newY = yPos - 2;
		}

		return newY;
	}

	// Print method with TypeWriter effect
	public static void printToConsole(String msg) {
		int i;
		for (i = 0; i < msg.length(); i++) {
			System.out.print(msg.charAt(i));
			try {
				Thread.sleep(5);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}

}
