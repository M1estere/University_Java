package boardgame;

import java.util.*;

public class App {
    public static void main(String[] args) {
        List<String> boardGamesNames = new ArrayList<>(Arrays.asList(
                "Dune", "Resident Evil", "Descent", "Bunker", "Poker",
                "Runebound", "Gloomhaven", "D&D", "Ticket To Ride", "Battletech",
                "Jackal", "Pathfinder", "Unmatched", "Catan"
        ));

        // filling shop with games
        Map<String, BoardGame> boardGames = new HashMap<String, BoardGame>();
        for (String gameName : boardGamesNames) {
            int playerMin = new Random().nextInt(2 - 1) + 1;
            int playerMax = new Random().nextInt(8 - 4) + 4;
            int duration = new Random().nextInt(800 - 300) + 300;
            int age = new Random().nextInt(18 - 6) + 6;
            int price = new Random().nextInt(5000 - 800) + 800;
            boardGames.put(gameName, new BoardGame(gameName, playerMin, playerMax, duration,age, price));
        }
        GameShop gameShop = new GameShop(boardGames);

        // filling customers
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            customers.add(new Customer(boardGamesNames.remove(new Random().nextInt(boardGamesNames.size())), new Random().nextInt(5000 - 500) + 500));
        }

        int sold = 0;
        for (Customer customer : customers) {
            if (gameShop.getCatalog().size() <= 0) break;

            BoardGame result = gameShop.buyGame(customer.getGameName(), customer.getMoney());
            if (result == null) {
                System.out.println(String.format("Customer has not enough money for %s (%s₽)", customer.getGameName(), customer.getMoney()));
            } else {
                sold++;
                System.out.println(String.format("Customer bought %s for %s₽", customer.getGameName(), result.getPrice()));
            }
        }

        System.out.println("Sold: " + sold);
        System.out.println("Earnings: " + gameShop.getEarnings());
    }
}
