package julija.jelicanin.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class NewListActivity extends AppCompatActivity implements View.OnClickListener {
    Button saveButton;
    Button okButton;
    EditText edit1;
    TextView tv1;
    RadioButton radioYes;
    RadioButton radioNo;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        saveButton=findViewById(R.id.save_button);
        radioYes=findViewById(R.id.radio_yes);
        radioNo=findViewById(R.id.radio_no);
        okButton=findViewById(R.id.ok_button);
        edit1=findViewById(R.id.input_list_name);
        tv1=findViewById(R.id.list_name);

        saveButton.setOnClickListener(this);
        okButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_yes:
                if (checked)
                    break;
            case R.id.radio_no:
                if (checked)
                    break;
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.save_button)
        {
            Intent intent=new Intent(NewListActivity.this,WelcomeActivity.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.ok_button)
        {
            String listName=edit1.getText().toString();
            tv1.setText(listName);
        }

    }
}