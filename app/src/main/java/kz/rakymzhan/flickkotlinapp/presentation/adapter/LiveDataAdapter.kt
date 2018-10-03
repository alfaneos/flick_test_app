package kz.rakymzhan.flickkotlinapp.presentation.adapter

import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kz.rakymzhan.flickkotlinapp.R

class LiveDataAdapter(context: Context?, cursor: Cursor?): CursorAdapter(context, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER){
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.search_text_view, parent)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val textView = view?.findViewById<TextView>(R.id.search_src_text)
        textView?.text = cursor?.getString(0)
    }

}