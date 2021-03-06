package com.app.qbik.qbik.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.qbik.qbik.R;
import com.app.qbik.qbik.Statics;
import com.app.qbik.qbik.models.Comentario;
import com.app.qbik.qbik.models.Publicacion;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

public class HomePubsFragment extends Fragment {

    ListView lv;
    Publicacion [] pubs;
    View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_home_pubs, container, false);

        lv = (ListView) rootView.findViewById(R.id.listProds);

        AsyncHttpClient client = new AsyncHttpClient();



        client.get("http://108.61.205.173/publicaciones/list", new AsyncHttpResponseHandler() {
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

            final TextView titulo = (TextView) rowView.findViewById(R.id.title);
            final TextView contenido = (TextView) rowView.findViewById(R.id.content);
            final ImageView img = (ImageView) rowView.findViewById(R.id.banner);
            Button btn = (Button) rowView.findViewById(R.id.btnVerMas);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ViewPager p = (ViewPager) getActivity().findViewById(R.id.pager);

                    TextView title = (TextView) getActivity().findViewById(R.id.showTitle);
                    ImageView banner = (ImageView) getActivity().findViewById(R.id.showBanner);
                    TextView content = (TextView) getActivity().findViewById(R.id.showContent);

                    banner.setImageDrawable(img.getDrawable());
                    title.setText(titulo.getText());
                    content.setText(Statics.html2text(values[position].contenido));
                    Statics.currentPub = values[position];
                    p.setCurrentItem(1);

                    AsyncHttpClient client = new AsyncHttpClient();

                    System.out.println(values[position].comentarios);

                }
            });

            System.out.println(values[position].idAsStr);

            titulo.setText(values[position].titulo);
            contenido.setText(Statics.wrap(Statics.html2text(values[position].contenido), 50));
            Statics.imageLoaderMemoryCache(getActivity(), img, R.drawable.logo_blak, values[position].banner);

            return rowView;
        }
    }

}
