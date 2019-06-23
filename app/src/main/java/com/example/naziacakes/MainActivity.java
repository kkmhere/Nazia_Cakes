package com.example.naziacakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.mytoolbar);
        if(toolbar!=null)
        {
            setSupportActionBar(toolbar);
        }

        final DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //Toast.makeText(MainActivity.this,"OPENED",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
               // Toast.makeText(MainActivity.this,"CLOSED",Toast.LENGTH_SHORT).show();
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView=(NavigationView)findViewById(R.id.myNavigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId())
                {
                    case R.id.myorders:
                        Toast.makeText(MainActivity.this,"Orders",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.myaddr:
                        Toast.makeText(MainActivity.this,"Addresses",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.editProfile:
                        Toast.makeText(MainActivity.this,"EDITING",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.trackOrder:
                        Toast.makeText(MainActivity.this,"TRACK ORDER",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.mwallet:
                        Toast.makeText(MainActivity.this,"MY WALLET",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.logout:
                        Toast.makeText(MainActivity.this,"LOGOUT",Toast.LENGTH_SHORT).show();
                }


                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sidemenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.cart:
                Toast.makeText(MainActivity.this,"CART OPENING",Toast.LENGTH_SHORT).show();
                break;

            case R.id.profile:
                Toast.makeText(MainActivity.this,"PROFILE OPENING",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
