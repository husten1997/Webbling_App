package com.kinderlicht.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kinderlicht.sql.table_objects.Project_Item;
import com.kinderlicht.sql.table_objects.ToDoList_Item;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //private ListView lv_Todo;
    private RecyclerView lv_Todo;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionButton fab;


    public TodoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodoFragment newInstance(String param1, String param2) {
        TodoFragment fragment = new TodoFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        //lv_Todo = (ListView) view.findViewById(R.id.lv_todo);

        lv_Todo = (RecyclerView) view.findViewById(R.id.lv_todo);
        lv_Todo.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        lv_Todo.setLayoutManager(layoutManager);

        fab = ((StartActivity) getActivity()).getFab();


        System.out.println("do update of drawable");
        fab.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.ic_add_white_24dp));
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "I successfully captured the ActionButton", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ArrayList<ToDoList_Item> list_p1 = new ArrayList<>();

        list_p1.add(new ToDoList_Item(0, false, "This is a test ToDo-List entry in Project 1"));
        list_p1.add(new ToDoList_Item(1, false, "This is a test ToDo-List entry in Project 1"));
        list_p1.add(new ToDoList_Item(2, false, "This is a test ToDo-List entry in Project 1"));
        list_p1.add(new ToDoList_Item(3, false, "This is a test ToDo-List entry in Project 1"));

        ArrayList<ToDoList_Item> list_p2 = new ArrayList<>();

        list_p2.add(new ToDoList_Item(0, false, "This is a test ToDo-List entry in Project 2"));
        list_p2.add(new ToDoList_Item(1, false, "This is a test ToDo-List entry in Project 2"));
        list_p2.add(new ToDoList_Item(2, false, "This is a test ToDo-List entry in Project 2"));
        list_p2.add(new ToDoList_Item(3, false, "This is a test ToDo-List entry in Project 2"));


        ToDoList_Item[] items_p1 = new ToDoList_Item[list_p1.size()];

        for (int i = 0; i < list_p1.size(); i++) {
            items_p1[i] = list_p1.get(i);

        }

        ToDoList_Item[] items_p2 = new ToDoList_Item[list_p2.size()];

        for (int i = 0; i < list_p2.size(); i++) {
            items_p2[i] = list_p2.get(i);

        }

        Project_Item[] projects = new Project_Item[2];
        projects[0] = new Project_Item("Project 1", items_p1);
        projects[1] = new Project_Item("Project 2", items_p2);

        //TodoListArrayAdapter adapter = new TodoListArrayAdapter(getActivity().getApplicationContext(), items);
        ProjectArrayAdapter adapter = new ProjectArrayAdapter(getActivity().getApplicationContext(), projects);
        lv_Todo.setAdapter(adapter);

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


