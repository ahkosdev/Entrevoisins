package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NeighbourDetailScreenActivity extends AppCompatActivity{


    @BindView(R.id.activity_detail_avatar_img)
    ImageView mNeighbourAvatar;
    @BindView(R.id.activity_detail_name_txt)
    TextView mNeighbourName;
    @BindView(R.id.activity_detail_address_txt)
    TextView mNeighbourAddress;
    @BindView(R.id.activity_detail_phone_txt)
    TextView mNeighbourPhoneNumber;
    @BindView(R.id.activity_detail_facebook_txt)
    TextView mNeighbourFacebook;
    @BindView(R.id.activity_detail_aboutMe_title) TextView mAboutMeTitle;
    @BindView(R.id.activity_detail_aboutMe_txt) TextView mAboutMeText;
    @BindView(R.id.activity_detail_favorite_btn)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.neighbour_detail_avatar_name_txt) TextView mAvatarName;
    @BindView(R.id.neighbour_detail_buttonUp_img) ImageView mButtonUp;

    private NeighbourApiService mApiService;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail_screen);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Neighbour neighbour = (Neighbour)intent.getSerializableExtra("KEY_NEIGHBOUR");
        mNeighbourName.setText(neighbour.getName());
        mAvatarName.setText(neighbour.getName());
        mNeighbourAddress.setText(neighbour.getAddress());
        mNeighbourPhoneNumber.setText(neighbour.getPhoneNumber());
        mNeighbourFacebook.setText("www.facebook.fr/" + neighbour.getName());
        mAboutMeText.setText(neighbour.getAboutMe());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mNeighbourAvatar);
        mApiService = DI.getNeighbourApiService();






        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mApiService.getFavorite().contains(neighbour)) {


                    mApiService.createFavorite(neighbour);
                }
            }
        });



        mButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }




}