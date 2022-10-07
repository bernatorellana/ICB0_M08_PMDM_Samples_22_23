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
import com.example.clashroyale.touch.ItemTouchHelperInterface;

import java.util.Collections;
import java.util.List;

public class CardsAdapter
        extends RecyclerView.Adapter<CardsAdapter.ViewHolder>
        implements ItemTouchHelperInterface {

    List<Card> mCards;
    public CardsAdapter(List<Card> pCards){
        mCards = pCards;
    }
    private int posItemSeleccionat = -1;

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
                int posAntiga = posItemSeleccionat;
                posItemSeleccionat = pos;
                /*for (int i=0;i<mCards.size();i++) {
                    if(mCards.get(i).isSelected()) {
                        mCards.get(i).setSelected(false);
                        notifyItemChanged(i);
                        break;
                    }
                }
                mCards.get(pos).toogleSelect();*/
                if(posAntiga!=-1) notifyItemChanged(posAntiga);
                notifyItemChanged(pos);
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
        Log.d("XXX", "Actualitzant fila YY "+position);

        holder.vieSelected.setVisibility(
                    (position==posItemSeleccionat)?
                            View.VISIBLE:
                            View.INVISIBLE );
        /*holder.vieSelected.setVisibility( c.isSelected()?
                                            View.VISIBLE:
                                            View.INVISIBLE );*/

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



    @Override
    public void move(int fromIndex, int toIndex) {
        Card c = mCards.remove(fromIndex);
        if(toIndex>fromIndex){
            toIndex--;
        }
        mCards.add(toIndex,c);
        notifyItemMoved(fromIndex, toIndex);
    }

    @Override
    public void delete(int toDelete) {
        if(toDelete!=-1){
            mCards.remove(toDelete);
            notifyItemRemoved(toDelete);
            //---------------------------------------
            if(toDelete==this.posItemSeleccionat) {
                if (this.posItemSeleccionat == mCards.size()) {
                    this.posItemSeleccionat--;
                }
                if (posItemSeleccionat != -1) {
                    notifyItemChanged(this.posItemSeleccionat);
                }
            }
        }
    }

    public void esborraActual(){
        delete(posItemSeleccionat);
    }

    public void upSelected(){
        if(this.posItemSeleccionat!=-1 && this.posItemSeleccionat>0){
            Collections.swap(mCards,this.posItemSeleccionat-1,this.posItemSeleccionat);
            notifyItemMoved(posItemSeleccionat, posItemSeleccionat-1);
            this.posItemSeleccionat--;
            notifyItemChanged(posItemSeleccionat);
        }
    }


    public void downSelected(){
        if(this.posItemSeleccionat!=-1 && this.posItemSeleccionat< mCards.size()-1){
            Collections.swap(mCards,this.posItemSeleccionat+1,this.posItemSeleccionat);
            notifyItemMoved(posItemSeleccionat, posItemSeleccionat+1);
            this.posItemSeleccionat++;
            notifyItemChanged(posItemSeleccionat);
        }
    }

}
