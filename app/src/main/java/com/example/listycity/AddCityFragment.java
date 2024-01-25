package com.example.listycity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class AddCityFragment extends DialogFragment {

    AddCityFragment(){

    }

    public static AddCityFragment newInstance(City city , int position){
        Bundle args = new Bundle() ;
        args.putSerializable("city" ,  city);
        args.putInt("position" , position) ;
        AddCityFragment fragment = new AddCityFragment() ;
        fragment.setArguments(args);
        return fragment ;
    }
    interface  AddCityDialogListener{
        void addCity(City city);
        void editCity(City city , int position) ;
    }

    private AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        Bundle arguments = getArguments() ;
        if(arguments != null && !arguments.isEmpty()){
            City selectedCity = (City) arguments.getSerializable("city") ;
            int position = arguments.getInt("position") ;
            editCityName.setText(selectedCity.getCityName());
            editProvinceName.setText(selectedCity.getProvince());
            return builder
                    .setView(view)
                    .setTitle("Edit a city")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Add", (dialog, which) -> {
                        String cityName = editCityName.getText().toString();
                        String provinceName = editProvinceName.getText().toString();
                        City updatedCity = new City(cityName , provinceName) ;
                        listener.editCity(updatedCity , position);
                    })
                    .create();
        }else {
            return builder
                    .setView(view)
                    .setTitle("Add a city")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Add", (dialog, which) -> {
                        String cityName = editCityName.getText().toString();
                        String provinceName = editProvinceName.getText().toString();
                        listener.addCity(new City(cityName, provinceName));
                    })
                    .create();
        }
    }
}
