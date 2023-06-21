package br.edu.ifsp.projetofinal.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;
import br.edu.ifsp.projetofinal.view.RecyclerViewItemClickListener;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder>{
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
                .inflate(R.layout.activity_request, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Request request = data.get(position);
        holder.fromEditText.setText(request.getOrigem());
        holder.toEditText.setText(request.getDestino());
        holder.statusEditText.setText(request.getStatus());
        holder.dateTextView.setText(request.getDataViagem());

    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setClickListener(RecyclerViewItemClickListener listener){
        clickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public EditText fromEditText;
        public EditText toEditText;
        public EditText statusEditText;
        public TextView dateTextView;

        public ViewHolder(View itemView){
            super(itemView);
            fromEditText = itemView.findViewById(R.id.edittext_from);
            toEditText = itemView.findViewById(R.id.edittext_to);
            statusEditText = itemView.findViewById(R.id.edittext_status);
            dateTextView = itemView.findViewById(R.id.edittext_date);
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
