package julija.jelicanin.shoppinglist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowListCustomAdapter extends BaseAdapter {
    private ArrayList<ShowListCharacter> mCharacters;
    private Context mContext;
    DbHelper dbHelper;

    public ShowListCustomAdapter(Context mContext) {
        mCharacters = new ArrayList<ShowListCharacter>();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mCharacters.size();
    }

    @Override
    public Object getItem(int position) {
        Object retVal=null;
        try{
            retVal=mCharacters.get(position);
        }
        catch(IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{

        TextView textViewItem;
        CheckBox checkBoxItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ShowListCustomAdapter.ViewHolder viewHolder;
        dbHelper = DbHelper.getInstance(parent.getContext());



        if(convertView==null)
        {
            LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.showlist_element_row,null);

            viewHolder =new ShowListCustomAdapter.ViewHolder();
            viewHolder.textViewItem=convertView.findViewById(R.id.showlist_tv);
            viewHolder.checkBoxItem=convertView.findViewById(R.id.showlist_checkbox);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder=(ShowListCustomAdapter.ViewHolder)convertView.getTag();
        }

        ShowListCharacter c=(ShowListCharacter) getItem(position);
        DBItems item = dbHelper.readItem(String.valueOf(c.getmId()));


        viewHolder.textViewItem.setText(c.getmName());
        //viewHolder.checkBoxItem.setChecked(c.ismCheck());
        //proveravam stanje checkBox-a

        //Log.d("text","pocetak()"+c.getmName().toString());
        //Log.d("text","pocetak()"+c.ismCheck());
        if(item.ismItemChecked())
        {
            viewHolder.textViewItem.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            c.setmCheck(true);
            //Log.d("text","if pre vh()"+c.getmName().toString());
            //Log.d("text","if pre vh()"+c.ismCheck());

        }
        else {
            viewHolder.textViewItem.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
            c.setmCheck(false);
            //Log.d("text","else pre vh()"+c.getmName().toString());
            //Log.d("text","els epre vh()"+c.ismCheck());
        }
        viewHolder.textViewItem.setText(item.getmItemName());
        viewHolder.checkBoxItem.setChecked(item.ismItemChecked());
        //Log.d("text","van posle()"+c.getmName().toString());
        //Log.d("text","van posle()"+c.ismCheck());

        //viewHolder.textViewItem.setText(c.getmName());
        //viewHolder.checkBoxItem.setChecked(c.ismCheck());

        /*viewHolder.checkBoxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    viewHolder.textViewItem.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    viewHolder.checkBoxItem.setChecked(true);

                }
                else {
                    viewHolder.textViewItem.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                    viewHolder.checkBoxItem.setChecked(false);
                }
            }
        });*/
        viewHolder.checkBoxItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(viewHolder.checkBoxItem.isChecked())
                {
                    viewHolder.textViewItem.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    viewHolder.checkBoxItem.setChecked(true);
                    c.setmCheck(true);
                    //Log.d("text","viewh petlja if"+c.getmName().toString());
                    //Log.d("text","viewh petlja if"+c.ismCheck());


                }
                else {
                    viewHolder.textViewItem.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                    viewHolder.checkBoxItem.setChecked(false);
                    //Log.d("text","viewh petlja else"+c.getmName().toString());
                    //Log.d("text","viewh petlja else"+c.ismCheck());
                    c.setmCheck(false);
                }*/

                if (viewHolder.checkBoxItem.isChecked()) {
                    viewHolder.textViewItem.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    viewHolder.textViewItem.setBackgroundColor(Color.parseColor("#ffe6ed"));
                    c.setmCheck(true);
                    dbHelper.updateItem(new DBItems(c.getmName(), c.getBelonging(), c.ismCheck(), c.getmId()));
                } else {
                    viewHolder.textViewItem.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                    viewHolder.textViewItem.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    c.setmCheck(false);
                    dbHelper.updateItem(new DBItems(c.getmName(), c.getBelonging(), c.ismCheck(), c.getmId()));
                }

            }
        });
        return convertView;
    }
    public void addCharacter(ShowListCharacter c)
    {
        c.setmCheck(false);
        mCharacters.add(c);
        notifyDataSetChanged();
    }

    public void removeCharacter(ShowListCharacter c)
    {
        mCharacters.remove(c);
        notifyDataSetChanged();
    }
}
