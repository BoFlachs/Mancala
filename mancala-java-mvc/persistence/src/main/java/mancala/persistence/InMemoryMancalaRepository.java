package mancala.persistence;

import mancala.domain.IMancala;

import java.util.HashMap;

public class InMemoryMancalaRepository implements IMancalaRepository{
    HashMap<String, IMancala> repo = new HashMap<String, IMancala>();

    @Override
    public void save(String key, IMancala game) {
        repo.put(key, game);
    }

    @Override
    public IMancala get(String key) {
        return repo.get(key);
    }
}
