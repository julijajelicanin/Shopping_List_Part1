package julija.jelicanin.shoppinglist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText edit1;
    EditText edit2;
    Button button;

    DbHelper dbHelper;
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_login, container, false);
        button=v.findViewById(R.id.login);
        edit1=v.findViewById(R.id.username_edit);
        edit2=v.findViewById(R.id.password_edit);
        button.setOnClickListener(this);

        return v;

    }
    public void onClick(View view){

        /*if(edit1.getText().toString().trim().equals("admin") && edit2.getText().toString().equals("admin"))
        {
            Intent intent=new Intent(getActivity(),WelcomeActivity.class);
            Bundle bundle=new Bundle();
            String name=edit1.getText().toString();
            intent.putExtra("name_key",name);
            //bundle.putString("User",edit1.getText().toString());
            //intent.putExtras(bundle);

            startActivity(intent);
        }*/
        if(edit1.getText().toString().trim().isEmpty() || edit2.getText().toString().isEmpty())
        {
            Toast.makeText(getContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        }
        else{
            dbHelper =DbHelper.getInstance(getContext());
            DBUser user =dbHelper.readUser(edit1.getText().toString());
            if(user != null && user.getmPassword().equals(edit2.getText().toString()))
            {
                Intent intent=new Intent(getActivity(),WelcomeActivity.class);
                Bundle bundle=new Bundle();

                //String name=edit1.getText().toString();
                //intent.putExtra("name_key",name);
                bundle.putString("User", edit1.getText().toString().trim());
                intent.putExtras(bundle);
                Toast.makeText(getContext(), "Username and password match", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else {
                Toast.makeText(getContext(), "Username and password do not match", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}