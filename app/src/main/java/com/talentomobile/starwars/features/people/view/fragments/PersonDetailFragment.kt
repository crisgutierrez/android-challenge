package com.talentomobile.starwars.features.people.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.talentomobile.skell.R
import com.talentomobile.starwars.core.extensions.loadFromUrl
import com.talentomobile.starwars.core.extensions.onClick
import com.talentomobile.starwars.core.extensions.showInfoAlertDialog
import com.talentomobile.starwars.core.navigation.MainActivity
import com.talentomobile.starwars.core.platform.BaseFragment
import com.talentomobile.starwars.features.people.models.view.PersonView
import kotlinx.android.synthetic.main.fragment_person.*

class PersonDetailFragment : BaseFragment(R.layout.fragment_person) {

    private val fragmentArgs: PersonDetailFragmentArgs by navArgs()

    // region LIFECYCLE ----------------------------------------------------------------------------
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (fragmentArgs.person != null) {
            initView(fragmentArgs.person!!)
        } else {
            handleFailure()
        }
        
        initListeners()
    }
    // endregion

    // region PRIVATE METHODS -----------------------------------------------------------------------
    private fun initView(person: PersonView) {
        (requireActivity() as MainActivity).supportActionBar!!.hide()

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
    }

    private fun handleFailure() {
        showInfoAlertDialog {
            setTitle(getString(R.string.common_error))
        }.show()
    }

    // endregion
}