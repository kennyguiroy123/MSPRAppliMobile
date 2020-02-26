package fr.epsi.applimspr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogPopup extends AppCompatDialogFragment {
    String message;
    String title;
    String button;
    public DialogPopup(String title, String message, String button ){
        this.button = button;
        this.message = message;
        this.title = title;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setMessage(message).setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }
}
