package com.kolyadko_polovtseva.book_maze;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kolyadko_polovtseva.book_maze.entity.Book;
import com.kolyadko_polovtseva.book_maze.entity.Category;
import com.kolyadko_polovtseva.book_maze.entity.Response;
import com.kolyadko_polovtseva.book_maze.network.WebServiceCall;
import com.kolyadko_polovtseva.book_maze.row_model.RowModel;
import com.kolyadko_polovtseva.book_maze.row_model.impl.BookRowModel;
import com.kolyadko_polovtseva.book_maze.util.BookUtil;
import com.kolyadko_polovtseva.book_maze.util.CustomItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class BookListActivity extends BaseActivity {

    private Category category;
    private List<Book> books;
    private CustomItemAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            new SearchBookTask(query).execute();
        } else {
            category = intent.getParcelableExtra("category");
            new BookTask().execute();
        }
        listView = (ListView) findViewById(R.id.book_list_view);

        adapter = new CustomItemAdapter(this, new ArrayList<RowModel>());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = ((BookRowModel)adapter.getModelItems().get(i)).getBook();
                Intent intent = new Intent(getBaseContext(), BookActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void addBooks(List<Book> books) {
        ArrayList<RowModel> rows = new ArrayList<>();
        for (Book b : books) {
            rows.add(new BookRowModel(b));
        }
        adapter.updateData(rows);
    }

    public class BookTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... paramss) {
            String urlParameters = "category=" + category.getIdCategory();
            Response response = WebServiceCall.postTo("/books/category", urlParameters,
                    WebServiceCall.GET_METHOD_NAME);
            if (response.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                books = BookUtil.convert(response.getBody());
            } else {
//                showMessage ("Cannot authenticate: " + response.getResponseCode());
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            addBooks(books);
        }

        @Override
        protected void onCancelled() {

        }
    }

    public class SearchBookTask extends AsyncTask<Void, Void, List<Book>> {

        private final String query;

        public SearchBookTask(String query) {
            this.query = query;
        }

        @Override
        protected List<Book> doInBackground(Void... paramss) {
            List<Book> result = new ArrayList<>();
            String urlParameters = "query=" + query;
            Response response = WebServiceCall.postTo("/books/search", urlParameters,
                    WebServiceCall.POST_METHOD_NAME);
            if (response.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                result = BookUtil.convert(response.getBody());
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            addBooks(books);
        }
    }
}
