package fr.epsi.applimspr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PromotionAdapter extends BaseAdapter {

    Context context;
    ArrayList<Promotion> arrayList;

    public PromotionAdapter(Context context, ArrayList<Promotion> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.detailspromotion,parent,false);
        }
        TextView pctPromo, libelle, dateExpiration;

        pctPromo =(TextView) convertView.findViewById(R.id.pctPromo);
        libelle = (TextView) convertView.findViewById(R.id.libelle);
        dateExpiration = (TextView) convertView.findViewById(R.id.dateExpriation);

        pctPromo.setText(arrayList.get(position).getPctPromo());
        libelle.setText(arrayList.get(position).getLibelle());
        dateExpiration.setText(arrayList.get(position).getDateExpiration());
        return convertView;
    }
}
