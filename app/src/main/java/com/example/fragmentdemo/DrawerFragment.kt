package com.example.fragmentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DrawerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DrawerFragment : Fragment() {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DrawerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DrawerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var provinces = listOf<String>("GuangDong","GuangXi","HuNan","HuBei","FuJian")
    private var capitals = listOf<String>("GZ","NN","CS","WH","FZ")
    private lateinit var rvDrawer:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewL = inflater.inflate(R.layout.fragment_drawer, container, false)
        rvDrawer = viewL.findViewById(R.id.rvDrawer)
        val mAdapter = DrawerAdapter(provinces as MutableList<String>,
            capitals as MutableList<String>
        )
        mAdapter.onTabListener = object: OnTabListener{
            override fun onTab(str: String) {
                val fragment = fragmentManager?.findFragmentById(R.id.container_end) as MenuFragment
                fragment.change(str)
            }
        }
        rvDrawer.adapter = mAdapter

        return viewL;
    }

    class DrawerAdapter(var list:MutableList<String>,var capitals:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        var onTabListener:OnTabListener? = null
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return DrawerItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_title,parent,false))
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val tv = holder.itemView.findViewById<TextView>(R.id.tv_title)
            tv.text = list[position]
            holder.itemView.setOnClickListener { onTabListener?.onTab(capitals[position]) }
        }

        override fun getItemCount(): Int = list.size

    }
    class DrawerItemViewHolder(v:View):RecyclerView.ViewHolder(v)
    interface OnTabListener{
        fun onTab(str:String)
    }
}