package com.example.apprecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprecyclerview.model.Client;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {

    private List<Client> clients;

    public ClientsAdapter( List<Client> clients) {
        this.clients = clients;
    }


    @NonNull
    @Override
    public ClientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewFila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new ClientsAdapter.ViewHolder(viewFila);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Client actual = clients.get(position);
        holder.txvId.setText( actual.getId()+"");
        holder.txvNom.setText( actual.getNom());
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    /**
     * Classe interna ViewHolder: el seu paper és contenir la fila
     * i tenir referències a TOTS els camps de la fila
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvId;
        TextView txvNom;
        public ViewHolder(@NonNull View filaview) {
            super(filaview);
            txvId = filaview.findViewById(R.id.txvId);
            txvNom = filaview.findViewById(R.id.txvNom);
        }
    }
}
