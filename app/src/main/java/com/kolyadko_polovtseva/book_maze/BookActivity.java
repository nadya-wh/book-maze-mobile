package com.kolyadko_polovtseva.book_maze;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kolyadko_polovtseva.book_maze.entity.Book;
import com.kolyadko_polovtseva.book_maze.entity.Response;
import com.kolyadko_polovtseva.book_maze.network.WebServiceCall;
import com.kolyadko_polovtseva.book_maze.util.AuthorUtil;
import com.kolyadko_polovtseva.book_maze.util.CategoryUtil;
import com.kolyadko_polovtseva.book_maze.util.CloudinaryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;


public class BookActivity extends BaseActivity {

    private Book book;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        book = getIntent().getParcelableExtra("book");

        TextView nameTextView = (TextView) findViewById(R.id.name_text_view);
        TextView authorTextView = (TextView) findViewById(R.id.author_text_view);
        imageView = (ImageView) findViewById(R.id.book_image);
        TextView description = (TextView) findViewById(R.id.description);

        if (book != null) {
            nameTextView.setText("Name: " + book.getName());
            authorTextView.setText("Authors: " + AuthorUtil.toString(book.getAuthors()));
            description.setText(book.getDescription());
        }


        FloatingActionButton reserveBookButton = (FloatingActionButton) findViewById(R.id.reserve_book_fab);
        reserveBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences myprefs = getApplicationContext().getSharedPreferences("user", MODE_APPEND);
                String token = myprefs.getString("login", "");
                new ReserveBookTask(token).execute();
            }
        });

        DownloadPictureTask downloadPictureTask = new DownloadPictureTask();
        downloadPictureTask.execute(CloudinaryUtil.getInstance().generateImageUrl(book.getImageUrl(), 100, 150));
    }


    class DownloadPictureTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap image = null;
            try {
                java.net.URL url = new java.net.URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                image = BitmapFactory.decodeStream(input);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }



    class ReserveBookTask extends AsyncTask<Void, Void, Boolean> {

        private String token;

        public ReserveBookTask(String token) {
            this.token = token;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            String urlParameters = "book=" + book.getIdBook() + "&userId=" + token;
            Response response = WebServiceCall.postTo("/user/books/reserved", urlParameters,
                    WebServiceCall.GET_METHOD_NAME);
            return response.getResponseCode() == HttpsURLConnection.HTTP_OK;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            String message = result ? "The book was reserved." : "The book was not reserved.";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}
