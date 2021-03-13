package com.talentomobile.starwars.features.people.view.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talentomobile.skell.R
import com.talentomobile.starwars.core.extensions.inflate
import com.talentomobile.starwars.core.extensions.loadFromUrl
import com.talentomobile.starwars.core.extensions.randomImage
import com.talentomobile.starwars.features.people.models.view.PersonView
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.item_person_row.view.*

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    internal var collection: List<PersonView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: ((PersonView, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_person_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(person: PersonView, clickListener: ((PersonView, String) -> Unit)?) {
            val image = String.randomImage()
            itemView.ivBanner.loadFromUrl(image)
            itemView.tvName.text = person.name
            itemView.cvPerson.setOnClickListener {
                clickListener?.invoke(person, image)
            }
        }
    }
}
