package com.kemp.kemplibrary.utils;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

/**
 * Fragment Manager Transaction
 * Created by wangkp on 2018/3/2.
 */

public final class FragmentMT {

    private FragmentManager fm;

    private @IdRes
    int contentId;

    private SparseArray<Fragment> fragments;

    private int index = 0;

    private int currentIndex = 0;

    /**
     * 是否预加载
     */
    private boolean preloading = false;

    /**
     * 预加载fragment个数,最多预加载5个
     */
    private int preNum = 2;

    public FragmentMT(FragmentActivity activity, @IdRes int contentId) {
        this(activity, contentId, false);
    }

    public FragmentMT(FragmentActivity activity, @IdRes int contentId, boolean preloading) {
        this(activity, contentId, preloading, 2);
    }

    public FragmentMT(FragmentActivity activity, @IdRes int contentId, boolean preloading, int preNum) {
        this.contentId = contentId;
        this.preloading = preloading;
        this.preNum = preNum;
        if (preNum > 5) {
            this.preNum = 5;
        }

        fm = activity.getSupportFragmentManager();

        fragments = new SparseArray<>();
    }

    public void add(Fragment fragment) {
        fragments.put(index++, fragment);
    }

    public void add(Fragment... fragments) {
        for (Fragment fragment : fragments) {
            add(fragment);
        }
    }

    /**
     * add后需要调用否则没有预加载效果
     * <p>
     * 默认显示第一个，其余的统统隐藏
     */
    public void commit() {
        if (preloading) {
            FragmentTransaction ft = fm.beginTransaction();
            for (int i = 0; i < fragments.size() && i < preNum; i++) {
                Fragment f = fragments.get(i);
                ft.add(contentId, f);
                if (i > 0) {
                    ft.hide(f);
                }
            }
            ft.commit();
        }
    }

    /**
     * 切换fragment
     *
     * @param index fragment'index
     */
    public void toggle(int index) {
        Fragment preFragment = fragments.get(currentIndex);
        FragmentTransaction ft = fm.beginTransaction();

        if (preFragment != null && preFragment.isAdded()) {
            ft.hide(preFragment);
        }

        Fragment fragment = fragments.get(index);
        if (fragment.isAdded()) {
            ft.show(fragment);
        } else {
            ft.add(contentId, fragment);
        }
        ft.commit();

        currentIndex = index;
    }

    public void toggleSimple(int index) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = fragments.get(index);
        ft.replace(contentId, fragment);
        ft.commit();

        currentIndex = index;
    }
}
