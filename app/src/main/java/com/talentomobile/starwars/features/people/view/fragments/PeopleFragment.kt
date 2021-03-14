package com.talentomobile.starwars.features.people.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.navigation.fragment.findNavController
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
import com.talentomobile.starwars.core.navigation.MainActivity
import com.talentomobile.starwars.features.people.models.view.PersonView
import kotlinx.android.synthetic.main.navigation_activity.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PeopleFragment : BaseFragment(R.layout.fragment_movies) {

    private val binding by viewBinding(FragmentMoviesBinding::bind)

    private val peopleViewModel: PeopleViewModel by viewModel()
    private val peopleAdapter: PeopleAdapter by inject()

    // region LIFECYCLE ----------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(peopleViewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(people, ::handlePeople)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
    }
    // endregion

    // region PRIVATE METHODS -----------------------------------------------------------------------
    private fun initView() {
        setUpAppbar()
        peopleViewModel.getPeople()

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = peopleAdapter
        }
    }

    private fun setUpAppbar() {
        (requireActivity() as MainActivity).supportActionBar!!.show()
    }

    private fun initListeners() {
        peopleAdapter.clickListener = { personView, image ->
            navigateToPersonDetailFragment(personView, image)
        }
    }

    private fun handlePeople(people: PeopleView?) {
        people.notNull { peopleList ->
            peopleAdapter.collection = peopleList.results.orEmpty()
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

    private fun navigateToPersonDetailFragment(personView: PersonView, image: String) {
        findNavController().navigate(
            PeopleFragmentDirections.actionPeopleToPersonDetailFragment()
                .setPerson(personView)
                .setImage(image)
        )
    }
    // endregion
}
