package org.milainfontanals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milainfontanals.databinding.FragmentDetallBinding;
import org.milainfontanals.model.Card;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetallFragment extends Fragment implements View.OnClickListener {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM_PERSONATGE = "param1";

    private FragmentDetallBinding binding;


    private Card mCard;

    public DetallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment DetallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetallFragment newInstance(Card param1) {
        DetallFragment fragment = new DetallFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_PERSONATGE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCard = (Card) getArguments().getSerializable(ARG_PARAM_PERSONATGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentDetallBinding.inflate(inflater,container, false);

        binding.txvNomPersonatge.setText(mCard.getName());
        binding.btnBack.setOnClickListener(this);


        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        NavController navController =  NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_detallFragment_to_llistaFragment);
    }
}