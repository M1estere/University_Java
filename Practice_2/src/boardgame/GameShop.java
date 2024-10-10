package boardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameShop {

    private Map<String, BoardGame> gamesCatalog;
    private float earnings;

    public GameShop(Map<String, BoardGame> incomingGames) {
        this.gamesCatalog = incomingGames;
    }

    public BoardGame buyGame(String game, float money) {
        if (this.gamesCatalog.containsKey(game) == false) return null;

        if (this.gamesCatalog.get(game).getPrice() > money) {
            return null;
        }

        earnings += money;
        return this.gamesCatalog.remove(game);
    }

    public float getEarnings() {
        return earnings;
    }

    public List<BoardGame> getCatalog() {
        return new ArrayList<>(gamesCatalog.values());
    }
}
