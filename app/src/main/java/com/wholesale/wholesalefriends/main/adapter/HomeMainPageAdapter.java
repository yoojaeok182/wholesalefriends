package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.wholesale.wholesalefriends.main.fragment.HomeMainViewPager01Fragment;
import com.wholesale.wholesalefriends.main.fragment.HomeMainViewPager02Fragment;
import com.wholesale.wholesalefriends.main.fragment.HomeMainViewPager03Fragment;

public class HomeMainPageAdapter extends FragmentStatePagerAdapter {
    private Context context;

    private final int MAX_COUNT = 3;
    public HomeMainPageAdapter(FragmentManager fm,Context ctx) {
        super(fm);
        context = ctx;
    }
    @Override
    public int getItemPosition(Object object) {

        if(object instanceof HomeMainViewPager01Fragment){
        }else  if(object instanceof HomeMainViewPager02Fragment){
        }else  if(object instanceof HomeMainViewPager03Fragment){
//            ((HomeMainViewPager03Fragment) object).update();
        }
        return super.getItemPosition(object);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HomeMainViewPager01Fragment.newInstance(context);
            case 1:
                return HomeMainViewPager02Fragment.newInstance(context);
            case 2:
                return HomeMainViewPager03Fragment.newInstance(context);


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return MAX_COUNT;
    }
}
