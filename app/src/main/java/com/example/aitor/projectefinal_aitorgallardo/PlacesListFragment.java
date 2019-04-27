package com.example.aitor.projectefinal_aitorgallardo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlacesListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlacesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlacesListFragment extends Fragment
                                implements EditFragment.OnFragmentInteractionListener{

    private LugaresBDService bd;
    private FragmentActivity myContext;

    Toolbar toolbar;

    private static String[] from = new String[]{LugaresBDService.PLACESLIST_NOMBRE, LugaresBDService.PLACESLIST_DIRECCION};
    private static int[] to = new int[]{R.id.placeName, R.id.placeDirection};

    private SimpleCursorAdapter sCursorAdapter;
    Cursor cursorTasks;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlacesListFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlacesListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlacesListFragment newInstance(String param1, String param2, Activity main) {
        PlacesListFragment fragment = new PlacesListFragment();
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
            setHasOptionsMenu(true); //we need this function to edit the menu, it report that this fragment would like to participate in populating the options menu
                                    // by receiving a call to onCreateOptionsMenu(Menu, MenuInflater) and related methods.

         bd = new LugaresBDService(this.getContext());

    }

    private void loadTasks(View v) { // We need the view of the fragment, thats why we call this function
                                    // 'onCreateView()' to get the view;

        // We fill the cursor with the query that get all the places
        cursorTasks = bd.placesList();
        Log.d("GET_CONTEXT_VALUE ===", this.getContext().toString());
        // Now create a simple cursor adapter and set it to display
        sCursorAdapter = new SimpleCursorAdapter(v.getContext(), R.layout.fragment_places_list_row, cursorTasks, from, to);      // scTasks.oTodoListIcon = this;
        ListView lv = (ListView)v.findViewById(R.id.lvDades);
        lv.setAdapter(sCursorAdapter);

       lv.setOnItemClickListener(
               new AdapterView.OnItemClickListener()
               {
                   @Override
                   public void onItemClick(AdapterView<?> arg0, View view,
                                           int position, long id) {
                       Bundle bundle = new Bundle();
                       bundle.putLong("id",id);

                       EditFragment editFragment = new EditFragment();
                       editFragment.setArguments(bundle);

                       getFragmentManager()
                               .beginTransaction()
                               .replace(R.id.your_placeholder, editFragment)
                               .commit();

                   }
               }
       );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_places_list, container, false);
        loadTasks(v);


        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.list_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                EditFragment editFragment = new EditFragment();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.your_placeholder, editFragment)
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
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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

