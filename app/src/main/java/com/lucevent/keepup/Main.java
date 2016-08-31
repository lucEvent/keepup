package com.lucevent.keepup;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.lucevent.keepup.data.util.Sport;
import com.lucevent.keepup.kernel.AppData;
import com.lucevent.keepup.kernel.SportsManager;
import com.lucevent.keepup.view.fragment.AboutFragment;
import com.lucevent.keepup.view.fragment.AppSettingsFragment;
import com.lucevent.keepup.view.fragment.SportFragment;
import com.lucevent.keepup.view.fragment.StatisticsFragment;

import java.lang.ref.WeakReference;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SportsManager dataManager;
    public Handler handler;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handler = new Handler(this);
        AppSettings.initialize(this, handler);
        dataManager = new SportsManager(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null : "Navigation view is null";
        navigationView.setNavigationItemSelectedListener(this);

        currentFragment = com.lucevent.keepup.view.fragment.SportFragment.instanceFor(AppData.sports.get(0).code);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, currentFragment)
                .commit();

        updateDrawer();
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else super.onBackPressed();
    }

    private void updateDrawer()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null : "Navigation view is null";

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            navigationView.setItemIconTintList(null);

        MenuItem stats = navigationView.getMenu().findItem(R.id.nav_stats);
        if (ProSettings.areStatisticsEnabled())
            stats.setVisible(true);
        else
            stats.setVisible(false);

        SubMenu sites_menu = navigationView.getMenu().findItem(R.id.nav_header_sports).getSubMenu();
        sites_menu.clear();

        for (Sport sport : AppData.sports) {
            MenuItem mi = sites_menu.add(3, sport.code, Menu.NONE, sport.name);
            configureMenuItem(mi, sport);
        }
        navigationView.invalidate();
    }

    private void configureMenuItem(MenuItem mi, Sport sport)
    {
        int icon_res_id = R.drawable.ic_sports;
        //       switch ()
// TODO: 10/06/2016
        mi.setIcon(icon_res_id);
        mi.setCheckable(true);
    }

    private Fragment currentFragment, previousFragment;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment fragment;
        String title;

        switch (item.getItemId()) {
            case R.id.nav_stats:
                fragment = new StatisticsFragment();
                title = getString(R.string.statistics);
                break;
            case R.id.nav_settings:
                fragment = new AppSettingsFragment();
                title = getString(R.string.settings);
                break;
            case R.id.nav_about:
                fragment = new AboutFragment();
                title = getString(R.string.about);
                break;
            default:
                Sport sport = AppData.getSportByCode(item.getItemId());
                fragment = SportFragment.instanceFor(sport.code);
                title = sport.name;
        }
        drawer.closeDrawer(GravityCompat.START);

        previousFragment = currentFragment;
        currentFragment = fragment;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();

        setTitle(title);

        return true;
    }

    static class Handler extends android.os.Handler {

        private final WeakReference<Main> context;

        Handler(Main context)
        {
            this.context = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg)
        {
            Main service = context.get();
            switch (msg.what) {
                default:
                    AppSettings.printerror("[SPORTSMAIN] OPTION UNKNOWN: " + msg.what, null);
            }
        }

    }

}
