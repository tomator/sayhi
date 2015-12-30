package xyz.iseeyou.sayhi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import xyz.iseeyou.sayhi.R;


/**
 * Created by bici on 15/12/10.
 */
public class AccountInputView extends LinearLayout {
    private int iconBg;
    private String textHint;
    private int inputType;

    private ImageView imageView;
    private EditText editText;

    public AccountInputView(Context context) {
        this(context, null);
    }

    public AccountInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AccountInputView, defStyleAttr, 0);
        iconBg = typedArray.getResourceId(R.styleable.AccountInputView_iconBg, 0);
        textHint = typedArray.getString(R.styleable.AccountInputView_textHint);
        inputType = typedArray.getInt(R.styleable.AccountInputView_android_inputType, EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.account_input_item, this);
        imageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editText);
        imageView.setBackgroundResource(iconBg);
        editText.setHint(textHint);
        editText.setHintTextColor(Color.parseColor("#bdbdbd"));
        editText.setInputType(inputType);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int size = editText.getText().length();
                if (size > 0) {
                    imageView.setEnabled(false);
                } else {
                    imageView.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setText(int stringId) {
        editText.setText(stringId);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public EditText getEditText() {
        return editText;
    }
}
