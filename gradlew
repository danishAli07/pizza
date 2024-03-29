<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:id="@+id/main_ll"
    android:orientation="vertical"
    android:elevation="22dp"
    android:background="@drawable/bg_simple_transparent"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_marginTop="@dimen/margin_top"
    android:layout_marginStart="@dimen/margin_top"
    android:layout_marginEnd="@dimen/margin_top"
    app:cardCornerRadius="4dp"
    android:padding="8dp"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <ImageView
        android:id="@+id/iv_match_pic"
        android:scaleType="fitXY"
        android:src="@drawable/ebg_logo"
        android:layout_width="match_parent"
        android:layout_height="160dp"
        android:visibility="gone"/>
            <LinearLayout
                android:orientation="horizontal"
                android:layout_width="match_parent"
                android:layout_height="70dp"
                tools:ignore="NestedWeights">
                <android.support.v7.widget.CardView
                    android:layout_margin="4dp"
                    android:layout_width="60dp"
                    android:layout_height="60dp"
                    app:cardCornerRadius="30dp">
                    <ImageView
                        android:id="@+id/iv_matchJoin_icon"
                        android:layout_alignParentTop="true"
                        android:scaleType="fitXY"
                        android:src="@drawable/ebg_logo"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent" />
                </android.support.v7.widget.CardView>
                <LinearLayout
                    android:layout_margin="8dp"
                    android:layout_width="0dp"
                    android:layout_weight="1"
                    android:layout_height="match_parent"
                    android:orientation="vertical">
                <TextView
                    android:id="@+id/tv_matchJoin_gameName"
                    android:text="Evening Quad"
                    android:textStyle="bold"
                    android:textColor="@color/golden"
                    android:textSize="16sp"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    />
                <TextView
                    android:layout_alignParentEnd="true"
                    android:text="@string/room_and_pass"
                    android:textStyle="bold"
                    android:textColor="@color/white"
                    android:fontFamily="serif-monospace"
                    android:textSize="10sp"
                  android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    tools:ignore="SmallSp" />

                    <LinearLayout
                        android:layout_marginTop="4dp"
                        android:orientation="horizontal"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content">
                <TextView
                    android:id="@+id/tv_matchJoin_date"
                    android:textColor="@color/grey"
                    android:text="Date : 20 May 2019"
                    android:textSize="10sp"
                    android:layout_width="wrap_content"
                    android:layout_weight="1"
                    android:layout_height="wrap_content"
                    tools:ignore="SmallSp" />
                <TextView
                    android:id="@+id/tv_matchJoin_time"
                    android:textColor="@color/grey"
                    android:text="Time : 3:00 PM"
                    android:textSize="10sp"
                    android:layout_width="wrap_content"
                    android:layout_weight="1"
                    android:layout_height="wrap_content"
                    tools:ignore="SmallSp" />
                    </LinearLayout>
                </LinearLayout>
            </LinearLayout>
            <LinearLayout

                android:orientation="vertical"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

                    <LinearLayout
                        android:layout_marginTop="8dp"
                        android:gravity="center"
                        android:id="@+id/ll_price_laundryWash"
                        android:orientation="horizontal"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content">
                        <TextView
                            android:id="@+id/tv_matchJoin_prizeLabel"
                            android:text="1st PRIZE"
                            style="@style/tv_main" />

                        <TextView
                            android:id="@+id/tv_matchJoin_killLabel"
                            android:text="PER KILL"
                    