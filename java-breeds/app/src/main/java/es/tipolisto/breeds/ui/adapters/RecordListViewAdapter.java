package es.tipolisto.breeds.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.model.RecordEntity;

public class RecordListViewAdapter extends BaseAdapter {
    private List<RecordEntity> arrayListRecordEntity;
    private Context context;
    public RecordListViewAdapter(Context context,List<RecordEntity> arrayListRecordEntity) {
        this.arrayListRecordEntity = arrayListRecordEntity;
        this.context=context;
    }

    @Override
    public int getCount() {
        return arrayListRecordEntity.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListRecordEntity.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=LayoutInflater.from(context).inflate(R.layout.item_list_view_records, null);
        RecordEntity recordEntity=arrayListRecordEntity.get(i);
        TextView textViewname=view.findViewById(R.id.textViewNameItemListViewRecords);
        TextView textViewRecordValue=view.findViewById(R.id.textViewScoreListViewRecords);
        textViewname.setText(recordEntity.getName());
        textViewRecordValue.setText(String.valueOf(recordEntity.getScore()));
        return view;
    }
    static class ViewHolder{
        TextView textViewname;
        TextView textViewRecordValue;
        public ViewHolder(View view){
            textViewname=view.findViewById(R.id.textViewNameItemListViewRecords);
            textViewRecordValue=view.findViewById(R.id.textViewScoreListViewRecords);
        }

    }

}
