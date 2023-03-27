package julija.jelicanin.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    Button registerButton;
    LoginFragment loginFragment1;
    RegisterFragment registerFragment1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LoginFragment loginFragment=new LoginFragment();
        //RegisterFragment registerFragment=new RegisterFragment();

        loginFragment1=LoginFragment.newInstance("param1","param2");
        registerFragment1=RegisterFragment.newInstance("param1","param2");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout1,loginFragment1)
                .hide(loginFragment1)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout1,registerFragment1)
                .hide(registerFragment1)
                .commit();

        loginButton=findViewById(R.id.button_login);
        registerButton=findViewById(R.id.button_register);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_login) {
            loginButton.setVisibility(View.INVISIBLE);
            registerButton.setVisibility(View.INVISIBLE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(loginFragment1)
                    .commit();
        }
        else if(view.getId()==R.id.button_register) {

                loginButton.setVisibility(View.INVISIBLE);
                registerButton.setVisibility(View.INVISIBLE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(registerFragment1)
                        .commit();
        }
        }
    }