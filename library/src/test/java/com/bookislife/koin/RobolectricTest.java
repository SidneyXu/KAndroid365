package com.bookislife.koin;

import android.text.format.DateUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;

import static android.text.format.DateUtils.FORMAT_NUMERIC_DATE;
import static android.text.format.DateUtils.FORMAT_SHOW_DATE;
import static android.text.format.DateUtils.FORMAT_SHOW_YEAR;
import static android.text.format.DateUtils.MINUTE_IN_MILLIS;

/**
 * Created by SidneyXu on 2016/05/20.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, manifest = Config.NONE)
public class RobolectricTest {

    @Test
    public void test01() throws Exception {
        Date date = new Date(System.currentTimeMillis()-1000_1000);
        long now = System.currentTimeMillis();
        String result = DateUtils.getRelativeTimeSpanString(date.getTime(), now,
                MINUTE_IN_MILLIS, FORMAT_SHOW_DATE | FORMAT_SHOW_YEAR
                        | FORMAT_NUMERIC_DATE).toString();
        System.out.println(result);

        System.out.println(DateUtils.getRelativeTimeSpanString(date.getTime()));
    }

    @Test
    public void test02() throws Exception {
    }
}
