package com.example.aitor.projectefinal_aitorgallardo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlacesListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlacesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlacesListFragment extends Fragment{

    private LugaresBDService bd;

    private static String[] from = new String[]{LugaresBDService.PLACESLIST_NOMBRE, LugaresBDService.PLACESLIST_DIRECCION};
    private static int[] to = new int[]{R.id.placeName, R.id.placeDirection};

    private SimpleCursorAdapter sCursorAdapter;

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
    public static PlacesListFragment newInstance(String param1, String param2) {
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

         bd = new LugaresBDService(this.getContext());
         //loadTasks();
    }

    private void loadTasks(View v) { // We need the view of the fragment, thats why we call this function
                                    // 'onCreateView()' to get the view;

        // We fill the cursor with the query that get all the places
        Cursor cursorTasks = bd.placesList();
        Log.d("GET_CONTEXT_VALUE ===", this.getContext().toString());
        // Now create a simple cursor adapter and set it to display
        sCursorAdapter = new SimpleCursorAdapter(v.getContext(), R.layout.fragment_places_list_row, cursorTasks, from, to);      // scTasks.oTodoListIcon = this;
        ListView lv = (ListView)v.findViewById(R.id.lvDades);
        lv.setAdapter(sCursorAdapter);

//       lv.setOnItemClickListener(
//               new AdapterView.OnItemClickListener()
//               {
//                   @Override
//                   public void onItemClick(AdapterView<?> arg0, View view,
//                                           int position, long id) {
//
//                       // modifiquem el id
//                       updateTask(id);
//                   }
//               }
//       );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_places_list, container, false);

        loadTasks(v);

        return v;

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
class adapterPlacesList extends android.widget.SimpleCursorAdapter {
    private static final String colorTaskPending = "#d78290";
    private static final String colorTaskCompleted = "#d7d7d7";

    public  PlacesListFragment oTodoListIcon;

    public adapterPlacesList(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position, convertView, parent);

        // Agafem l'objecte de la view que es una LINEA DEL CURSOR
        Cursor linia = (Cursor) getItem(position);



        // Capturem botons
/*        ImageView btnMensage = (ImageView) view.findViewById(R.id.btnDelete);
        ImageView btnUpdate = (ImageView) view.findViewById(R.id.btnUpdate);
        btnMensage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Busco la ROW
                View row = (View) v.getParent();
                // Busco el ListView
                ListView lv = (ListView) row.getParent();
                // Busco quina posicio ocupa la Row dins de la ListView
                int position = lv.getPositionForView(row);

                // Carrego la linia del cursor de la posició.
                Cursor linia = (Cursor) getItem(position);

                oTodoListIcon.deleteTask(linia.getInt(linia.getColumnIndexOrThrow(LugaresBD.TODOLIST_ID)));
            }
        });*/

/*        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Busco la ROW
                View row = (View) v.getParent();
                // Busco el ListView
                ListView lv = (ListView) row.getParent();
                // Busco quina posicio ocupa la Row dins de la ListView
                int position = lv.getPositionForView(row);

                // Carrego la linia del cursor de la posició.
                Cursor linia = (Cursor) getItem(position);
                oTodoListIcon.updateTask(linia.getInt(linia.getColumnIndexOrThrow(toDoListDatasource.TODOLIST_ID)));

            }
        });*/

        return view;
    }
}
