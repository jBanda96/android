package com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Fragments


import android.content.Context
import android.graphics.SurfaceTexture
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.juliobanda.udemy.R
import com.example.juliobanda.udemy.YourSecondKotlinAndroidApp.Model.TaskList

/**
 * A simple [Fragment] subclass.
 *
 */
class ListSelectionFragment : Fragment() {

    companion object {
        fun newInstance() : ListSelectionFragment {
            return ListSelectionFragment()
        }
    }

    private var listener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is SurfaceTexture.OnFrameAvailableListener) {
            listener = context as OnFragmentInteractionListener
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_selection, container, false)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onListItemClicked(list: TaskList)
    }

}
