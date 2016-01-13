package xyz.iseeyou.sayhi.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import xyz.iseeyou.sayhi.R;
import xyz.iseeyou.sayhi.bean.User;
import xyz.iseeyou.sayhi.config.BmobConstants;
import xyz.iseeyou.sayhi.config.Constants;
import xyz.iseeyou.sayhi.util.DateUtil;
import xyz.iseeyou.sayhi.util.DensityUtil;
import xyz.iseeyou.sayhi.util.Log;
import xyz.iseeyou.sayhi.view.BiciAlertDialogBuilder;

public class ProfileEdtiActivity extends ActivityBase implements AdapterView.OnItemClickListener {

    @InjectView(R.id.gridView)
    GridView gridView;
    @InjectView(R.id.nicknameHint)
    TextView nicknameHint;
    @InjectView(R.id.nicknameEdit)
    EditText nicknameEdit;
    @InjectView(R.id.descptionHint)
    TextView descptionHint;
    @InjectView(R.id.descptionEdit)
    EditText descptionEdit;
    @InjectView(R.id.genderView)
    TextView genderView;
    @InjectView(R.id.genderContainer)
    LinearLayout genderContainer;
    @InjectView(R.id.birthdayView)
    TextView birthdayView;
    @InjectView(R.id.birthdayContainer)
    LinearLayout birthdayContainer;
    public static final int NICKNAME_LIMIT = 10;
    public static final int DESC_LIMIT = 200;
    private static final String UPLOAD_IMAGE = "upload.jpg";
    private static final String TEMP_IMAGE = "photo_temp.jpg";

