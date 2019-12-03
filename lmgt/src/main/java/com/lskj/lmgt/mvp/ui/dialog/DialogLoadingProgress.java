package com.lskj.lmgt.mvp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.lskj.lmgt.R;

import timber.log.Timber;

/**
 * 单例 全局唯一实例
 * 文档描述:    {正在加载dialog}
 * 作者：      Administrator
 * 创建时间：  2019/11/27
 *
 * @author Administrator
 */
public class DialogLoadingProgress extends Dialog {
    private final String TAG = DialogLoadingProgress.class.getSimpleName();
    private static DialogLoadingProgress instance = null;

    public DialogLoadingProgress(Context context) {
        super(context);
    }

    public static DialogLoadingProgress getInstance(Context context) {
        if (instance == null) {
            synchronized (DialogLoadingProgress.class) {
                if (instance == null) {
                    instance = createDialog(context);
                }
            }
        }
        return instance;
    }

    public DialogLoadingProgress(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void show() {
        if (instance == null) {
            Timber.d(TAG, "instance is null show毛线？");
            return;
        }
        if (!instance.isShowing()) {
           super.show();
        }
    }

    private static DialogLoadingProgress createDialog(Context context) {
        DialogLoadingProgress dialog = new DialogLoadingProgress(context, R.style.Style_Dialog_Loading);
        dialog.setContentView(R.layout.dialog_common_loading);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (instance == null) {
            return;
        }
        ImageView imageView = instance.findViewById(R.id.iv_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    /**
     * setTitile 设置标题
     */
    public DialogLoadingProgress setTitile(String strTitle) {
        if (instance != null) {
            instance.setTitile(strTitle);
        }
        return instance;
    }

    /**
     * setMessage 设置信息
     */
    public DialogLoadingProgress setMessage(String strMessage) {
        TextView tvMsg = instance.findViewById(R.id.id_tv_loading_msg);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
        return instance;
    }
}
