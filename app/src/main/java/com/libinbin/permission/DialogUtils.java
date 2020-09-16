package com.libinbin.permission;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * @author LiBinBin
 * @Date : 2020/9/15
 * @Description : 不再询问拒绝权限弹窗
 */
public class DialogUtils {
    private static AlertDialog alertDialog;

    public static void showDotAskDialog(Context context ,final OnDialogClickListener listener){
        if(alertDialog!=null){
            alertDialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.style_permission_dialog);
        View view = View.inflate(context, R.layout.dialog_dotask_layout, null);
        TextView tvConfirm = view.findViewById(R.id.tv_ok);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        builder.setView(view);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cancel();
                alertDialog.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.confirm();
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    public interface OnDialogClickListener {
        void confirm();

        void cancel();
    }

}
