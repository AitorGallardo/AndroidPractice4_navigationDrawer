package com.example.aitor.projectefinal_aitorgallardo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private LugaresBDService bd;
    Cursor cursorTasks;


    private OnFragmentInteractionListener mListener;

    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();


        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Bundle bundle = this.getArguments();
        bd = new LugaresBDService(this.getContext());
        if(bundle != null){
          long taskId = bundle.getLong("id");
            cursorTasks = bd.task(taskId);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        setUpLayoutElements(view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }




    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setUpLayoutElements(View view){

        // Selector (Using an array from '/styles' with the options)
        final Spinner typeSelector = (Spinner) view.findViewById(R.id.typeSpinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.typeOptions_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
        typeSelector.setAdapter(adapter);
        final TextInputEditText addName = (TextInputEditText) view.findViewById(R.id.addName);
        final TextInputEditText addDirection = (TextInputEditText) view.findViewById(R.id.addDirection);
        final TextInputEditText addWeb = (TextInputEditText) view.findViewById(R.id.addWeb);
        final TextInputEditText addPhone = (TextInputEditText) view.findViewById(R.id.addPhone);
        final TextInputEditText addLon = (TextInputEditText) view.findViewById(R.id.addLon);
        final TextInputEditText addLat = (TextInputEditText) view.findViewById(R.id.addLat);
        final TextInputEditText addRate = (TextInputEditText) view.findViewById(R.id.addRate);

        if(cursorTasks != null){
            cursorTasks.moveToFirst();

            String nameToEdit = cursorTasks.getString(cursorTasks.getColumnIndex(bd.PLACESLIST_NOMBRE));
            addName.setText(nameToEdit);
            String dirToEdit = cursorTasks.getString(cursorTasks.getColumnIndex(bd.PLACESLIST_DIRECCION));
            addDirection.setText(dirToEdit);
            String webToEdit = cursorTasks.getString(cursorTasks.getColumnIndex(bd.PLACESLIST_URL));
            addWeb.setText(webToEdit);
            String phoneToEdit = cursorTasks.getString(cursorTasks.getColumnIndex(bd.PLACESLIST_TELEFONO));
            addPhone.setText(phoneToEdit);
            String lonToEdit = cursorTasks.getString(cursorTasks.getColumnIndex(bd.PLACESLIST_LONGITUD));
            addLon.setText(lonToEdit);
            String latToEdit = cursorTasks.getString(cursorTasks.getColumnIndex(bd.PLACESLIST_LATITUD));
            addLat.setText(latToEdit);
        }

        Button button = (Button) view.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = addName.getText().toString() != null ? addName.getText().toString() : null;
                String direction = addDirection.getText().toString() != null ? addDirection.getText().toString() : null;
                String web = addWeb.getText().toString() != null ? addWeb.getText().toString() : null;
                String phone = addPhone.getText().toString() != null ? addPhone.getText().toString() : null;
                String type = typeSelector.getSelectedItem().toString();
                String lon = addLon.getText().toString() != null ? addLon.getText().toString() : null;
                String lat = addLat.getText().toString() != null ? addLat.getText().toString() : null;
                String rate = addRate.getText().toString() != null ? addRate.getText().toString() : null;

                if(cursorTasks != null){
                    long id = Long.parseLong(cursorTasks.getString(cursorTasks.getColumnIndex(bd.PLACESLIST_ID)));
                    bd.taskUpdate(id ,name, direction, web, phone, type, lon, lat, rate);
                }else{
                    bd.taskAdd(name, direction, web, phone, type, lon, lat, rate);
                }

            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
