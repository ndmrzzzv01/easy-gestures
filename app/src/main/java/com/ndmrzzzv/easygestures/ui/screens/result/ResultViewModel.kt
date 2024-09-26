package com.ndmrzzzv.easygestures.ui.screens.result

import androidx.lifecycle.ViewModel
import com.ndmrzzzv.easygestures.utils.StudyData

class ResultViewModel: ViewModel() {

    fun getResultOfTest() = StudyData.result

}