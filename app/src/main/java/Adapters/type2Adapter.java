package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hidroist1453uygulamam.R;

import java.util.List;

import Models.type2Pojo;

public class type2Adapter extends BaseAdapter {
    List<type2Pojo> list;
    Context context;

    public type2Adapter(List<type2Pojo> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.type_2_adapter_listview, parent, false);
        TextView isimTextView, degerTextView, tarihTextView;
        isimTextView = (TextView) convertView.findViewById(R.id.tip2AdapterTextViewIsim);
        degerTextView = (TextView) convertView.findViewById(R.id.tip2AdapterTextViewDeger);
        tarihTextView = (TextView) convertView.findViewById(R.id.tip2AdapterTextViewTarih);
        isimTextView.setText(list.get(position).getIsim());
        degerTextView.setText(list.get(position).getDeger());
        tarihTextView.setText(list.get(position).getTarih());

        return convertView;
    }
}
