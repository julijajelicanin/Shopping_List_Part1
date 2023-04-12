package julija.jelicanin.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<Character> mCharacters;
    private Context mContext;

    public CustomAdapter(Context mContext) {
        mCharacters = new ArrayList<Character>();
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

    private class ViewHolder{
        TextView textViewItem1;
        TextView textViewItem2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView==null)
        {
            LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_element_row,null);

            viewHolder =new ViewHolder();
            viewHolder.textViewItem1=convertView.findViewById(R.id.element_name);
            viewHolder.textViewItem2=convertView.findViewById(R.id.element_shared);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        Character c=(Character) getItem(position);
        viewHolder.textViewItem1.setText(c.getmName());
        viewHolder.textViewItem2.setText(c.getmShared());


        return convertView;
    }
    public void addCharacter(Character c)
    {
        mCharacters.add(c);
        notifyDataSetChanged();
    }

    public void removeCharacter(Character c)
    {
        mCharacters.remove(c);
        notifyDataSetChanged();
    }
}
