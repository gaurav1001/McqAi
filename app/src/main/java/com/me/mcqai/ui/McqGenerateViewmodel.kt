package guru.mcqai.www

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import guru.mcqai.www.data.repository.AiRemoteRepo
import guru.mcqai.www.model.Mcq
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class McqGenerateViewmodel @Inject constructor (
    private val aiRemoteRepo: AiRemoteRepo
): ViewModel() {


    private val _mcqUiState: MutableStateFlow<McqUiState> = MutableStateFlow(McqUiState.Idle())
    val mcqUiState: StateFlow<McqUiState> = _mcqUiState



    private val _mcqScore: MutableStateFlow<Int> = MutableStateFlow(0)
    val mcqScore: StateFlow<Int> = _mcqScore


    fun updateScore(score: Int) {
        _mcqScore.value += score
    }


    fun generateQuestions(userContent: String) {
        viewModelScope.launch {
            _mcqUiState.value = McqUiState.Loading()
            try {
                val result = aiRemoteRepo.convertContextIntoMcq(userContent) ?: return@launch
                _mcqUiState.value = McqUiState.Success(result)
                _mcqScore.value = 0

            } catch (e: Exception) {
                _mcqUiState.value = McqUiState.Error(e)
            }

        }
    }

    fun updateToIdleState(){
        _mcqUiState.value = McqUiState.Idle()
    }

}


sealed class McqUiState {

    data class Idle(val message: String? = null): McqUiState()

    data class Loading(val message: String? = null): McqUiState()

    data class Success(val list: List<Mcq>?): McqUiState()
    data class Error(val exception: Throwable): McqUiState()
}