package com.example.chroniccaremanagementapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ProgramViewHolder internal constructor(v: View) {
    internal var images: ImageView = v.findViewById(R.id.doc)
    internal var mTitle:TextView = v.findViewById(R.id.textView1)
    internal var mDescription: TextView = v.findViewById(R.id.textView2)
}