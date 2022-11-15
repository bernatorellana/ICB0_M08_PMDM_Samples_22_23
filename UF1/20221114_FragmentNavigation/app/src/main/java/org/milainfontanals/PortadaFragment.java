package org.milainfontanals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milainfontanals.databinding.FragmentPortadaBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PortadaFragment extends Fragment implements View.OnClickListener {

    FragmentPortadaBinding binding;

    public PortadaFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPortadaBinding.inflate(inflater,container, false);
        binding.btnGo.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        NavController navController =  NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_portadaFragment_to_llistaFragment);
    }
}