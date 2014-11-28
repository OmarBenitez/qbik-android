package com.app.qbik.qbik.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.qbik.qbik.R;
import com.app.qbik.qbik.Statics;
import com.app.qbik.qbik.models.Publicacion;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.InputStream;
import java.net.URL;

public class HomePubsFragment extends Fragment {

    ListView lv;
    Publicacion [] pubs;
    View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_games, container, false);

        lv = (ListView) rootView.findViewById(R.id.listProds);

        AsyncHttpClient client = new AsyncHttpClient();



        client.get("http://108.61.205.173:9000/publicaciones/list", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Gson g = new Gson();
                pubs = g.fromJson(new String(bytes), Publicacion[].class);
                lv.setAdapter(new MySimpleArrayAdapter(rootView.getContext(), pubs));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                //TODO: Porfa pongan acentos mi windows no tiene ):
                Toast toast1 =
                        Toast.makeText(rootView.getContext(),
                                "Error de Comunicacion", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });



		return rootView;
	}

    public class MySimpleArrayAdapter extends ArrayAdapter<Publicacion> {
        private final Context context;
        private final Publicacion[] values;

        public MySimpleArrayAdapter(Context context, Publicacion[] values) {
            super(context, R.layout.simple_list_item, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.simple_list_item, parent, false);

            TextView titulo = (TextView) rowView.findViewById(R.id.title);
            TextView contenido = (TextView) rowView.findViewById(R.id.content);
            ImageView img = (ImageView) rowView.findViewById(R.id.banner);

            System.out.println(titulo);

            titulo.setText("Nombre: "+values[position].titulo);
            contenido.setText("Cantidad: "+values[position].contenido);
            Statics.imageLoaderMemoryCache(getActivity(), img, R.drawable.logo_blak, values[position].banner);

            return rowView;
        }
    }

}
