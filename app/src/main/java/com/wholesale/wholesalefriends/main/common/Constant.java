package com.wholesale.wholesalefriends.main.common;

import android.os.Environment;

public class Constant {
    public static final String PICTURE_DIR =  "/data/data/" + "com.wholesale.wholesalefriend" + "/picture";
    public static final String PICTURE_DIR2 =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  "/picture";

    public static int PAGE_GO = 30;

    public interface CommonCategoryMenuName{
        String[] arrHomeMenu = {"티셔츠","바지류","상하세트","원피스","아우터","전체"};
        String[] arrHomeMenu1 = {"티셔츠","바지류","상하세트","원피스/치마","아우터","실내복","유아복","잡화","신발","계절용품","이월/세일","맘/대디"};
    }

    public interface CommonKey{
        String user_no = "user_no";
        String user_id = "user_id";
        String user_pwd = "user_pwd";
        String store_id="store_id";

        String category_code = "category_code";
        String category_name = "category_name";

        String product_id = "product_id";
        String product_name = "product_name";
        String intent_order_type = "intent_order_type";

        String intent_p_id = "intent_p_id";
        String intent_p_option_1  = "intent_p_option_1";
        String intent_p_option_2  = "intent_p_option_2";
        String intent_amount   = "intent_amount";

        String intent_c_id   = "intent_c_id";
    }
    public interface DOMAIN{
        String url = "http://app.asadalin.com/";
        String api = "api/";
    }

}
