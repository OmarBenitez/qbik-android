package com.app.qbik.qbik.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.qbik.qbik.R;
import com.app.qbik.qbik.Statics;
import com.app.qbik.qbik.models.Usuario;
import com.app.qbik.qbik.home.HomeActivity;

public class LoginFragment extends Fragment {


    TextView correo ;
    TextView pwd ;
    Button btnLogin;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        correo =(TextView) rootView.findViewById(R.id.loginCorreo);
        pwd =(TextView) rootView.findViewById(R.id.loginPswd);
        btnLogin = (Button) rootView.findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn(v);
            }
        });

        return rootView;
	}

    public void logIn(View v){
        if(correo.getText().length() > 0 && pwd.getText().length() > 0){
            Usuario u = new Usuario();
            u.email = correo.getText().toString();
            u.password = pwd.getText().toString();
            Statics.logIn(u);
            if(Statics.loggedId()){
                startActivity(new Intent(getActivity().getBaseContext(), HomeActivity.class));
                getActivity().finish();
            }
        }
    }

}
