package com.example.aitor.projectefinal_aitorgallardo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.math.BigDecimal;


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

    TextInputEditText name, direction, web, phone, lon, lat;
    Spinner typeSelector;
    RatingBar rating;


    private LugaresBDService bd;
    Cursor cursorWithaPlaceToEdit;

    private boolean edditing_place;

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

        if(bundle != null){ // we need to be aware when our form is adding or edditing
            edditing_place = true;
            long placeId = bundle.getLong("id");
            cursorWithaPlaceToEdit = bd.place(placeId);
        } else {
            edditing_place = false;
        }

        setHasOptionsMenu(true); //we need this function to edit the menu, it report that this fragment would like to participate in populating the options menu
                                 // by receiving a call to onCreateOptionsMenu(Menu, MenuInflater) and related methods.


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        setUpLayoutElements(view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(edditing_place){
            inflater.inflate(R.menu.edit_menu, menu);
        } else {
            inflater.inflate(R.menu.add_menu, menu);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PlacesListFragment listFragment = new PlacesListFragment(); // any action on menu ends up on returning to listview

        switch (item.getItemId()) {
            case R.id.arrow_back:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.your_placeholder, listFragment)
                        .commit();
                return true;
            case R.id.action_save:
                addOrUpdate();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.your_placeholder, listFragment)
                        .commit();
                return true;
            case R.id.action_delete:
                long id = Long.parseLong(cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_ID)));
                bd.placeDelete(id);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.your_placeholder, listFragment)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        // Selector
        typeSelector = (Spinner) view.findViewById(R.id.typeSpinner);
            // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<TipoLugar> adapter = new ArrayAdapter<TipoLugar>(view.getContext(), android.R.layout.simple_spinner_item,TipoLugar.values());
            // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
        typeSelector.setAdapter(adapter);
        name = (TextInputEditText) view.findViewById(R.id.addName);
        direction = (TextInputEditText) view.findViewById(R.id.addDirection);
        web = (TextInputEditText) view.findViewById(R.id.addWeb);
        phone = (TextInputEditText) view.findViewById(R.id.addPhone);
        lat = (TextInputEditText) view.findViewById(R.id.addLat);
        lon = (TextInputEditText) view.findViewById(R.id.addLon);
        rating = (RatingBar) view.findViewById(R.id.ratingBar);

        if(edditing_place){

            cursorWithaPlaceToEdit.moveToFirst();
            String nameToEdit = cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_NOMBRE));
            name.setText(nameToEdit);
            String dirToEdit = cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_DIRECCION));
            direction.setText(dirToEdit);
            String webToEdit = cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_URL));
            web.setText(webToEdit);
            String phoneToEdit = cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_TELEFONO));
            phone.setText(phoneToEdit);
            String latToEdit = cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_LATITUD)) != null ?  cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_LATITUD)) : "";
            lat.setText(latToEdit);
            String lonToEdit = cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_LONGITUD)) != null ? cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_LONGITUD)) : "" ;
            lon.setText(lonToEdit);
            // We store enum value, is there for that we look for it on our adapter and then we take
            //  the position in the adapter. With this we can set preselected value in spinner
            int TypeToEditValue = cursorWithaPlaceToEdit.getInt(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_TIPO));
            int spinnerPosition = adapter.getPosition(TipoLugar.findLugarbyValue(TypeToEditValue));
            typeSelector.setSelection(spinnerPosition);
            float rateToEdit = cursorWithaPlaceToEdit.getFloat(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_VALORACION));
            rating.setRating(rateToEdit);

        }

    }

    public void addOrUpdate(){

        String name = this.name.getText().toString() != null ? this.name.getText().toString() : null;
        String direction = this.direction.getText().toString() != null ? this.direction.getText().toString() : null;
        String web = this.web.getText().toString() != null ? this.web.getText().toString() : null;
        String phone = this.phone.getText().toString() != null ? this.phone.getText().toString() : null;
        int type = ((TipoLugar)typeSelector.getSelectedItem()).getValue();
        float rate = rating.getRating();
        String lat;
        String lon;

        try {
            Double.parseDouble(this.lat.getText().toString());
            lat = this.lat.getText().toString();
        }
        catch (NumberFormatException e) {
            // Using default value
            lat = null;
        }
        try {
            Double.parseDouble(this.lon.getText().toString());
            lon = this.lon.getText().toString();
        }
        catch (NumberFormatException e) {
            // Using default value
            lon = null;
        }



        if(edditing_place){
            long id = Long.parseLong(cursorWithaPlaceToEdit.getString(cursorWithaPlaceToEdit.getColumnIndex(bd.PLACESLIST_ID)));
            bd.placeUpdate(id ,name, direction, web, phone, type, lat, lon, rate);
        }else{
            bd.placeAdd(name, direction, web, phone, type, lat, lon, rate);
        }
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
