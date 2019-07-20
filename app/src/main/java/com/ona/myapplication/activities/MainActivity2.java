package com.ona.myapplication.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;
import com.ona.myapplication.R;
import com.ona.myapplication.network.CheckInternetConnection;
import com.webianks.easy_feedback.EasyFeedback;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class MainActivity2 extends AppCompatActivity {

    private SliderLayout sliderShow;
    private Drawer result;
    private String name, email;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;
    private HashMap<String, String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        TextView appname = findViewById(R.id.appname);
        appname.setTypeface(typeface);

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();

        //Navigation Drawer with toolbar
        inflateNavDrawer();

        //ImageSLider
        inflateImageSlider();

        //display tapview
        tapview();

    }

    private void tapview() {

            new TapTargetSequence(this)
                    .targets(
                            TapTarget.forView(findViewById(R.id.ngroreport), "NGO Food Cash report", "SMP_Monthly_Cash_Food_Report_NGOs_July_2019 link  !")
                                    .targetCircleColor(R.color.colorAccent)
                                    .titleTextColor(R.color.colorAccent)
                                    .titleTextSize(25)
                                    .descriptionTextSize(15)
                                    .descriptionTextColor(R.color.accent)
                                    .drawShadow(true)                   // Whether to draw a drop shadow or not
                                    .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                    .tintTarget(true)
                                    .transparentTarget(true)
                                    .outerCircleColor(R.color.first),
                            TapTarget.forView(findViewById(R.id.guava), "Kitui Guava Survey", "Kitui_Guava_Baseline_survey_1 July 2019!")
                                    .targetCircleColor(R.color.colorAccent)
                                    .titleTextColor(R.color.colorAccent)
                                    .titleTextSize(25)
                                    .descriptionTextSize(15)
                                    .descriptionTextColor(R.color.accent)
                                    .drawShadow(true)                   // Whether to draw a drop shadow or not
                                    .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                    .tintTarget(true)
                                    .transparentTarget(true)
                                    .outerCircleColor(R.color.third),
                            TapTarget.forView(findViewById(R.id.nutrition), "Nutrition Survey", "This is a nutrition survey conducted from several households")
                                    .targetCircleColor(R.color.colorAccent)
                                    .titleTextColor(R.color.colorAccent)
                                    .titleTextSize(25)
                                    .descriptionTextSize(15)
                                    .descriptionTextColor(R.color.accent)
                                    .drawShadow(true)
                                    .cancelable(false)// Whether tapping outside the outer circle dismisses the view
                                    .tintTarget(true)
                                    .transparentTarget(true)
                                    .outerCircleColor(R.color.second),
                            TapTarget.forView(findViewById(R.id.karamoja), "Karamoja Crop Survey", "Karamoja_in_season_crop_monitoring_2019_july")
                                    .targetCircleColor(R.color.colorAccent)
                                    .titleTextColor(R.color.colorAccent)
                                    .titleTextSize(25)
                                    .descriptionTextSize(15)
                                    .descriptionTextColor(R.color.accent)
                                    .drawShadow(true)
                                    .cancelable(false)// Whether tapping outside the outer circle dismisses the view
                                    .tintTarget(true)
                                    .transparentTarget(true)
                                    .outerCircleColor(R.color.fourth))
                    .listener(new TapTargetSequence.Listener() {
                        // This listener will tell us when interesting(tm) events happen in regards
                        // to the sequence
                        @Override
                        public void onSequenceFinish() {
                          Toasty.success(MainActivity2.this, " You are ready to go !", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                        }

                        @Override
                        public void onSequenceCanceled(TapTarget lastTarget) {

                        }
                    }).start();

    }

    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = findViewById(R.id.slider);

        //populating Image slider
        ArrayList<String> sliderImages = new ArrayList<>();
        sliderImages.add("https://storage.googleapis.com/sporting-4496c.appspot.com/sliderimages/jy8qe5jdi0fkae8or6o/image1/onaio.png");
        sliderImages.add("https://storage.googleapis.com/sporting-4496c.appspot.com/sliderimages/jy8qe5jdi0fkae8or6o/image2/ona2.png");
        sliderImages.add("https://storage.googleapis.com/sporting-4496c.appspot.com/sliderimages/jy8qe5jdi0fkae8or6o/image3/ona3.png");

        for (String s : sliderImages) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.image(s);
            sliderShow.addSlider(sliderView);
        }

        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
    }

    private void inflateNavDrawer() {
        //set Custom toolbar to activity -----------------------------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the AccountHeader ----------------------------------------------------------------
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.gradient_background)
                 .withCompactStyle(true)
                .build();

        //Adding nav drawer items ------------------------------------------------------------------
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.home).withIcon(R.drawable.home);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.myprofile).withIcon(R.drawable.profile);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName(R.string.aboutapp).withIcon(R.drawable.credits);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(4).withName(R.string.feedback).withIcon(R.drawable.feedback);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(5).withName(R.string.helpcentre).withIcon(R.drawable.helpccenter);
        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withIdentifier(6).withName("App Tour").withIcon(R.drawable.explore);

        //creating navbar and adding to the toolbar ------------------------------------------------
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withDrawerLayout(R.layout.crossfade_drawer)
                .withAccountHeader(headerResult)
                .withDrawerWidthDp(72)
                .withGenerateMiniDrawer(true)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        item1, item2, item3, item4, item5, item6
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {

                    switch (position) {

                        case 1:
                            if (result != null && result.isDrawerOpen()) {
                                result.closeDrawer();
                            }
                            break;
                        case 2:
                            Snackbar.make(findViewById(android.R.id.content), "Feature Not Available", Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.RED)
                            .show();
                            break;
                        case 3:
                            Snackbar.make(findViewById(android.R.id.content), "Feature Not Available", Snackbar.LENGTH_LONG)
                                    .setActionTextColor(Color.RED)
                                    .show();
                            break;
                        case 4:

                            new EasyFeedback.Builder(MainActivity2.this)
                                    .withEmail("amuribonface@gmail.com.com")
                                    .withSystemInfo()
                                    .build()
                                    .start();
                            break;

                        case 5:
                            Snackbar.make(findViewById(android.R.id.content), "Feature Not Available", Snackbar.LENGTH_LONG)
                                    .setActionTextColor(Color.RED)
                                    .show();

                            break;

                        case 6:
                            if (result != null && result.isDrawerOpen()) {
                                result.closeDrawer();
                            }
                            tapview();
                            break;
                        default:
                            Toast.makeText(MainActivity2.this, "Default", Toast.LENGTH_LONG).show();

                    }

                    return true;
                })
                .build();

        //Setting crossfader drawer------------------------------------------------------------

        crossfadeDrawerLayout = (CrossfadeDrawerLayout) result.getDrawerLayout();

        //define maxDrawerWidth
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(this));

        //add second view (which is the miniDrawer)
        final MiniDrawer miniResult = result.getMiniDrawer();

        //build the view for the MiniDrawer
        View view = miniResult.build(this);

        //set the background of the MiniDrawer as this would be transparent
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(this, com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.material_drawer_background));

        //we do not have the MiniDrawer view during CrossfadeDrawerLayout creation so we will add it here
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    result.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });
    }
    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void contactAdmin(View view) {
        String number="+254717264871";
        String url = "https://api.whatsapp.com/send?phone="+number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onResume() {
        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();
        sliderShow.startAutoCycle();
        super.onResume();
    }


    public void kituiGuava(View view) { startActivity(new Intent(MainActivity2.this, KituiGuavaActivity.class));
    }

    public void ngoFood(View view) { startActivity(new Intent(MainActivity2.this, NGOreportActivity.class));
    }

    public void nutritionSurvey(View view) { startActivity(new Intent(MainActivity2.this, NutritionAnalysis.class));
    }

    public void karamojaCrop(View view) { startActivity(new Intent(MainActivity2.this, KaraMojaActivity.class));
    }

    public void ebolaSurvey(View view) {
       // startActivity(new Intent(MainActivity2.this, KituiGuavaActivity.class));
    }
    public void rwandaSurvey(View view) {
       // startActivity(new Intent(MainActivity2.this, KituiGuavaActivity.class));
    }

}