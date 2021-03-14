package com.talentomobile.starwars.features.people.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.talentomobile.skell.R
import com.talentomobile.starwars.core.exception.NoDataException
import com.talentomobile.starwars.core.extensions.*
import com.talentomobile.starwars.core.extensions.onClick
import com.talentomobile.starwars.core.navigation.MainActivity
import com.talentomobile.starwars.core.platform.BaseFragment
import com.talentomobile.starwars.features.people.models.view.PersonView
import com.talentomobile.starwars.features.people.view.viewmodels.PersonDetailViewModel
import kotlinx.android.synthetic.main.fragment_person.*
import org.koin.android.viewmodel.ext.android.viewModel

class PersonDetailFragment : BaseFragment(R.layout.fragment_person) {

    private val fragmentArgs: PersonDetailFragmentArgs by navArgs()
    private val personDetailViewModel: PersonDetailViewModel by viewModel()

    // region LIFECYCLE ----------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(personDetailViewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(isFavorite, ::handleIsFavorite)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (fragmentArgs.person != null) {
            initView(fragmentArgs.person!!)
        } else {
            handleFailure(NoDataException())
        }

        initListeners()
    }
    // endregion

    // region PRIVATE METHODS -----------------------------------------------------------------------
    private fun initView(person: PersonView) {
        (requireActivity() as MainActivity).supportActionBar!!.hide()

        personDetailViewModel.getPersonFavorite(person.name)

        tvName.text = person.name
        tvBirthYear.text = person.birth_year
        tvGender.text = person.gender
        tvHeight.text = person.height
        tvHairColor.text = person.hair_color
        tvEyeColor.text = person.eye_color
        tvSkinColor.text = person.skin_color
        tvMass.text = person.mass

        setPersonImage()
    }

    /**
     * Set the image of the person.
     */
    private fun setPersonImage() {
        val image = fragmentArgs.image

        if (image != null) {
            ivBanner.loadFromUrl(image) { drawable ->
                setContainerBackground(drawable)
            }
        }
    }

    /**
     * Use the [drawable] to get the light vibrant color and set it as a background of the screen.
     */
    private fun setContainerBackground(drawable: Drawable?) {
        drawable?.let {
            Palette.from(it.toBitmap()).generate { palette ->
                val backgroundColor = palette?.getLightVibrantColor(getColor(requireContext(), R.color.backgroundColor))
                if (backgroundColor != null) {
                    person_container.setBackgroundColor(backgroundColor)
                }
            }
        }

    }

    private fun initListeners() {
        ivBackButton.onClick { requireActivity().onBackPressed() }

        ivFavButton.onClick { favButtonClicked()  }
    }

    private fun favButtonClicked() {
        ivFavButton.isSelected = !ivFavButton.isSelected
        personDetailViewModel.setPersonFavorite(tvName.text.toString(), ivFavButton.isSelected)
    }

    private fun handleIsFavorite(isFavorite : Boolean?) {
        isFavorite?.let { ivFavButton.isSelected = it }
    }

    private fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    private fun handleFailure(failure: Throwable?) {
        showInfoAlertDialog {
            setTitle(getString(R.string.common_error))
        }.show()
    }

    // endregion
}