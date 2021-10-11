package ru.skyrel74.andersen_homework_5

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.skyrel74.andersen_homework_5.databinding.FragmentContactListBinding

class ContactListFragment : Fragment(R.layout.fragment_contact_list) {

    private val binding by viewBinding(FragmentContactListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            setContact(etContact1, dataset[0])
            setContact(etContact2, dataset[1])
            setContact(etContact3, dataset[2])
            setContact(etContact4, dataset[3])
        }
    }

    private fun setContact(container: Button, contact: Contact) {
        container.text = "${contact.name} ${contact.surname}\n${contact.phone}"

        val containerId =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                R.id.container
            else
                R.id.details_container

        container.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(containerId, ContactDetailsFragment.newInstance(contact))
                .addToBackStack(ROOT_TAG)
                .commit()
        }
    }

    companion object {

        private val dataset = listOf(
            Contact(1, "Александр", "Пистолетов", "88005553535"),
            Contact(2, "Евгелий", "Бибагур", "82283221488"),
            Contact(3, "Алексей", "Стогусей", "89456123789"),
            Contact(4, "Билли", "Харингтон", "88888888888"),
        )

        fun newInstance(contact: Contact): ContactListFragment {
            dataset[contact.id - 1].apply {
                name = contact.name
                surname = contact.surname
                phone = contact.phone
            }
            return ContactListFragment()
        }
    }
}