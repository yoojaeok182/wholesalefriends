package com.wholesale.wholesalefriends.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.dialog.CommonAlertDialog;

public class HomeFragment extends Fragment {

    private EditText tvCategorySearch;
    private ImageButton btnManagerment;
    private RecyclerView horRecyclerView;
    private LinearLayout btnAutoRecommAdd;
    private TextView btnHidden;
    private TextView tvTobMenu01;
    private LinearLayout llayoutTopMenu01;
    private TextView tvTobMenu02;
    private LinearLayout llayoutTopMenu02;
    private LinearLayout llayoutTopLine01;
    private LinearLayout llayoutTopLine02;
    private TextView tvSearchItem;
    private LinearLayout btnProcuctManage;
    private LinearLayout btnFilter;
    private RecyclerView recyclerView;
    private AppBarLayout appBarLayout;
    private ImageView ivSearch;
    private String search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvCategorySearch = view.findViewById(R.id.tvCategorySearch);
        btnManagerment = view.findViewById(R.id.btnManagerment);
        horRecyclerView = view.findViewById(R.id.horRecyclerView);
        btnAutoRecommAdd = view.findViewById(R.id.btnAutoRecommAdd);
        btnHidden = view.findViewById(R.id.btnHidden);
        tvTobMenu01 = view.findViewById(R.id.tvTobMenu01);
        llayoutTopMenu01 = view.findViewById(R.id.llayoutTopMenu01);
        tvTobMenu02 = view.findViewById(R.id.tvTobMenu02);
        llayoutTopMenu02 = view.findViewById(R.id.llayoutTopMenu02);
        llayoutTopLine01 = view.findViewById(R.id.llayoutTopLine01);
        llayoutTopLine02 = view.findViewById(R.id.llayoutTopLine02);
        tvSearchItem = view.findViewById(R.id.tvSearchItem);
        btnProcuctManage = view.findViewById(R.id.btnProcuctManage);
        btnFilter = view.findViewById(R.id.btnFilter);
        recyclerView = view.findViewById(R.id.recyclerView);
        appBarLayout =  view.findViewById(R.id.appBarLayout);
        ivSearch = view.findViewById(R.id.ivSearch);
        init();
        return view;
    }

    private void init(){
        btnHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                final CommonAlertDialog dg = new CommonAlertDialog(getActivity(),true,true);
                dg.setTitle("우리매장 TOP30자동등록");
                dg.setMessage("숨기기를 하면 이미 등록된 우리매장 TOP30 상품이 비워집니다.\n\n\n\n" +
                        "* 우리매장 TOP 30을 다시 사용하시려면 아래 매장TOP 버튼을 이용해 등록해주시면 됩니다.\n\n\n\n" +
                        "* 추천상품 자동등록 버튼으로 우리매장 TOP30을 편하게 관리해보세요.");
                dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(dg.isOk()){

                        }
                    }
                });
                dg.show();
            }
        });
        btnAutoRecommAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                final CommonAlertDialog dg = new CommonAlertDialog(getActivity(),true,true);
                dg.setTitle("우리매장 TOP30 자동등록");
                dg.setMessage("자동등록은 우리매장의 인기상품을 일괄 등록하는 기능입니다.\n\n\n\n*기존 등록된 우리매장 TOP30이 모두 삭제되니 주의해주세요!\n\n\n" +
                        "\n" +
                        "*자동등록된 우리매장 TOP30은 절대적인 순위가 아닙니다. 따라서 매장에서 밀고 싶은 상품을 30개까지 추가 등록하여 사용하는 것을 권장합니다.");
                dg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if(dg.isOk()){

                        }
                    }
                });
                dg.show();
            }
        });
        btnManagerment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvCategorySearch.getText() != null && tvCategorySearch.getText().toString() != null && tvCategorySearch.getText().toString().length() > 0) {
                    search = tvCategorySearch.getText().toString();
                } else {
                    search = null;
                }
               /* getCelebList(true);
              */
                hideKeyboardFrom(getActivity(), tvCategorySearch);
            }
        });

        tvCategorySearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (tvCategorySearch.getText() != null && tvCategorySearch.getText().toString() != null && tvCategorySearch.getText().toString().length() > 0) {
                        search = tvCategorySearch.getText().toString();
                    } else {
                        search = null;
                    }
                    hideKeyboardFrom(getActivity(), tvCategorySearch);
                }
                return false;
            }
        });

        tvCategorySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
