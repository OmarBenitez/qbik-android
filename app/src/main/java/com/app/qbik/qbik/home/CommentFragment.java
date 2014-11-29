package com.app.qbik.qbik.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.qbik.qbik.R;
import com.app.qbik.qbik.Statics;
import com.app.qbik.qbik.models.Comentario;

public class CommentFragment extends Fragment {


    ListView lv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_comments, container, false);
        lv = (ListView) rootView.findViewById(R.id.commentsList);

        lv.setAdapter(new CommentsAdapter(rootView.getContext(), Statics.currentPub.comentarios));

		return rootView;
	}

    public class CommentsAdapter extends ArrayAdapter<Comentario> {
        private final Context context;
        private final Comentario[] values;

        public CommentsAdapter(Context context, Comentario[] values) {
            super(context, R.layout.comment_item, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.comment_item, parent, false);

            TextView user = (TextView) rowView.findViewById(R.id.commentUsername);
            TextView content = (TextView) rowView.findViewById(R.id.commentContent);

            user.setText(values[position].usuario.nombre);

            content.setText(values[position].contenido);


            return rowView;
        }
    }
}
