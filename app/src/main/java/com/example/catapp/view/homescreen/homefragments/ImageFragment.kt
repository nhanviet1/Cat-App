package com.example.catapp.view.homescreen.homefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.catapp.data.model.bodymodel.SearchModel
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import com.example.catapp.databinding.FragmentImageBinding
import com.example.catapp.utils.PresenterProvider
import com.example.catapp.utils.disable
import com.example.catapp.utils.enable
import com.example.catapp.utils.CAT_IMAGE_MAX_SIZE
import com.example.catapp.utils.NONE
import com.example.catapp.utils.RANDOM
import com.example.catapp.utils.SEARCH
import com.example.catapp.utils.shortToast
import com.example.catapp.view.adapter.CatImageAdapter
import com.example.catapp.view.homescreen.HomeActivity
import com.example.catapp.view.homescreen.imagescreenpresenter.CatInterface
import java.lang.Exception

class ImageFragment : Fragment(), CatInterface.View {

    private val binding by lazy { FragmentImageBinding.inflate(layoutInflater) }
    private var userApi: String? = null
    private val catAdapter by lazy { CatImageAdapter() }
    private val breedNameList = mutableListOf<String>()
    private val categoryNameList = mutableListOf<String>()
    private var catPresenter: CatInterface.Presenter? = null
    private val breedAdapter by lazy {
        ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, breedNameList
        )
    }
    private val categoryAdapter by lazy {
        ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, categoryNameList
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userApi = HomeActivity.userApi
        setup()
        setupSpinners()
        bindingButton()
        catPresenter?.getRemoteCat(userApi!!, SearchModel())
        catPresenter?.getRemoteBreed()
        catPresenter?.getRemoteCategory()
    }

    private fun setup() {
        catAdapter
        binding.rvCatImage.adapter = catAdapter
        catPresenter = PresenterProvider.catPresenter(this@ImageFragment)
    }

    private fun bindingButton() {
        binding.btnRandom.setOnClickListener {
            if (userApi != null) {
                catPresenter?.getRemoteCat(userApi!!, SearchModel())
            }
        }
    }

    override fun onGetCatSuccess(cat: MutableList<Cat>) {
        if (cat.size < CAT_IMAGE_MAX_SIZE) {
            binding.btnNext.disable(requireContext())
        } else {
            binding.btnNext.enable()
        }
        catAdapter.setData(cat)
    }

    override fun onGetBreedSuccess(breed: MutableList<BreedItem>) {
        breedNameList.clear()
        breedNameList.add(NONE, "None")
        breed.forEach {
            breedNameList.add(it.name)
        }
        breedAdapter.notifyDataSetChanged()
    }

    override fun onGetCategorySuccess(breed: MutableList<CategoriesItem>) {
        categoryNameList.clear()
        categoryNameList.add(NONE, "None")
        breed.forEach {
            categoryNameList.add(it.name)
        }
        categoryAdapter.notifyDataSetChanged()
    }

    override fun onError(exception: Exception?) {
        requireContext().shortToast(exception.toString())
    }

    private fun setupSpinners() {
        binding.selectorOrder.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val selection = adapterView?.getItemAtPosition(position).toString()
                val random = adapterView?.getItemAtPosition(0).toString()
                if (selection == random) {
                    binding.btnRandom.text = RANDOM
                    binding.btnPrevious.disable(requireContext())
                    binding.btnNext.disable(requireContext())
                } else {
                    binding.btnPrevious.enable()
                    binding.btnNext.enable()
                    binding.btnRandom.text = SEARCH
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                //Do it later
            }
        }

        breedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.selectorBreed.adapter = breedAdapter
        binding.selectorBreed.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                //Do it later
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                //Do later
            }
        }

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.selectorCategory.adapter = categoryAdapter
        binding.selectorCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                //Do it later
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                //Do later
            }
        }
    }
}
