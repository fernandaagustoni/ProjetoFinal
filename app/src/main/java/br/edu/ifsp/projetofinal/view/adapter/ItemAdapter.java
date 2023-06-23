package br.edu.ifsp.projetofinal.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;

public class ItemAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private RequestMVP.Presenter presenter;

    public ItemAdapter(Context context, List<Request> data, RequestMVP.Presenter presenter){
        super(context, R.layout.list_view_request, data);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_view_request, null);
            holder = new ViewHolder();
            holder.fromTextView = convertView.findViewById(R.id.textview_from);
            holder.toTextView = convertView.findViewById(R.id.textview_to);
            holder.dateTextView = convertView.findViewById(R.id.textview_date);
            holder.statusTextView = convertView.findViewById(R.id.textview_status);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Request request = (Request) getItem(position);
        holder.fromTextView.setText(request.getOrigem());
        holder.toTextView.setText(request.getDestino());
        holder.dateTextView.setText(request.getDataViagem());
        holder.statusTextView.setText(request.getDataViagem());
        return convertView;
    }
    private static class ViewHolder{
        public TextView fromTextView;
        public TextView toTextView;
        public TextView dateTextView;
        public TextView statusTextView;
    }
}