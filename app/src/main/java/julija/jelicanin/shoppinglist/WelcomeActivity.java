package julija.jelicanin.shoppinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


        //zadatak2
        ListView lista=findViewById(R.id.list);
        CustomAdapter adapter=new CustomAdapter(this);
        lista.setAdapter(adapter);

        //punimo adapter
        adapter.addCharacter(new Character("Naslov1","true"));
        adapter.addCharacter(new Character("Naslov2","true"));
        adapter.addCharacter(new Character("Naslov3","false"));
        adapter.addCharacter(new Character("Naslov4","false"));
        adapter.addCharacter(new Character("Naslov5","true"));
        adapter.addCharacter(new Character("Naslov6","true"));
        adapter.addCharacter(new Character("Naslov7","true"));
        adapter.addCharacter(new Character("Naslov8","true"));
        adapter.addCharacter(new Character("Naslov9","true"));
        adapter.addCharacter(new Character("Naslov10","false"));
        adapter.addCharacter(new Character("Naslov11","true"));
        adapter.addCharacter(new Character("Naslov12","false"));
        adapter.addCharacter(new Character("Naslov13","true"));
        adapter.addCharacter(new Character("Naslov14","false"));


        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Character character=(Character)adapter.getItem(position);
                adapter.removeCharacter(character);
                return false;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(WelcomeActivity.this, ShowListActivity.class);
                Bundle bundle=new Bundle();
                Character character=(Character) adapter.getItem(position);
                String name= character.getmName();
                intent.putExtra("list_name_key",name);
                startActivity(intent);
            }

        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.new_list_button)
        {
            dialog.show();
        }
    }
}