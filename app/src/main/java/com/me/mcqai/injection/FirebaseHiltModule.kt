package guru.mcqai.www.injection

import com.google.firebase.Firebase
import com.google.firebase.ai.FirebaseAI
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseHiltModule {

    @Provides
    fun firebaseAI(): FirebaseAI {
        return Firebase.ai(backend = GenerativeBackend.googleAI())
    }

}