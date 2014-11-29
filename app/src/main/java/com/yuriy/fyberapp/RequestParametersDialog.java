package com.yuriy.fyberapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by Yuriy Chernyshov
 * At Android Studio
 * On 11/23/14
 * E-Mail: chernyshov.yuriy@gmail.com
 */
public class RequestParametersDialog extends DialogFragment {

    /**
     * Tag string to use when create a dialog in Activity.
     */
    public static final String FRAGMENT_TAG = RequestParametersDialog.class.getName().toUpperCase();

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final Context context = getActivity();
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.request_parameters_dialog_layout,
                (ViewGroup) getActivity().findViewById(R.id.item_root));

        final EditText uidEditView = (EditText) layout.findViewById(R.id.uid_edit_view);
        final EditText apiKeyEditView = (EditText) layout.findViewById(R.id.api_key_edit_view);
        final EditText appIdEditView = (EditText) layout.findViewById(R.id.app_edit_view);
        final EditText pub0EditView = (EditText) layout.findViewById(R.id.pub0_edit_view);
        final CheckBox useFakeResponseCheckView
                = (CheckBox) layout.findViewById(R.id.use_fake_data_check_view);

        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.request_parameters_dialog_title)
                .setView(layout)
                .setPositiveButton(R.string.ok_string, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ((MainActivity)getActivity()).onRequestParametersDialogResult(
                                uidEditView.getText().toString().trim(),
                                apiKeyEditView.getText().toString().trim(),
                                appIdEditView.getText().toString().trim(),
                                pub0EditView.getText().toString().trim(),
                                useFakeResponseCheckView.isChecked()
                        );
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
