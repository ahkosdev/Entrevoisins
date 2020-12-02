package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {


    private NeighbourApiService mApiService;
    private List<Neighbour> mFavoriteNeighbours;





    public static FavoriteFragment newInstance() {


        return (new FavoriteFragment());
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getNeighbourApiService();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View favoriteView = inflater.inflate(R.layout.fragment_favorite, container, false);
        ImageView favoriteAvatar = (ImageView) favoriteView.findViewById(R.id.favorite_item_list_avatar);
        TextView favoriteName = (TextView) favoriteView.findViewById(R.id.favorite_item_list_name);



        return favoriteView;

    }



    private void initList(){
        mFavoriteNeighbours = mApiService.getNeighbours();
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onFavoriteEvent(AddFavoriteEvent event) {
        mApiService.createFavorite(event.neighbour);
        initList();
    }
}