package com.example.chroniccaremanagementapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast

class ProgramAdapter(context:Context, mTitle:Array<String>, mDescription: Array<String>, images: IntArray):ArrayAdapter<String>(context, R.layout.row, R.id.textView1, mTitle) {
    internal var context:Context
    internal var images:IntArray
    internal var mTitle:Array<String>
    internal var mDescription:Array<String>
    init{
        this.context = context
        this.images = images
        this.mTitle = mTitle
        this.mDescription = mDescription
    }

    fun View.getView(position: Int, parent: ViewGroup) {
        var singleItem = this
        var holder:ProgramViewHolder ?= null
        if (singleItem == null)
        {
            val layoutInflater = this@ProgramAdapter.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            singleItem = layoutInflater.inflate(R.layout.row, parent, false)
            holder = ProgramViewHolder(singleItem)
            singleItem.setTag(holder)
        }
        else
        {
            holder = singleItem.getTag() as ProgramViewHolder
        }
        holder.images.setImageResource(images[position])
        holder.mTitle.setText(mTitle[position])
        holder.mDescription.setText(mDescription[position])
        singleItem.setOnClickListener(View.OnClickListener {
            fun onCLick(view:View) {
                if (position == 0)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. M.L. Hindonia", Toast.LENGTH_LONG).show()
                }
                if (position == 1)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Tosher S. Contactor", Toast.LENGTH_LONG).show()
                }
                if (position == 2)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Vishal Sanghi", Toast.LENGTH_LONG).show()
                }
                if (position == 3)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. S.R. Bansal", Toast.LENGTH_LONG).show()
                }
                if (position == 4)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Rajkumar Mewara", Toast.LENGTH_LONG).show()
                }
                if (position == 5)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Hardik Kumar", Toast.LENGTH_LONG).show()
                }
                if (position == 6)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Manoj Jain", Toast.LENGTH_LONG).show()
                }
                if (position == 7)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Lal Patlab", Toast.LENGTH_LONG).show()
                }
                if (position == 8)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Goyal", Toast.LENGTH_LONG).show()
                }
                if (position == 9)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Sanjeevni", Toast.LENGTH_LONG).show()
                }
                if (position == 10)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Jeevanjyot", Toast.LENGTH_LONG).show()
                }
                if (position == 11)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Balaji", Toast.LENGTH_LONG).show()
                }
                if (position == 12)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. J. Wathumull", Toast.LENGTH_LONG).show()
                }
                if (position == 13)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Radha Mohan", Toast.LENGTH_LONG).show()
                }
                if (position == 14)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Abhinav Bal", Toast.LENGTH_LONG).show()
                }
                if (position == 15)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Kishore Kumar", Toast.LENGTH_LONG).show()
                }
                if (position == 16)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Seema Laad", Toast.LENGTH_LONG).show()
                }
                if (position == 17)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Radhe Shyamtiwari", Toast.LENGTH_LONG).show()
                }
                if (position == 18)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Nikhil Patel", Toast.LENGTH_LONG).show()
                }
                if (position == 19)
                {
                    Toast.makeText(this@ProgramAdapter.getContext(), "Dr. Hargovindkela", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}