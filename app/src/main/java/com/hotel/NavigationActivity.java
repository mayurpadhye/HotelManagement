package com.hotel;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class NavigationActivity extends AppCompatActivity
        implements HomeFragment.OnFragmentInteractionListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    private Handler mHandler;

    public static int navItemIndex = 0;
    private static final String TAG_HOME = "home";
    private static final String TAG_MY_PROFILE= "my_profile";
    private static final String TAG_FAMILY_CIRCLE= "my_family_circle";
    private static final String TAG_CART = "my_cart";
    private static final String TAG_HISTORY = "my_history";
    private static final String TAG_SETTINGS = "my_settings";
    private static final String TAG_NOTIFICATION = "notification";
    private static final String TAG_MYNULA_FEATURES = "mynula_feature";
    private static final String TAG_LOGOUT = "mynula_logout";
    public static String CURRENT_TAG = TAG_HOME;
    Toolbar toolbar;
    private String[] activityTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
         toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHandler = new Handler();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         drawer = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

      //  navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")


    private void loadHomeFragment() {
        selectNavMenu();

        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments


                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.content_main_frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();


            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        drawer.closeDrawers();

        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                return new HomeFragment();

           /* case 1:
                return new MyProfileFragment();*/
           /* case 2:
                return new MyFamilyCircleFragment();
            case 3:
                return new MyCartFragment();
            case 4:
                return new MyHistoryFragment();
            case 5:
                return new MySettingsFragment();
            case 6:
                return new NotificationFragment();
            case 7:
                return new AboutMynulaFragment();
            case 8:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NavigationDrawerActivity.this);
                alertDialog.setMessage("Do you want to logout from the application?");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
//                                   Toast.makeText(getApplicationContext(), "An appointment SMS request has been sent to the businessman. Please wait for the confirmation.", Toast.LENGTH_LONG).show();
                        logout(user_id);
                        dialog.cancel();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                                   alertDialog.setMessage(message);
//                                   Toast.makeText(getApplicationContext(), "Appointment Cancelled", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
                alertDialog.setCancelable(false);
                alertDialog.show();*/

//                logout(user_id);
            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }


    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;

                        break;
                   /* case R.id.nav_my_profile:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_MY_PROFILE;
                        break;
                    case R.id.nav_family_circle:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_FAMILY_CIRCLE;
                        break;
                    case R.id.nav_cart:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_CART;
                        break;
                    case R.id.nav_history:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_HISTORY;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_notification:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_NOTIFICATION;
                        break;
                    case R.id.nav_mynula_features:
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_MYNULA_FEATURES;
                        break;
                    case R.id.nav_logout:
                        navItemIndex = 8;
                        CURRENT_TAG = TAG_LOGOUT;
                        break;*/
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

   /* @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}
