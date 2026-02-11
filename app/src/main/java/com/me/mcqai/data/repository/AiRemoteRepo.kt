package guru.mcqai.www.data.repository

import guru.mcqai.www.data.datasource.AiRemoteDatasource
import guru.mcqai.www.model.Mcq
import javax.inject.Inject

class AiRemoteRepo @Inject constructor (
    private val aiRemoteDatasource: AiRemoteDatasource,

    ) {

   suspend  fun convertContextIntoMcq(userContent:String): List<Mcq>?{
        return aiRemoteDatasource.mcqGenerator(userContent)
    }

}