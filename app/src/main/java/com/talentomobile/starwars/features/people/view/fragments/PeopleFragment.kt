package com.talentomobile.starwars.features.people.view.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.talentomobile.skell.R
import com.talentomobile.skell.databinding.FragmentMoviesBinding
import com.talentomobile.starwars.core.extensions.failure
import com.talentomobile.starwars.core.extensions.observe
import com.talentomobile.starwars.core.extensions.showInfoAlertDialog
import com.talentomobile.starwars.core.platform.BaseFragment
import com.talentomobile.starwars.core.platform.viewBinding.viewBinding
import com.talentomobile.starwars.features.people.models.view.PeopleView
import com.talentomobile.starwars.features.people.view.adapters.PeopleAdapter
import com.talentomobile.starwars.features.people.view.viewmodels.PeopleViewModel
import com.kotlinpermissions.notNull
import org.koin.android.ext.android.inject

class PeopleFragment : BaseFragment(R.layout.fragment_movies) {

    private val binding by viewBinding(FragmentMoviesBinding::bind)

    private val peopleViewModel: PeopleViewModel by
    private val peopleAdapter: PeopleAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(peopleViewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(people, ::handleMovies)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
    }

    private fun initView() {
        peopleViewModel.getPeople()

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = peopleAdapter
        }
    }

    private fun initListeners() {}

    private fun handleMovies(people: PeopleView?) {
        people.notNull { movies ->
            peopleAdapter.collection = movies.results.orEmpty()
        }
    }

    private fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    private fun handleFailure(failure: Throwable?) {
        showInfoAlertDialog {
            setTitle(getString(R.string.common_error))
        }.show()
    }
}
