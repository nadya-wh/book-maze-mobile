package com.kolyadko_polovtseva.book_maze;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kolyadko_polovtseva.book_maze.entity.Book;
import com.kolyadko_polovtseva.book_maze.entity.RegisterRecord;
import com.kolyadko_polovtseva.book_maze.entity.Response;
import com.kolyadko_polovtseva.book_maze.network.WebServiceCall;
import com.kolyadko_polovtseva.book_maze.row_model.RowModel;
import com.kolyadko_polovtseva.book_maze.row_model.impl.BookRowModel;
import com.kolyadko_polovtseva.book_maze.row_model.impl.RegisterRecordRowModel;
import com.kolyadko_polovtseva.book_maze.util.CustomItemAdapter;
import com.kolyadko_polovtseva.book_maze.util.RegisterRecordUtil;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ReservedBooksActivity extends BaseActivity {

    private List<RegisterRecord> registerRecords;
    private CustomItemAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.reserved_books_list_view);

        adapter = new CustomItemAdapter(this, new ArrayList<RowModel>());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RegisterRecord registerRecord = ((RegisterRecordRowModel)adapter.getModelItems().get(i)).getRegisterRecord();
                Intent intent = new Intent(getBaseContext(), RegisterRecordActivity.class);
                intent.putExtra("registerRecord", registerRecord);
                startActivity(intent);
            }
        });
        SharedPreferences myprefs = getApplicationContext().getSharedPreferences("user", MODE_APPEND);
        String token = myprefs.getString("login", "");
        new ReservedBookTask(token).execute();
    }

    private void addRecords(List<RegisterRecord> registerRecords) {
        ArrayList<RowModel> rows = new ArrayList<>();
        for (RegisterRecord b : registerRecords) {
            rows.add(new RegisterRecordRowModel(b));
        }
        adapter.updateData(rows);
    }

    public class ReservedBookTask extends AsyncTask<Void, Void, List<RegisterRecord>> {

        private final String token;

        public ReservedBookTask(String token) {
            this.token = token;
        }

        @Override
        protected List<RegisterRecord> doInBackground(Void... paramss) {
            String urlParameters = "userId=" + token;
            Response response = WebServiceCall.postTo("/user/books/reserved/mine", urlParameters,
                    WebServiceCall.GET_METHOD_NAME);
            if (response.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                return RegisterRecordUtil.convert(response.getBody());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<RegisterRecord> registerRecords) {
            if (registerRecords != null) {
                addRecords(registerRecords);
            } else {
                Toast.makeText(getApplicationContext(), "Something gone wrong", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

}
