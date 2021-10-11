package ru.skyrel74.andersen_homework_5

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.skyrel74.andersen_homework_5.databinding.FragmentContactDetailsBinding

const val CONTACT_TAG = "CONTACT_TAG"

class ContactDetailsFragment : Fragment(R.layout.fragment_contact_details) {

    private val binding by viewBinding(FragmentContactDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contact = arguments?.getSerializable(CONTACT_TAG) as Contact?

        if (contact != null) {
            binding.apply {
                etName.setText(contact.name)
                etSurname.setText(contact.surname)
                etPhone.setText(contact.phone)
                btnSave.setOnClickListener {
                    val changedContact = Contact(contact.id,
                        etName.text.toString(),
                        etSurname.text.toString(),
                        etPhone.text.toString())
                    when(resources.configuration.orientation) {
                        Configuration.ORIENTATION_PORTRAIT -> {
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.container, ContactListFragment.newInstance(changedContact))
                                .commit()
                        }
                        Configuration.ORIENTATION_LANDSCAPE -> {
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.list_container, ContactListFragment.newInstance(changedContact))
                                .remove(this@ContactDetailsFragment)
                                .commit()
                        }
                    }
                }
            }
        }
    }

    companion object {

        fun newInstance(contact: Contact): ContactDetailsFragment {
            val args = Bundle()
            args.putSerializable(CONTACT_TAG, contact)

            val fragment = ContactDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}