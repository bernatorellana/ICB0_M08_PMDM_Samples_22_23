package org.milainfontanals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.milainfontanals.adapter.CardsAdapter;
import org.milainfontanals.databinding.FragmentLlistaBinding;
import org.milainfontanals.model.Card;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LlistaFragment extends Fragment implements View.OnClickListener {

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
        //return inflater.inflate(R.layout.fragment_llista, container, false);
        binding = FragmentLlistaBinding.inflate(inflater, container, false);
        binding.btnBack.setOnClickListener(this);

        // Programem el recycler view per mostrar la llista
        binding.rcyLlista.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rcyLlista.setHasFixedSize(true);
        CardsAdapter adapter = new CardsAdapter(Card.getCartes(), requireContext());
        binding.rcyLlista.setAdapter(adapter);


        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        NavController navController =  NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_llistaFragment_to_portadaFragment);
    }
}