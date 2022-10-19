package com.example.clashroyale.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clashroyale.FitxaActivity;
import com.example.clashroyale.R;
import com.example.clashroyale.model.Card;
import com.example.clashroyale.touch.ItemTouchHelperInterface;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Collections;
import java.util.List;

public class CardsAdapter
        extends RecyclerView.Adapter<CardsAdapter.ViewHolder>
        implements ItemTouchHelperInterface {

    private ImageLoader mImageLoader;
    private List<Card> mCards;
    private int mPosItemSeleccionat = -1;
    private Context mContext;

    public CardsAdapter(List<Card> pCards, Context c){

        mCards = pCards;
        mImageLoader = ImageLoader.getInstance(); // Get singleton instance
        mContext = c;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        ViewHolder vh = new ViewHolder(fila);


        fila.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Obrir la finestra de visualització de la vistaç
                // del personatge
                Intent i = new Intent( mContext, FitxaActivity.class);

                // Prenem el personatge de la posició corresponent
                int pos = vh.getAdapterPosition();
                Card c = mCards.get(pos);

                // passar els paràmetres que ens facin falta
                i.putExtra(FitxaActivity.CARTA, c);
                mContext.startActivity(i);

                return false;
            }
        });


        // Programació del clic de la fila
        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = vh.getAdapterPosition();
                Log.d("XXX", "Han fet click a la posició "+pos);
                //mCards.get(pos).setSelected(true);
                int posAntiga = mPosItemSeleccionat;
                mPosItemSeleccionat = pos;
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


        // Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
        //	which implements ImageAware interface)
        mImageLoader.displayImage(c.getImageURL(), holder.imvPhoto);

        Log.d("XXX", "Actualitzant fila YY "+position);

        holder.vieSelected.setVisibility(
                    (position== mPosItemSeleccionat)?
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
            if(toDelete==this.mPosItemSeleccionat) {
                if (this.mPosItemSeleccionat == mCards.size()) {
                    this.mPosItemSeleccionat--;
                }
                if (mPosItemSeleccionat != -1) {
                    notifyItemChanged(this.mPosItemSeleccionat);
                }
            }
        }
    }

    public void esborraActual(){
        delete(mPosItemSeleccionat);
    }

    public void upSelected(){
        if(this.mPosItemSeleccionat !=-1 && this.mPosItemSeleccionat >0){
            Collections.swap(mCards,this.mPosItemSeleccionat -1,this.mPosItemSeleccionat);
            notifyItemMoved(mPosItemSeleccionat, mPosItemSeleccionat -1);
            this.mPosItemSeleccionat--;
            notifyItemChanged(mPosItemSeleccionat);
        }
    }


    public void downSelected(){
        if(this.mPosItemSeleccionat !=-1 && this.mPosItemSeleccionat < mCards.size()-1){
            Collections.swap(mCards,this.mPosItemSeleccionat +1,this.mPosItemSeleccionat);
            notifyItemMoved(mPosItemSeleccionat, mPosItemSeleccionat +1);
            this.mPosItemSeleccionat++;
            notifyItemChanged(mPosItemSeleccionat);
        }
    }

}