    private User user;
    private List<BmobFile> avatarList;
    private AvatarAdapter avatarAdapter;
    private BmobFile selectFile;
    public File imageFile = null;
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.drawable.avatar_team)
            .showImageOnLoading(R.drawable.avatar_team)
            .showImageOnFail(R.drawable.avatar_team)
            .cacheInMemory(true)
            .cacheOnDisc(true)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edti);
        ButterKnife.inject(this);

        user = userManager.getCurrentUser(User.class);
        avatarList = user.getAvatars();
        if (avatarList == null) {
            avatarList = new ArrayList<>();
            user.setAvatars(avatarList);
        }
        if (avatarList.size() == 0) {
            avatarList.add(new BmobFile());
        }
        avatarAdapter = new AvatarAdapter(this);
        gridView.setAdapter(avatarAdapter);
        gridView.setOnItemClickListener(this);
        initView();
    }

    private void initView() {
        if (!TextUtils.isEmpty(user.getNick())) {
            nicknameEdit.setHint(user.getNick() + "");
        }
        nicknameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int size = nicknameEdit.getText().length();
                nicknameHint.setText(size + "/" + NICKNAME_LIMIT);
                if (size > NICKNAME_LIMIT) {
                    nicknameHint.setTextColor(Color.parseColor("#e51c23"));
                } else {
                    nicknameHint.setTextColor(Color.parseColor("#9e9e9e"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        if (!TextUtils.isEmpty(user.getDescription())) {
            descptionEdit.setHint(user.getDescription() + "");
        }
        descptionEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int size = descptionEdit.getText().length();
                descptionHint.setText(size + "/" + DESC_LIMIT);
                if (size > DESC_LIMIT) {
                    descptionHint.setTextColor(Color.parseColor("#e51c23"));
                } else {
                    descptionHint.setTextColor(Color.parseColor("#9e9e9e"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        genderView.setText(user.getSex() != null && user.getSex() ? "男" : "女");
    }

    @OnClick(R.id.genderContainer)
    void genderClick() {
        if(user.getSex() == null){
            showGenderDialog();
        }else {
            ShowToast("性别不可以修改。");
        }
    }

    @OnClick(R.id.birthdayContainer)
    void onStartTimeClick() {
        long startTime = user.getBirthday();
        final Calendar calendar = Calendar.getInstance();
        if (startTime > 0) {
            calendar.setTimeInMillis(startTime);
        }
        showDateTimePick(calendar);
    }

    private void showDateTimePick(final Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        final DatePicker datePicker = new DatePicker(this);
        datePicker.updateDate(year, month, day);
        new BiciAlertDialogBuilder(this)
                .setView(datePicker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        calendar.set(datePicker.getYear(),
                                datePicker.getMonth(),
                                datePicker.getDayOfMonth());
                        long timeStamp = calendar.getTimeInMillis();
                        String detaStr = DateUtil.format(timeStamp, 1);
                        birthdayView.setText(detaStr);
                        user.setBirthday(timeStamp);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    private void showGenderDialog() {
        String[] strings = new String[]{"女", "男"};
        new BiciAlertDialogBuilder(this)
                .setTitle("请选择性别")
                .setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user.setSex(which != 0);
                    }
                }).show();
    }

    public class AvatarAdapter extends BaseAdapter {
        private Context mContext;
        private int width;

        public AvatarAdapter(Context c) {
            mContext = c;
            width = (NewMainActivity.point.x - DensityUtil.dp2px(80f)) / 4;
        }

        public int getCount() {
            return avatarList.size();
        }

        public Object getItem(int position) {
            return avatarList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.avatar_edit_item, null);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            imageView.setLayoutParams(layoutParams);
            BmobFile bmobFile = avatarList.get(position);
            Log.d("getView bmobFile url = " + bmobFile.getUrl()+" position = " + position);
            ImageLoader.getInstance()
                    .displayImage(Constants.BMOB_FILE_HEADER + bmobFile.getUrl(), imageView, options);
            return convertView;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final BmobFile bmobFile = avatarList.get(position);
        String[] items;
        if (!TextUtils.isEmpty(bmobFile.getUrl()) && position != 0) {
            items = new String[]{"照相机", "图库", "设为头像", "删除"};
        } else {
            items = new String[]{"照相机", "图库"};
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEdtiActivity.this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                        selectFile = bmobFile;
                        switch (which) {
                            case 0:
                                File dir = new File(BmobConstants.MyAvatarDir);
                                if (!dir.exists()) {
                                    dir.mkdirs();
                                }
                                imageFile = new File(BmobConstants.MyAvatarDir + TEMP_IMAGE);
                                Log.d("photo path = "+imageFile.getAbsolutePath());
                                Uri imageUri = Uri.fromFile(imageFile);
                                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                startActivityForResult(intent,
                                        BmobConstants.REQUESTCODE_UPLOADAVATAR_CAMERA);
                                break;
                            case 1:
                                intent = new Intent(Intent.ACTION_PICK, null);
                                intent.setDataAndType(
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                try {
                                    startActivityForResult(intent,
                                            BmobConstants.REQUESTCODE_UPLOADAVATAR_LOCATION);
                                } catch (Exception e) {
                                   ShowToast("找不到可以处理图片的应用。");
                                }
                                break;
                            case 2:
                                int location = avatarList.indexOf(bmobFile);
                                BmobFile first = avatarList.get(0);
                                avatarList.set(0, bmobFile);
                                avatarList.set(location, first);
                                avatarAdapter.notifyDataSetChanged();
                                break;
                            case 3:
                                avatarList.remove(bmobFile);
                                avatarAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult requestCode = "+requestCode+" resultCode = "+resultCode);
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case BmobConstants.REQUESTCODE_UPLOADAVATAR_CAMERA:// 拍照修改头像
                cropImageUri(Uri.fromFile(imageFile));
                break;
            case BmobConstants.REQUESTCODE_UPLOADAVATAR_LOCATION:// 本地修改头像
                cropImageUri(data.getData());
                break;
            case BmobConstants.REQUESTCODE_UPLOADAVATAR_CROP:// 裁剪头像返回
                if (data == null) {
                    ShowToast("已取消选择图片");
                    return;
                } else {
                    uploadAvatar();
                }
                break;
            default:
                break;
        }
    }

    protected void cropImageUri(Uri uri) {
        Uri outputUri = Uri.fromFile(new File(BmobConstants.MyAvatarDir + UPLOAD_IMAGE));
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 1080);
        intent.putExtra("outputY", 1080);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true); // 去黑边
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, BmobConstants.REQUESTCODE_UPLOADAVATAR_CROP);
    }

    private void uploadAvatar() {
        String uploadPath = BmobConstants.MyAvatarDir + UPLOAD_IMAGE;
        Log.d(getClass()+" 头像地址：" + uploadPath);
        final BmobFile bmobFile = new BmobFile(new File(uploadPath));
        bmobFile.upload(this, new UploadFileListener() {
            @Override
            public void onSuccess() {
                String url = bmobFile.getFileUrl(ProfileEdtiActivity.this);
                Log.d("uploadAvatar success url = " + url);
                ShowToast("图片上传成功");
                if (TextUtils.isEmpty(selectFile.getUrl()) && avatarList.size() < 8) {
                    avatarList.add(avatarList.size() - 1, bmobFile);
                } else {
                    avatarList.set(avatarList.indexOf(selectFile), bmobFile);
                }
                avatarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onProgress(Integer arg0) {
                // TODO Auto-generated method stub
                Log.d("uploadAvatar onProgress " + arg0);
            }

            @Override
            public void onFailure(int arg0, String msg) {
                ShowToast("图片上传失败：" + msg);
            }
        });
    }

    private boolean checkEmpty(){
        if(user.getAvatars() == null || user.getAvatars().size() <= 1){
            return false;
        }
        if(TextUtils.isEmpty(user.getNick())){
            return false;
        }
        if(TextUtils.isEmpty(user.getDescription())){
            return false;
        }
        if(user.getSex() == null){
            return false;
        }
        if(user.getBirthday() == 0){
            return false;
        }
        return true;
    }

    private void updateUserData(){
        user.update(this, new UpdateListener() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(int i, String s) {
                ShowToast("信息修改失败,请检查网络连接");
                initView();
            }
        });
    }
}
