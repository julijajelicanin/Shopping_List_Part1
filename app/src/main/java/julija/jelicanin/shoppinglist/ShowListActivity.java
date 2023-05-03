package julija.jelicanin.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowListActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    Button button;
    EditText editText;
    DbHelper dbHelper;
    String naslov=null;
    ShowListCustomAdapter adapter;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        TextView tv=findViewById(R.id.showlist_name);

        //Intent intent=getIntent();
        //String name=intent.getStringExtra("list_name_key");
        //tv.setText(name);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            naslov = bundle.getString("Naslov");
        }
        tv.setText(naslov);



        Button button=findViewById(R.id.add_button);
        lista=findViewById(R.id.showlist_lista);
        EditText editText=findViewById(R.id.input_task_name);

        ShowListCustomAdapter adapter=new ShowListCustomAdapter(this);
        lista.setAdapter(adapter);

        dbHelper = DbHelper.getInstance(this);
        DBItems[] items = dbHelper.readItems();
        if(items != null)
        {
            for(DBItems item : items)
            {
                if(item.getmItemListName().equals(naslov))
                {
                    adapter.addCharacter(new ShowListCharacter(item.getmItemName(), item.getmItemId(), naslov));
                }
            }
        }

        button.setOnClickListener(this);

                    lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            ShowListCharacter character = (ShowListCharacter) adapter.getItem(position);
                            //adapter.removeCharacter(character);
                            dbHelper.deleteItemId(String.valueOf(character.getmId()));
                            adapter.removeCharacter(character);

                            return true;
                        }
                    });

                    /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                            ShowListCustomAdapter.ViewHolder viewHolder = (ShowListCustomAdapter.ViewHolder) view.getTag();
                            viewHolder.textViewItem.setTextColor(Color.parseColor("#FF0000"));
                            view.setTag(viewHolder);

                        }
                    });*/
                }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_button)
        {
            if (editText.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please, first enter some text in predicted filed!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ShowListCharacter element = new ShowListCharacter(editText.getText().toString(), ShowListCharacter.getTracking(), naslov);
                DBItems item = dbHelper.readItem(String.valueOf(element.getmId()));
                if (item == null)
                {
                    dbHelper.insert(new DBItems(element.getmName(), naslov, element.ismCheck(), element.getmId()));
                    adapter.addCharacter(element);
                    editText.getText().clear();
                }
                else
                {
                    Toast.makeText(this, "ERROR!\nID ALREADY EXIST!", Toast.LENGTH_SHORT).show();
                }

    }
}}}