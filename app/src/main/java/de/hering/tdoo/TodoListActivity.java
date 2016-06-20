package de.hering.tdoo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import de.hering.tdoo.adapters.TodoAdapter;
import de.hering.tdoo.model.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An activity representing a list of Todos. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link TodoDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class TodoListActivity extends AppCompatActivity {
    private ListView listViews;
    private ArrayList<Todo> toDoItems;
    private ArrayAdapter<Todo> toDoItemsAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, TodoDetailActivity.class);

                Todo tmp = new Todo();
                tmp.dueDate = new Date();
                Long id = tmp.save();

                intent.putExtra(TodoDetailFragment.ARG_ITEM_ID, id);

                context.startActivity(intent);
            }
        });

        View recyclerView = findViewById(R.id.todo_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        List<Todo> tdoolist;
        boolean result = true;

        switch (item.getItemId()) {
            case R.id.order_date:
                tdoolist = Todo.findWithQuery(Todo.class, "SELECT * from Todo ORDER BY due_date ASC, is_favourite DESC");
                break;
            case R.id.order_importance:
                tdoolist = Todo.findWithQuery(Todo.class, "SELECT * from Todo ORDER BY is_favourite DESC, due_date ASC");
                break;
            default:
                tdoolist = Todo.findWithQuery(Todo.class, "SELECT * from Todo ORDER BY is_done ASC");
                result =  super.onOptionsItemSelected(item);
        }

        recyclerView.setAdapter(new TodoAdapter(tdoolist));

        return result;
    }

    private void setupRecyclerView(@NonNull RecyclerView rv) {
        recyclerView = rv;
        List<Todo> tdoolist = Todo.findWithQuery(Todo.class, "SELECT * from Todo ORDER BY is_done ASC");
        recyclerView.setAdapter(new TodoAdapter(tdoolist));
    }
}