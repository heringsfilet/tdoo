package de.hering.tdoo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.Date;

import de.hering.tdoo.model.Todo;

/**
 * A fragment representing a single Todo detail screen.
 */
public class TodoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String DETAIL_FRAGMENT_TAG = "DETAIL_FRAGMENT_TAG";

    /**
     * The dummy content this fragment is presenting.
     */
    private Todo mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TodoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = Todo.findById(Todo.class, getArguments().getLong(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.todo_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.todo_detail_duedate)).setText(mItem.getDueDateString());
            if(mItem.dueDate.before(new Date())){
                ((LinearLayout) rootView.findViewById(R.id.todo_detail_duedatecontainer)).setBackgroundColor(Color.parseColor("#FFCDD2"));
            }

            ((EditText) rootView.findViewById(R.id.editName)).setText(mItem.name);
            ((TextView) rootView.findViewById(R.id.todo_detail_description)).setText(mItem.description);

            View.OnClickListener onFavouriteClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItem.isFavourite = !mItem.isFavourite;

                    // ToDo Anzeige vom Item ändern (starOn und starOff)
                    View rootView = (View) v.getParent().getParent().getParent();
                    if(mItem.isFavourite){
                        ((ImageView) rootView.findViewById(R.id.starViewOn)).setVisibility(View.VISIBLE);
                        ((ImageView) rootView.findViewById(R.id.starViewOff)).setVisibility(View.GONE);
                    }else{
                        ((ImageView) rootView.findViewById(R.id.starViewOff)).setVisibility(View.VISIBLE);
                        ((ImageView) rootView.findViewById(R.id.starViewOn)).setVisibility(View.GONE);
                    }
                }
            };

            ((ImageView) rootView.findViewById(R.id.starViewOn)).setOnClickListener(onFavouriteClickListener);
            ((ImageView) rootView.findViewById(R.id.starViewOff)).setOnClickListener(onFavouriteClickListener);

            if(mItem.isFavourite){
                ((ImageView) rootView.findViewById(R.id.starViewOn)).setVisibility(View.VISIBLE);
                ((ImageView) rootView.findViewById(R.id.starViewOff)).setVisibility(View.GONE);
            }else{
                ((ImageView) rootView.findViewById(R.id.starViewOff)).setVisibility(View.VISIBLE);
                ((ImageView) rootView.findViewById(R.id.starViewOn)).setVisibility(View.GONE);
            }

            // Todo set OnClickListener
            ((CheckBox) rootView.findViewById(R.id.checkBox)).setChecked(mItem.isDone);

            View.OnClickListener onCheckboxClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItem.isDone = !mItem.isDone;
                }
            };
            ((CheckBox) rootView.findViewById(R.id.checkBox)).setOnClickListener(onCheckboxClickListener);




            View.OnClickListener onDeleteClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(true);
                    builder.setTitle("Eintrag löschen?");
                    builder.setMessage(mItem.name + " wirklich löschen?");
                    builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mItem.delete();
                            getActivity().startActivity(new Intent(getActivity(), TodoListActivity.class));
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            };
            ((Button) rootView.findViewById(R.id.deleteButton)).setOnClickListener(onDeleteClickListener);


            View.OnClickListener onSaveClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Todo update TodoItem
                    ViewParent parent = v.getParent().getParent();
                    LinearLayout parentLayout = (LinearLayout) parent;

                    String enteredName = ((EditText) parentLayout.findViewById(R.id.editName)).getText().toString();
                    mItem.name = enteredName;
                    mItem.save();

                    getActivity().startActivity(new Intent(getActivity(), TodoListActivity.class));
                }
            };
            ((Button) rootView.findViewById(R.id.saveButton)).setOnClickListener(onSaveClickListener);
        }

        return rootView;
    }
}