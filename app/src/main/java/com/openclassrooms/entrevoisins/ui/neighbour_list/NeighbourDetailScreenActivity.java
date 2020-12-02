package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.AddFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NeighbourDetailScreenActivity extends AppCompatActivity{


    @BindView(R.id.activity_detail_avatar_img)
    ImageView mNeighbourAvatar;
    @BindView(R.id.activity_detail_name_test)
    TextView mNeighbourName;
    @BindView(R.id.activity_detail_address_test)
    TextView mNeighbourAddress;
    @BindView(R.id.activity_detail_phone_test)
    TextView mNeighbourPhoneNumber;
    @BindView(R.id.activity_detail_facebook_test)
    TextView mNeighbourFacebook;
    @BindView(R.id.activity_detail_aboutMy_tittle) TextView mAboutMyTittle;
    @BindView(R.id.activity_detail_aboutMy_test) TextView mAboutMyText;
    @BindView(R.id.activity_detail_favorite_btn)
    FloatingActionButton mFloatingActionButton;

    private Button mAddFavoriteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail_screen);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Neighbour neighbour = (Neighbour)intent.getSerializableExtra("KEY_NEIGHBOUR");
        mNeighbourName.setText(neighbour.getName());
        mNeighbourAddress.setText(neighbour.getAddress());
        mNeighbourPhoneNumber.setText(neighbour.getPhoneNumber());
        mNeighbourFacebook.setText("www.facebook.fr/" + neighbour.getName());
        mAboutMyText.setText(neighbour.getAboutMe());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mNeighbourAvatar);


        this.configureToolbar();


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new AddFavoriteEvent(neighbour));
            }
        });

    }

    private void configureToolbar(){
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }




}