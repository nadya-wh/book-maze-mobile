package com.kolyadko_polovtseva.book_maze;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.kolyadko_polovtseva.book_maze.entity.Category;
import com.kolyadko_polovtseva.book_maze.entity.Response;
import com.kolyadko_polovtseva.book_maze.network.WebServiceCall;
import com.kolyadko_polovtseva.book_maze.row_model.RowModel;
import com.kolyadko_polovtseva.book_maze.row_model.impl.CategoryRowModel;
import com.kolyadko_polovtseva.book_maze.util.CategoryUtil;
import com.kolyadko_polovtseva.book_maze.util.CustomItemAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class CategoryListActivity extends BaseActivity {

    private List<Category> categories;
    private CustomItemAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categegory_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomItemAdapter(this, new ArrayList<RowModel>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), BookListActivity.class);
                Category category = ((CategoryRowModel) adapter.getModelItems().get(i)).getCategory();
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });
        new CategoryTask().execute();
    }



    private void addCategories(List<Category> categories) {
        ArrayList<RowModel> rows = new ArrayList<>();
        for (Category c : categories) {
            rows.add(new CategoryRowModel(c));
        }
        adapter.updateData(rows);
    }

    public class CategoryTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... paramss) {
            String urlParameters = "";
            Response response = WebServiceCall.postTo("/categories", urlParameters,
                    WebServiceCall.GET_METHOD_NAME);
            if (response.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                categories = CategoryUtil.convert(response.getBody());
            } else {
//                showMessage ("Cannot authenticate: " + response.getResponseCode());
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            addCategories(categories);
        }

        @Override
        protected void onCancelled() {

        }
    }

}
