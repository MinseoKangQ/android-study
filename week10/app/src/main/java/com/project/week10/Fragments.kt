package com.project.week10

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        isCancelable = false
//        val builder = AlertDialog.Builder(requireActivity())
//        builder.setMessage("Dialog Message")
//        builder.setPositiveButton("OK") { dialog, id -> println("OK")}
//        return builder.create()

        // scope 함수
         return AlertDialog.Builder(requireActivity()).apply {
            setMessage("Dialog Message")
            setPositiveButton("OK") { dialog, id -> println("OK")}
         }.create()
    }
}

class Home : Fragment(R.layout.fragment_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView).text = "Home"
    }
}

class Frag1 : Fragment(R.layout.fragment_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView).text = "Frag1"
    }
}

class Frag2 : Fragment(R.layout.fragment_layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView).text = "Frag2"
    }
}