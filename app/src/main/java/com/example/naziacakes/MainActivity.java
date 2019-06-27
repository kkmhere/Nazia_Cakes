package com.example.naziacakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAapter;
    private List<Cakes> cakesList;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser,currentUserMain;

    TextView headername;
    String headerName;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
         currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(MainActivity.this,currentUser.getUid(),Toast.LENGTH_SHORT).show();


        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        currentUserMain=mAuth.getCurrentUser();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        cakesList=new ArrayList<>();

        for(int index=0;index<20;index++)
        {
            Cakes mycakes=new Cakes("KESHAV"+index,"PILOT");
            cakesList.add(mycakes);

        }

        recyclerViewAapter =new recyclerViewAdapter(cakesList,MainActivity.this);
        recyclerView.setAdapter(recyclerViewAapter);

        FragmentTransaction fragmentTransaction;

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
                        //Toast.makeText(MainActivity.this,"Orders",Toast.LENGTH_SHORT).show();
                        myFragmentClass myFragmentClass=new myFragmentClass();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container1,myFragmentClass);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        recyclerView.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.myaddr:
                        //Toast.makeText(MainActivity.this,"Addresses",Toast.LENGTH_SHORT).show();
                        addrFragment addrFragment=new addrFragment();
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container1,addrFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        recyclerView.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.editProfile:
                        //Toast.makeText(MainActivity.this,"EDITING",Toast.LENGTH_SHORT).show();
                        editFragment editFragment=new editFragment();
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container1,editFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        recyclerView.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.trackOrder:
                        //Toast.makeText(MainActivity.this,"TRACK ORDER",Toast.LENGTH_SHORT).show();
                        trackFragment trackFragment=new trackFragment();
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container1,trackFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        recyclerView.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.mwallet:
                        //Toast.makeText(MainActivity.this,"MY WALLET",Toast.LENGTH_SHORT).show();
                        walletFragment walletFragment=new walletFragment();
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container1,walletFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        recyclerView.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.logout:
                        //Toast.makeText(MainActivity.this,"LOGOUT",Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        Toast.makeText(MainActivity.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.home:
                        recyclerView.setVisibility(View.VISIBLE);
                        Intent intent2=new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent2);
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
