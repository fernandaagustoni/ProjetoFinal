package br.edu.ifsp.projetofinal.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;
import br.edu.ifsp.projetofinal.view.RecyclerViewItemClickListener;

public class ItemRecyclerAdapter  extends
        RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder>{

    private Context context;
    private RequestMVP.Presenter presenter;
    private List<Request> data;
    private static RecyclerViewItemClickListener clickListener;

    public ItemRecyclerAdapter(Context context, List<Request> data, RequestMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_request, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Request request = data.get(position);
        holder.fromTextView.setText(request.getOrigem());
        holder.toTextView.setText(request.getDestino());
        holder.dateTextView.setText(request.getDataViagem());
        holder.statusTextView.setText(request.getStatus());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(RecyclerViewItemClickListener listener){
        clickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        public TextView fromTextView;
        public TextView toTextView;
        public TextView statusTextView;
        public TextView dateTextView;

        public ViewHolder(View itemView){
            super(itemView);
            fromTextView = itemView.findViewById(R.id.textview_from);
            toTextView = itemView.findViewById(R.id.textview_to);
            dateTextView = itemView.findViewById(R.id.textview_date);
            statusTextView = itemView.findViewById(R.id.textview_status);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}