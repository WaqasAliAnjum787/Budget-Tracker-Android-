package com.example.budgettracker;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.annotation.SuppressLint;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation mainNavigationBar;
    RecyclerView rvMain;
    TabLayout tabLayout;
    ViewPager viewPager;

    SliderView sliderView;
    SliderAdapterExample sliderAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mainNavigationBar=findViewById(R.id.navigationBar);
       rvMain=findViewById(R.id.rvMain);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        sliderView=findViewById(R.id.sliderView);

        //it is used to hide the status bar (if u want)

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //We are doing so that when our app starts then it will be at 1 position always
        mainNavigationBar.show(1,true);

        TabPagerAdapter tabPagerAdapter=new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        //Slider View Implimentation
        sliderAdapter=new SliderAdapterExample(sliderData());
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        //set indicator animation by using SliderLayout.IndicatorAnimations.
        // :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(5);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mainNavigationBar.show(1,true);
                        break;
                    case 1:
                        mainNavigationBar.show(2,true);
                        break;
                    case 2:
                        mainNavigationBar.show(3,true);
                        break;
                    case 3:
                        mainNavigationBar.show(4,true);
                        break;
                    case 4:
                        mainNavigationBar.show(5,true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mainNavigationBar.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        mainNavigationBar.add(new MeowBottomNavigation.Model(2,R.drawable.pie));
        mainNavigationBar.add(new MeowBottomNavigation.Model(3,R.drawable.profile));
        mainNavigationBar.add(new MeowBottomNavigation.Model(4,R.drawable.how_to_use));
        mainNavigationBar.add(new MeowBottomNavigation.Model(5,R.drawable.whats_new));


        mainNavigationBar.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                if (model.getId()==1){
                    //it will land it on the second tab/fragment
                   viewPager.setCurrentItem(0);
                }
                else {
                    if (model.getId()==2){
                        viewPager.setCurrentItem(1);
                    }
                    else {
                        if (model.getId()==3){
                            viewPager.setCurrentItem(2);
                        }
                        else {
                            if (model.getId()==4){
                                viewPager.setCurrentItem(3);
                            }
                            else {
                                if (model.getId()==5){
                                    viewPager.setCurrentItem(4);
                                }
                            }
                        }
                    }
                }
                return null;
            }
        });
    }

    public  List<MainModelClass> gatData() {
        List<MainModelClass> list=new ArrayList<>();
        list.add(new MainModelClass(R.drawable.food1,"Food"));
        list.add(new MainModelClass(R.drawable.hostel1,"Hostel"));
        list.add(new MainModelClass(R.drawable.educational1,"Educational"));
        list.add(new MainModelClass(R.drawable.medical1,"Medical Health"));
        list.add(new MainModelClass(R.drawable.personal1,"Personal"));
        list.add(new MainModelClass(R.drawable.bills1,"Bills"));
        list.add(new MainModelClass(R.drawable.event1,"Events"));
        list.add(new MainModelClass(R.drawable.others2,"Others"));
        return list;
    }

    public List<SliderItem> sliderData(){
        List<SliderItem> sliderItems=new ArrayList<>();
        sliderItems.add(new SliderItem("","https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        sliderItems.add(new SliderItem("","https://images.unsplash.com/photo-1460925895917-afdab827c52f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=815&q=80"));
        sliderItems.add(new SliderItem("","https://images.unsplash.com/photo-1534951009808-766178b47a4f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        sliderItems.add(new SliderItem("","https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        sliderItems.add(new SliderItem("","https://images.unsplash.com/photo-1488190211105-8b0e65b80b4e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        sliderItems.add(new SliderItem("","https://images.unsplash.com/photo-1624953901718-e24ee7200b85?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        sliderItems.add(new SliderItem("","https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=876&q=80"));
        sliderItems.add(new SliderItem("","https://images.unsplash.com/photo-1634474588707-de99f09285c0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        return sliderItems;
    }
}





