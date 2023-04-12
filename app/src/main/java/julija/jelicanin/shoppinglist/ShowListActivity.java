package julija.jelicanin.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ShowListActivity extends AppCompatActivity {
    TextView tv;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        TextView tv=findViewById(R.id.showlist_name);

        Intent intent=getIntent();
        String name=intent.getStringExtra("list_name_key");
        tv.setText(name);


        Button button=findViewById(R.id.add_button);
        ListView lista=findViewById(R.id.showlist_lista);
        EditText editText=findViewById(R.id.input_task_name);

        ShowListCustomAdapter adapter=new ShowListCustomAdapter(this);
        lista.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=editText.getText().toString();
                if(!str.equals(""))
                {
                    adapter.addCharacter(new ShowListCharacter(editText.getText().toString(),false));
                }

            }
        }
        );

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ShowListCharacter character=(ShowListCharacter)adapter.getItem(position);
                adapter.removeCharacter(character);
                return false;
            }
        });


    }
}