package com.example.clashroyale.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clashroyale.R;
import com.example.clashroyale.model.Card;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder>  {

    List<Card> mCards;
    public CardsAdapter(List<Card> pCards){
        mCards = pCards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        ViewHolder vh = new ViewHolder(fila);

        // Programació del clic de la fila
        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = vh.getAdapterPosition();
                Log.d("XXX", "Han fet click a la posició "+pos);
                //mCards.get(pos).setSelected(true);
                mCards.get(pos).toogleSelect();
                notifyItemChanged(3, );
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card c = mCards.get(position);
        holder.txvName.setText( c.getId()+"-"+ c.getName()+">"+(c.isSelected()?"S":"N"));
        holder.txvDesc.setText(c.getDesc());
        holder.txvCost.setText(""+c.getElixirCost());
        holder.imvPhoto.setImageResource(c.getDrawable());
        Log.d("XXX", "Actualitzant fila "+position);
        holder.vieSelected.setVisibility( c.isSelected()?
                                            View.VISIBLE:
                                            View.INVISIBLE );

    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvName;
        TextView txvCost;
        TextView txvDesc;
        ImageView imvPhoto;
        View vieSelected;
        public ViewHolder(@NonNull View fila) {
            super(fila);
            txvName = fila.findViewById(R.id.txvName);
            txvCost = fila.findViewById(R.id.txvCost);
            txvDesc = fila.findViewById(R.id.txvDesc);
            imvPhoto = fila.findViewById(R.id.imvPhoto);
            vieSelected = fila.findViewById(R.id.vieSelected);
        }
    }
}
