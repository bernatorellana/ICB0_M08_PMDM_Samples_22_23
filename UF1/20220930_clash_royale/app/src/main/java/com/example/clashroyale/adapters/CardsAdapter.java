package com.example.clashroyale.adapters;

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

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder> {

    List<Card> mCards;
    public CardsAdapter(List<Card> pCards){
        mCards = pCards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new ViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card c = mCards.get(position);
        holder.txvName.setText( c.getId()+"-"+ c.getName());
        holder.txvDesc.setText(c.getDesc());
        holder.txvCost.setText(""+c.getElixirCost());
        holder.imvPhoto.setImageResource(c.getDrawable());
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
        public ViewHolder(@NonNull View fila) {
            super(fila);
            txvName = fila.findViewById(R.id.txvName);
            txvCost = fila.findViewById(R.id.txvCost);
            txvDesc = fila.findViewById(R.id.txvDesc);
            imvPhoto = fila.findViewById(R.id.imvPhoto);
        }
    }
}
