package com.example.traininglog.gorny.treningovy_zapisnik.achievements

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.traininglog.gorny.treningovy_zapisnik.R
import com.example.traininglog.gorny.treningovy_zapisnik.data.AchievementRow
import com.example.traininglog.gorny.treningovy_zapisnik.data.runAchievementList

class RunAchievementList : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_run_achievement, container, false)

        val viewAdapter = RunAchievementAdapter(runAchievementList())
        Log.i("RUN" ,"CREATE VIEW")
        view.findViewById<RecyclerView>(R.id.achievement_list).run {
            Log.i("RUN" ,"RUN")
            setHasFixedSize(true)

            adapter=viewAdapter
        }
        Log.i("RUN" ,"END CREATE VIEW")
        return view
    }

}
//class RunAchievementAdapter(private val myDataset: Array<AchievementRow>)
class RunAchievementAdapter(private val myDataset: Array<AchievementRow>) :
    RecyclerView.Adapter<RunAchievementAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        // create a new view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.achievement_item, parent, false)

        Log.i("RUN" ,"ONCREATE VIEW HOLDER")
        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.item.findViewById<TextView>(R.id.achievement_description).text = myDataset[position].description

        holder.item.findViewById<ImageView>(R.id.achievement_image)
            .setImageResource(myDataset[position].imageOfType)

        /* Ak by som chcel kliknut a ist napriklad na info toho itemu
        Treba potom este dole odkomentovat
        holder.item.setOnClickListener {
            val bundle = bundleOf(USERNAME_KEY to myDataset[position])


            holder.item.findNavController().navigate(
                R.id.action_leaderboard_to_userProfile,
                bundle)
        }

         */
        Log.i("RUN" ,"CREATE VIEW BIND HOLDER")
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
/*
    companion object {
        const val USERNAME_KEY = "userName"
    }

 */
}

