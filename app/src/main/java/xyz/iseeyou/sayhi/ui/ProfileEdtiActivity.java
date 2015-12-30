package xyz.iseeyou.sayhi.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;
import xyz.iseeyou.sayhi.R;
import xyz.iseeyou.sayhi.bean.User;
import xyz.iseeyou.sayhi.config.BmobConstants;
import xyz.iseeyou.sayhi.util.DateUtil;
import xyz.iseeyou.sayhi.util.DensityUtil;
import xyz.iseeyou.sayhi.util.PhotoUtil;
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

    private User user;
    private List<BmobFile> avatarList;
    private AvatarAdapter avatarAdapter;

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
        showGenderDialog();
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
            imageView.setImageResource(R.drawable.avatar_team);
            BmobFile bmobFile = avatarList.get(position);
            if (!TextUtils.isEmpty(bmobFile.getUrl())) {
                bmobFile.loadImageThumbnail(mContext, imageView, width, width);
            }
            return convertView;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BmobFile bmobFile = avatarList.get(position);
        String[] items;
        if (!TextUtils.isEmpty(bmobFile.getUrl())) {
            items = new String[]{"照相机", "图库", "设为头像", "删除"};
        } else {
            items = new String[]{"照相机", "图库"};
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEdtiActivity.this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                        switch (which) {
                            case 0:
                                File dir = new File(BmobConstants.MyAvatarDir);
                                if (!dir.exists()) {
                                    dir.mkdirs();
                                }
                                File file = new File(dir, new SimpleDateFormat("yyMMddHHmmss")
                                        .format(new Date()));
                                filePath = file.getAbsolutePath();// 获取相片的保存路径
                                Uri imageUri = Uri.fromFile(file);

                                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                startActivityForResult(intent,
                                        BmobConstants.REQUESTCODE_UPLOADAVATAR_CAMERA);
                                break;
                            case 1:
                                intent = new Intent(Intent.ACTION_PICK, null);
                                intent.setDataAndType(
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(intent,
                                        BmobConstants.REQUESTCODE_UPLOADAVATAR_LOCATION);
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                        }
                    }
                });
        builder.show();
    }

    boolean isFromCamera = false;// 区分拍照旋转
    public String filePath = "";
    int degree = 0;

    /**
     * @return void
     * @throws
     * @Title: startImageAction
     */
    private void startImageAction(Uri uri, int outputX, int outputY,
                                  int requestCode, boolean isCrop) {
        Intent intent = null;
        if (isCrop) {
            intent = new Intent("com.android.camera.action.CROP");
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case BmobConstants.REQUESTCODE_UPLOADAVATAR_CAMERA:// 拍照修改头像
                if (resultCode == RESULT_OK) {
                    if (!Environment.getExternalStorageState().equals(
                            Environment.MEDIA_MOUNTED)) {
                        ShowToast("SD不可用");
                        return;
                    }
                    isFromCamera = true;
                    File file = new File(filePath);
                    degree = PhotoUtil.readPictureDegree(file.getAbsolutePath());
                    Log.i("life", "拍照后的角度：" + degree);
                    startImageAction(Uri.fromFile(file), 200, 200,
                            BmobConstants.REQUESTCODE_UPLOADAVATAR_CROP, true);
                }
                break;
            case BmobConstants.REQUESTCODE_UPLOADAVATAR_LOCATION:// 本地修改头像
                Uri uri = null;
                if (data == null) {
                    return;
                }
                if (resultCode == RESULT_OK) {
                    if (!Environment.getExternalStorageState().equals(
                            Environment.MEDIA_MOUNTED)) {
                        ShowToast("SD不可用");
                        return;
                    }
                    isFromCamera = false;
                    uri = data.getData();
                    startImageAction(uri, 200, 200,
                            BmobConstants.REQUESTCODE_UPLOADAVATAR_CROP, true);
                } else {
                    ShowToast("照片获取失败");
                }

                break;
            case BmobConstants.REQUESTCODE_UPLOADAVATAR_CROP:// 裁剪头像返回
                if (data == null) {
                    ShowToast("取消选择");
                    return;
                } else {
                    uploadAvatar(saveCropAvator(data));
                }
                break;
            default:
                break;
        }
    }

    private void uploadAvatar(String path) {
        BmobLog.i("头像地址：" + path);
        final BmobFile bmobFile = new BmobFile(new File(path));
        bmobFile.upload(this, new UploadFileListener() {
            @Override
            public void onSuccess() {
                String url = bmobFile.getFileUrl(ProfileEdtiActivity.this);
            }

            @Override
            public void onProgress(Integer arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onFailure(int arg0, String msg) {
                ShowToast("头像上传失败：" + msg);
            }
        });
    }

    /**
     * 保存裁剪的头像
     *
     * @param data
     */
    private String saveCropAvator(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");
            Log.i("life", "avatar - bitmap = " + bitmap);
            if (bitmap != null) {
                bitmap = PhotoUtil.toRoundCorner(bitmap, 10);
                if (isFromCamera && degree != 0) {
                    bitmap = PhotoUtil.rotaingImageView(degree, bitmap);
                }
                // 保存图片
                String filename = new SimpleDateFormat("yyMMddHHmmss")
                        .format(new Date()) + ".png";
                String path = BmobConstants.MyAvatarDir + filename;
                PhotoUtil.saveBitmap(BmobConstants.MyAvatarDir, filename,
                        bitmap, true);// 上传头像
                if (bitmap != null && bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                return path;
            }
        }
        return null;
    }
}
