package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> mFavorite = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavorite() {
        return mFavorite;
    }

    @Override
    public void createFavorite(Neighbour neighbour) {
        mFavorite.add(neighbour);
    }

    @Override
    public void deleteDoubleFavorite(Neighbour neighbour) {
        mFavorite.remove(null);
    }

    @Override
    public void deleteFavorite(Neighbour neighbour) {
        mFavorite.remove(neighbour);
    }
}
