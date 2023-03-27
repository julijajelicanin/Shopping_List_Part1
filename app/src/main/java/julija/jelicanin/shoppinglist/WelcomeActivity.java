package julija.jelicanin.shoppinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
Button button1;
Button button2;
TextView tv1;
AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv1=findViewById(R.id.text_view_user);
        button1=findViewById(R.id.new_list_button);
        button2=findViewById(R.id.see_my_lists_button);

        dialog=new AlertDialog.Builder(this);
        dialog.setTitle("New List Dialog");
        dialog.setMessage("Are you sure you want to create new list?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(WelcomeActivity.this, NewListActivity.class);
                startActivity(intent);
            }
        });
        dialog.create();
        button2.isEnabled();

        Intent intent=getIntent();
        String name=intent.getStringExtra("name_key");
        tv1.setText(name);

        button1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.new_list_button)
        {
            dialog.show();
        }
    }
}