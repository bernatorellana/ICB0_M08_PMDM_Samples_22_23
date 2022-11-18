package org.milainfontanals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.milainfontanals.adapter.CardsAdapter;
import org.milainfontanals.databinding.FragmentLlistaBinding;
import org.milainfontanals.model.Card;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LlistaFragment extends Fragment implements View.OnClickListener, CardsAdapter.PersonatgeSelectedListener {

    FragmentLlistaBinding binding;

    public LlistaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_llista, container, false);
        //binding = FragmentLlistaBinding.inflate(inflater, container, false);
        Button btnBack = v.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        RecyclerView rcyLlista = v.findViewById(R.id.rcyLlista);

        // Programem el recycler view per mostrar la llista
        rcyLlista.setLayoutManager(new LinearLayoutManager(requireContext()));
        rcyLlista.setHasFixedSize(true);
        CardsAdapter adapter = new CardsAdapter(Card.getCartes(),this);
        rcyLlista.setAdapter(adapter);

        return v;
        //return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        NavController navController =  NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_llistaFragment_to_portadaFragment);
    }

    @Override
    public void onPersonatgeSeleccionat(Card seleccionat) {

        View v = this.getView().findViewById(R.id.nav_host_fragment);

        // Preparant els paràmetres del fragment
        Bundle args = new Bundle();
        args.putSerializable(DetallFragment.ARG_PARAM_PERSONATGE, seleccionat);

        boolean esticEnVertical = (v==null);
        if(esticEnVertical) {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_llistaFragment_to_detallFragment, args);

        } else {
            // estic en horitzontal
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.detallFragment2, args);

        }
    }
}