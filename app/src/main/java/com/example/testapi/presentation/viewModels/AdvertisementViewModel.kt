package com.example.testapi.presentation.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.domain.model.AdvertisementFilter
import com.example.testapi.domain.usecase.AddToFavoriteUseCase
import com.example.testapi.domain.usecase.AddToHistoryUseCase
import com.example.testapi.domain.usecase.CreateAdvertisementUseCase
import com.example.testapi.domain.usecase.DeleteAdvertisementUseCase
import com.example.testapi.domain.usecase.DeleteFromFavoriteUseCase
import com.example.testapi.domain.usecase.GetAdvertisementsUseCase
import com.example.testapi.domain.usecase.GetCitiesUseCase
import com.example.testapi.domain.usecase.GetDetailedAdvertisementUseCase
import com.example.testapi.domain.usecase.GetFavoritesUseCase
import com.example.testapi.domain.usecase.GetHistoryUseCase
import com.example.testapi.domain.usecase.GetMyAdvertisementsUseCase
import com.example.testapi.domain.usecase.GetTokenUseCase
import com.example.testapi.domain.usecase.UpdateAdvertisementUseCase
import com.example.testapi.presentation.states.AddToFavoriteState
import com.example.testapi.presentation.states.AddToHistoryState
import com.example.testapi.presentation.states.CreateAdvertisementState
import com.example.testapi.presentation.states.DeleteAdvertisementState
import com.example.testapi.presentation.states.DeleteFromFavoriteState
import com.example.testapi.presentation.states.GetAdvertisementsState
import com.example.testapi.presentation.states.GetCitiesState
import com.example.testapi.presentation.states.GetDetailedAdvertisementState
import com.example.testapi.presentation.states.GetFavoritesState
import com.example.testapi.presentation.states.GetHistoryState
import com.example.testapi.presentation.states.GetMyAdvertisementsState
import com.example.testapi.presentation.states.UpdateAdvertisementState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AdvertisementViewModel @Inject constructor(
    private val getAdvertisementsUseCase: GetAdvertisementsUseCase,
    private val getDetailedAdvertisementUseCase: GetDetailedAdvertisementUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addToHistoryUseCase: AddToHistoryUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val createAdvertisementUseCase: CreateAdvertisementUseCase,
    private val getMyAdvertisementsUseCase: GetMyAdvertisementsUseCase,
    private val updateAdvertisementUseCase: UpdateAdvertisementUseCase,
    private val deleteAdvertisementUseCase: DeleteAdvertisementUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _getAdvertisementsState = mutableStateOf(GetAdvertisementsState())
    private val _getDetailedAdvertisementState = mutableStateOf(GetDetailedAdvertisementState())
    private val _createAdvertisementState = mutableStateOf(CreateAdvertisementState())
    private val _updateAdvertisementState = mutableStateOf(UpdateAdvertisementState())
    private val _addToFavoriteState = mutableStateOf(AddToFavoriteState())
    private val _deleteFromFavoriteState = mutableStateOf(DeleteFromFavoriteState())
    private val _addToHistoryState = mutableStateOf(AddToHistoryState())
    private val _getHistoryState = mutableStateOf(GetHistoryState())
    private val _getFavoritesState = mutableStateOf(GetFavoritesState())
    private val _getMyAdvertisementsState = mutableStateOf(GetMyAdvertisementsState())
    private val _deleteAdvertisementState = mutableStateOf(DeleteAdvertisementState())
    private val _getCitiesState = mutableStateOf(GetCitiesState())

    val getAdvertisementsState: State<GetAdvertisementsState> = _getAdvertisementsState
    val getDetailedAdvertisementState: State<GetDetailedAdvertisementState> =
        _getDetailedAdvertisementState
    val createAdvertisementState: State<CreateAdvertisementState> = _createAdvertisementState
    val updateAdvertisementState: State<UpdateAdvertisementState> = _updateAdvertisementState
    val addToFavoriteState: State<AddToFavoriteState> = _addToFavoriteState
    val deleteFromFavoriteState: State<DeleteFromFavoriteState> = _deleteFromFavoriteState
    val addToHistoryState: State<AddToHistoryState> = _addToHistoryState
    val getHistoryState: State<GetHistoryState> = _getHistoryState
    val getFavoritesState: State<GetFavoritesState> = _getFavoritesState
    val getMyAdvertisementsState: State<GetMyAdvertisementsState> = _getMyAdvertisementsState
    val deleteAdvertisementState: State<DeleteAdvertisementState> = _deleteAdvertisementState
    val getCitiesState: State<GetCitiesState> = _getCitiesState


    private val _isFavorite = mutableStateOf(false)
    private val _filter = mutableStateOf(AdvertisementFilter())
    val isFavorite: State<Boolean> = _isFavorite
    var filter: State<AdvertisementFilter> = _filter

    fun updateFilter(newFilter: AdvertisementFilter) {
        _filter.value = newFilter
    }

    fun loadAdvertisements(
        filter: AdvertisementFilter? = null
    ) {
        viewModelScope.launch {
            _getAdvertisementsState.value = _getAdvertisementsState.value.copy(isLoading = true)
            val token = getTokenUseCase()
            Log.d("Test Token Ad", "$token")
            try {
                _getAdvertisementsState.value = _getAdvertisementsState.value.copy(
                    ads = getAdvertisementsUseCase(
                        filter = filter
                    ),
                    isLoading = false,
                    isSuccessful = true,
                )
                Log.d(
                    "API_DEBUG",
                    "Ads count = ${_getAdvertisementsState.value.ads.size}"
                )
            } catch (e: Exception) {
                _getAdvertisementsState.value = _getAdvertisementsState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            } finally {
                _getAdvertisementsState.value = _getAdvertisementsState.value.copy(
                    isLoading = false
                )
            }
        }
    }

    fun loadDetailedAdvertisement(jobId: Int) {
        viewModelScope.launch {
            _getDetailedAdvertisementState.value = _getDetailedAdvertisementState.value.copy(
                isLoading = true
            )
            try {
                val ad = getDetailedAdvertisementUseCase(jobId)
                _getDetailedAdvertisementState.value =
                    _getDetailedAdvertisementState.value.copy(
                        detailedAd = ad,
                        isLoading = false,
                        isSuccessful = true,
                    )
                Log.d(
                    "DetailedAdvertisement",
                    "Detailed Ad= ${_getDetailedAdvertisementState.value.detailedAd}"
                )
                _addToFavoriteState.value =
                    _addToFavoriteState.value.copy(isSuccessful = ad.isFavorite)
                _deleteFromFavoriteState.value =
                    _deleteFromFavoriteState.value.copy(isSuccessful = !ad.isFavorite)
                _isFavorite.value = ad.isFavorite
            } catch (e: Exception) {
                _getDetailedAdvertisementState.value = _getDetailedAdvertisementState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            } finally {
                _getDetailedAdvertisementState.value = _getDetailedAdvertisementState.value.copy(
                    isLoading = false,
                )
            }

        }
    }

    fun addToFavorite(
        jobId: Int
    ) {
        viewModelScope.launch {
            _addToFavoriteState.value = addToFavoriteState.value.copy(
                isLoading = true
            )
            try {
                val result = addToFavoriteUseCase(jobId)
                Log.d("VM_DEBUG", "AddToFavorite result = $result")
                _addToFavoriteState.value = _addToFavoriteState.value.copy(
                    addToFavorite = result,
                    isLoading = false,
                    isSuccessful = true
                )
                _deleteFromFavoriteState.value = _deleteFromFavoriteState.value.copy(
                    isSuccessful = false,
                    isLoading = false
                )
                _isFavorite.value = true
                Log.d("VM_DEBUG", "AddToFavorite isFavorite = ${_isFavorite.value}")


            } catch (e: Exception) {
                Log.d("VM_DEBUG", "AddToFavorite ERROR = ${e.message}")
                _addToFavoriteState.value = _addToFavoriteState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }

        }
    }

    fun deleteFromFavorite(jobId: Int) {
        viewModelScope.launch {
            _deleteFromFavoriteState.value = _deleteFromFavoriteState.value.copy(isLoading = true)
            try {
                val result = deleteFromFavoriteUseCase(jobId)
                Log.d("VM_DEBUG", "DeleteFromFavorite result = $result")
                _deleteFromFavoriteState.value = _deleteFromFavoriteState.value.copy(
                    deleteFromFavorite = result,
                    isLoading = false,
                    isSuccessful = true
                )
                _addToFavoriteState.value = _addToFavoriteState.value.copy(
                    isSuccessful = false,
                    isLoading = false
                )
                _isFavorite.value = false
                Log.d("VM_DEBUG", "DeleteFavorite isFavorite = ${_isFavorite.value}")
            } catch (e: Exception) {
                _deleteFromFavoriteState.value = _deleteFromFavoriteState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _getFavoritesState.value = _getFavoritesState.value.copy(
                isLoading = true
            )
            try {
                _getFavoritesState.value = _getFavoritesState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    favorites = getFavoritesUseCase()
                )
                Log.d(
                    "GetFavorites",
                    "Favorites Ads= ${_getFavoritesState.value.favorites}"
                )
            } catch (e: Exception) {
                _getFavoritesState.value = _getFavoritesState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun addToHistory(
        jobId: Int
    ) {
        viewModelScope.launch {
            _addToHistoryState.value = _addToHistoryState.value.copy(
                isLoading = true
            )
            try {
                _addToHistoryState.value = _addToHistoryState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    addToHistory = addToHistoryUseCase(jobId = jobId)
                )
            } catch (e: Exception) {
                _addToHistoryState.value = _addToHistoryState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            _getHistoryState.value = _getHistoryState.value.copy(
                isLoading = true
            )
            try {
                _getHistoryState.value = _getHistoryState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    history = getHistoryUseCase()
                )
                Log.d(
                    "GetHistory",
                    "History Ads= ${_getHistoryState.value.history}"
                )
            } catch (e: Exception) {
                _getHistoryState.value = _getHistoryState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun toggleFavorite(jobId: Int) {

        val list = _getAdvertisementsState.value.ads.toMutableList()

        val index = list.indexOfFirst { it.id == jobId }

        if (index != -1) {
            val ad = list[index]

            list[index] = ad.copy(
                isFavorite = !ad.isFavorite
            )

        }

        _getAdvertisementsState.value =
            _getAdvertisementsState.value.copy(
                ads = list
            )

    }

    fun toggleHistoryFavorite(jobId: Int) {

        val historyList = _getHistoryState.value.history.toMutableList()

        val historyIndex = historyList.indexOfFirst { it.id == jobId }

        if (historyIndex != -1) {
            val historyAd = historyList[historyIndex]

            historyList[historyIndex] = historyAd.copy(
                isFavorite = !historyAd.isFavorite
            )
        }
        _getHistoryState.value = _getHistoryState.value.copy(
            history = historyList
        )
    }

    fun createAdvertisement(
        title: String,
        wantedJob: String,
        description: String,
        salary: Int,
        date: String,
        timeStart: String,
        timeEnd: String,
        address: String,
        cityId: Int,
        city: String,
        xp: Int,
        age: Int,
        isUrgent: Boolean,
        car: Boolean
    ) {
        viewModelScope.launch {
            _createAdvertisementState.value = _createAdvertisementState.value.copy(
                isLoading = true
            )
            try {
                _createAdvertisementState.value = _createAdvertisementState.value.copy(
                    createAd = createAdvertisementUseCase(
                        title = title,
                        wantedJob = wantedJob,
                        description = description,
                        salary = salary,
                        date = date,
                        timeStart = timeStart,
                        timeEnd = timeEnd,
                        address = address,
                        cityId = cityId,
                        city = city,
                        xp = xp,
                        age = age,
                        isUrgent = isUrgent,
                        car = car
                    ),
                    isLoading = false,
                    isSuccessful = true
                )
                Log.d(
                    "CreateAdvertisement",
                    "Create Ad= ${_createAdvertisementState.value.createAd}"
                )
            } catch (e: Exception) {
                _createAdvertisementState.value = _createAdvertisementState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            } finally {
                _createAdvertisementState.value = _createAdvertisementState.value.copy(
                    isLoading = false
                )
            }
        }
    }

    fun updateAdvertisement(
        title: String? = null,
        wantedJob: String? = null,
        description: String? = null,
        salary: Int? = null,
        date: String? = null,
        timeStart: String? = null,
        timeEnd: String? = null,
        address: String? = null,
        cityId: Int? = null,
        city: String? = null,
        xp: Int? = null,
        age: Int? = null,
        isUrgent: Boolean? = null,
        car: Boolean? = null,
        jobId: Int,
    ) {
        viewModelScope.launch {
            _updateAdvertisementState.value = _updateAdvertisementState.value.copy(
                isLoading = true
            )
            try {
                _updateAdvertisementState.value = _updateAdvertisementState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    updateAd = updateAdvertisementUseCase(
                        title = title,
                        wantedJob = wantedJob,
                        description = description,
                        salary = salary,
                        date = date,
                        timeStart = timeStart,
                        timeEnd = timeEnd,
                        address = address,
                        cityId = cityId,
                        city = city,
                        xp = xp,
                        age = age,
                        isUrgent = isUrgent,
                        car = car,
                        jobId = jobId
                    )
                )
            } catch (e: Exception) {
                _updateAdvertisementState.value = _updateAdvertisementState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun deleteAdvertisement(
        jobId: Int
    ) {
        viewModelScope.launch {
            _deleteAdvertisementState.value = _deleteAdvertisementState.value.copy(
                isLoading = true
            )
            try {
                _deleteAdvertisementState.value = _deleteAdvertisementState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    deleteAd = deleteAdvertisementUseCase(jobId = jobId)
                )
                loadMyAdvertisements()
            } catch (e: Exception) {
                _deleteAdvertisementState.value = _deleteAdvertisementState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun loadMyAdvertisements() {
        viewModelScope.launch {
            _getMyAdvertisementsState.value = _getMyAdvertisementsState.value.copy(isLoading = true)
            try {
                _getMyAdvertisementsState.value = _getMyAdvertisementsState.value.copy(
                    myAds = getMyAdvertisementsUseCase(),
                    isLoading = false,
                    isSuccessful = true,
                )
                Log.d(
                    "API_DEBUG",
                    "Ads count = ${_getMyAdvertisementsState.value.myAds.size}"
                )
            } catch (e: Exception) {
                _getMyAdvertisementsState.value = _getMyAdvertisementsState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            } finally {
                _getMyAdvertisementsState.value = _getMyAdvertisementsState.value.copy(
                    isLoading = false
                )
            }
        }
    }

    fun getCities() {
        viewModelScope.launch {
            _getCitiesState.value = _getCitiesState.value.copy(
                isLoading = true
            )
            try {
                _getCitiesState.value = _getCitiesState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    cities = getCitiesUseCase()
                )
                Log.d(
                    "API_DEBUG",
                    "Cities = ${_getCitiesState.value.cities}"
                )
            } catch (e: Exception) {
                _getCitiesState.value = _getCitiesState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun clearErrors() {
        _createAdvertisementState.value = _createAdvertisementState.value.copy(
            error = null
        )
        _deleteAdvertisementState.value = _deleteAdvertisementState.value.copy(
            isSuccessful = false,
            error = null
        )
    }

}